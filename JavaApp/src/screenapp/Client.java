package screenapp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket clientSocket;
    private ArrayList <String> results = new ArrayList<>();

    private String ID = null;
    private boolean connected = true;

    public Client(Socket s){
        this.clientSocket = s;
    }

    public void heartBeat(){
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("a");
        }catch (Exception e){

        }
    }

    public boolean isConnected(){
        try {
            return this.connected;
        }catch (Exception e){

        }
        return false;
    }
    public void closeSocket(){
        try {
            this.clientSocket.close();
            this.connected = false;
        }catch (Exception e){

        }
    }
    public void setID(String id){
        this.ID = id;
        System.out.println("ID is" + ID);
    }
    public int addResult(int row, int col, int R, int G, int B){
        try {
            int id = results.size();
            results.add(String.format("%02d", row) + String.format("%02d", col) + String.format("%03d", R) + String.format("%03d", G) + String.format("%03d", B));

            System.out.println(String.format("%02d", row) + String.format("%02d", col) + String.format("%03d", R) + String.format("%03d", G) + String.format("%03d", B));

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("00" + String.format("%02d", results.size() - 1) + results.get(results.size() - 1));

            new Thread(() -> {
                try {
                    Thread.sleep(60000);
                    removeResult(id);
                }catch (Exception e){

                }
            }).start();

        }catch (Exception e){
            System.out.println(e.toString());
            ID = null;
            System.out.println("Client timed out");
            try {
                this.clientSocket.close();
            }catch (IOException x){

            }
            return -1;
        }
        return results.size() - 1;
    }

    public void removeResult(int id){
        try {
            results.remove(id);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("01" + id);

            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String respond = br.readLine();
            if(!respond.equals("")){
                ID = null;
                clientSocket.close();
                System.out.println("Client timed out");
            }

        }catch (IOException e){
            try {
                System.out.println("Client timed out");
                this.clientSocket.close();
            }catch (IOException x){

            }
        }
    }

    public void clearResults(){
        int x = 0;
        for (String result : results) {
            try {
                results.remove(x);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("01" + x);
            }catch (IOException e){

            }
            x++;
        }
    }

    public ArrayList <String> getResults(){
        return results;
    }

    public String getAddress(){
        return clientSocket.getRemoteSocketAddress().toString();
    }

    public String getID(){
        return ID;
    }
}
