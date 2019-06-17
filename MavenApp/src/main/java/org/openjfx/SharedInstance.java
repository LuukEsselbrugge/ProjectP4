package org.openjfx;

import java.util.ArrayList;

class SharedInstance {
    private static SharedInstance _instance = null;
    String data;
    int search_q = 0;
    TCPServer server;
    ArrayList<Book> books;
    Shelf result;
    int counter = 0;

    /**
     * Shared instance class for sharing variables.
     * @return , instance object.
     */
    static SharedInstance getInstance() {
        if(_instance == null) {
            _instance = new SharedInstance();
        }

        return _instance;
    }
}
