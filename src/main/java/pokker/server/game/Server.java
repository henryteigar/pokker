package pokker.server.game;

import pokker.lib.network.messages.MessageContainer;
import pokker.lib.network.messages.PlayerJoinedMessage;
import pokker.server.network.ClientConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Server implements Runnable {
    public static void main(String[] args) {
        int port = 1337;

        // let user specify the port as an argument
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("The first argument should be the port number to start the server on!");
                return;
            }
        }

        Server server = new Server(port);
        server.run();
        while (true) {
            if (server.users.size() % 8 == 0) {
                server.randomTableManager();
            }
        }
    }

    /**
     * Port that this server listens on
     */
    private final int port;

    /**
     * List of tables available on the server
     */
    private final List<TableServer> tables = new ArrayList<>();

    /**
     * This counter is used to assign id-s to new tables
     */
    private int tableIdCounter = 0;

    /**
     * List of users connected to the server
     */
    private final List<User> users = new ArrayList<>();

    Server(int port) {
        this.port = port;

        createNewTable(6, 100);
        createNewTable(9, 500);
        createNewTable(7, 300);
    }

    /**
     * Creates a new table on the server
     *
     * @param tableSize maximum amount of players that the table can hold
     * @param bigBlind  size of the big blind
     */
    private void createNewTable(int tableSize, int bigBlind) {
        tables.add(new TableServer(tableSize, bigBlind, tableIdCounter++));
    }

    /*
     * Creates a new table or tries to delete one, taking into account the amount of users online.
     * The size of the tables is from 6 to 10.
     * Big blind is from 100 to 500.
     * Aim is to have one table per 8 users + some extra tables as well.
     */
    private void randomTableManager() {
        int numberOfPeopleOnline = users.size();
        int numberOfTables = tables.size();

        if (numberOfPeopleOnline / 8 + 3 > numberOfTables) {
            int tableSize = ThreadLocalRandom.current().nextInt(6, 11);
            int blind = ThreadLocalRandom.current().nextInt(1, 6) * 100;
            createNewTable(tableSize, blind);
        }

        if (numberOfPeopleOnline / 8 + 1 < numberOfTables) {
            for (TableServer table : tables) {
                if (table.getPlayers().size() == 0) {
                    tables.remove(table);
                    break;
                }
            }
        }
    }


    /**
     * This is called when a user connects to the server. This is important since a connection != user. A user is someone
     * who has sent information about themselves to the server - so only after that data has been received can the
     * server acknowledge that there's an actual user on the server (and not just an open connection)
     *
     * @param user
     */
    public void userConnected(User user) {
        users.add(user);
    }

    /**
     * Runs the server
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try (ServerSocket socket = new ServerSocket(port)) {
            serverSocket = socket;
            acceptSockets(serverSocket);
        } catch (SocketException e) {
            if (serverSocket != null && serverSocket.isClosed()) {
                // socket was closed somehow, restarting server
                run();
            } else {
                System.out.println("Could not open server socket at port " + port + " !");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts accepting sockets on the ServerSocket
     *
     * @param serverSocket
     * @throws IOException
     */
    private void acceptSockets(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientConnection(this, clientSocket);
        }
    }

    /**
     * This is called when a user joins a table
     *
     * @param tableId the id of the table that the user joined
     * @param user    the user that joined the table
     */
    public void userJoinTableId(User user, int tableId) {
        TableServer table = tables.get(tableId);
        PlayerClient playerClient = new PlayerClient(user, table);
        user.joinedTableAsClient(playerClient);

        for (User userI : users) {
            if (userI != user) {
                userI.getConnection().sendMessage(new PlayerJoinedMessage(tableId, playerClient).createContainedMessage());
            }
        }

        table.playerJoined(playerClient);
        if (this.users.size() % 8 == 0) {
            this.randomTableManager();
        }
    }

    /**
     * Broadcasts a message to all users on the server
     *
     * @param message
     */
    public void broadcast(MessageContainer message) {
        for (User user : users) {
            user.getConnection().sendMessage(message);
        }
    }

    /**
     * This is called when a user disconnects from the server
     *
     * @param user
     * @see #userConnected(User)
     */
    public void userDisconnected(User user) {
        for (PlayerClient playerClient : user.getPlayerClients()) {
            playerClient.getTable().playerLeft(playerClient);
        }
    }

    /**
     * @return list of tables available on the server
     */
    public List<TableServer> getTables() {
        return tables;
    }

    public TableServer getTableById(int tableId) {
        return getTables().get(tableId);
    }

}
