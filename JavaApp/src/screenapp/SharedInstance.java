package screenapp;

class SharedInstance {
    private static SharedInstance _instance = null;
    String data;
    int search_q = 0;
    TCPServer server;

    static SharedInstance getInstance() {
        if(_instance == null) {
            _instance = new SharedInstance();
        }

        return _instance;
    }
}
