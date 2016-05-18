package pokker.lib.network.messages;

/**
 * Represent a request (which doesn't have a message body)
 */
public class Request extends MessageContainer {
    public Request(MessageType requestType) {
        super(requestType, null);
    }
}
