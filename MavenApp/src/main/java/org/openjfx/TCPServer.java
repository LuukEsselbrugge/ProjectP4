package org.openjfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
        clients.removeIf(c -> c.isConnected() == false);
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
            Client c = new Client(null);
            try {

                Socket s = this.serverSocket.accept();
                s.setSoTimeout(15000);
                c = new Client(s);
                clients.add(c);
                System.out.println("Client connected");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String ID = br.readLine();
                c.setID(ID);


                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Client send " + line);
                    c.heartBeat();
                    try {
                        Thread.sleep(10000);
                    }catch (Exception e){

                    }
                }

            }
            catch (SocketTimeoutException e){
                System.out.println("Client timed out lmao");
                c.closeSocket();

            }
            catch (IOException e) {
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