package screenapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TCPServer implements Runnable {

    protected int serverPort;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;
    private ArrayList<Client> clients = new ArrayList<>();

    public TCPServer(int port) {
        this.serverPort = port;
        System.out.println("TCP Server started");
    }

    public ArrayList<Client> getClients(){
        clients.removeIf(c -> c.getID() == null);
        return clients;
    }

    public Client getClient(String id){
        for(Client c : clients){
            if(c.getID().equals(id)){
                return c;
            }
        }
        return null;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {

            try {
                clients.add(new Client(this.serverSocket.accept()));
                System.out.println("Client connected");

            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

        }
        System.out.println("Server Stopped.");
    }



    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port", e);
        }
    }

}