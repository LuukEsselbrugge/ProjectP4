package screenapp;

import java.util.ArrayList;

class SharedInstance {
    private static SharedInstance _instance = null;
    String data;
    int search_q = 0;
    TCPServer server;
    ArrayList<Book> books;

    static SharedInstance getInstance() {
        if(_instance == null) {
            _instance = new SharedInstance();
        }

        return _instance;
    }
}
