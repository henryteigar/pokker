package pokker.lib.network.messages;

public enum MessageType {
    UserData,
    GetTableList,
    TableList,
    JoinTable,
    SuccessfulTableJoin,
    PlayerJoined,
    PlayerLeft,
    TableEvent,
    AskForPlayerAct,
    PlayerAct,
    Acknowledgment
}
