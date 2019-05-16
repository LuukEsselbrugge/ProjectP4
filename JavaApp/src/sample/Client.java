package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket clientSocket;
    private ArrayList <String> results = new ArrayList<>();

    private String ID = null;

    public Client(Socket s){
        this.clientSocket = s;
        try {
            this.clientSocket.setSoTimeout(10000);
            BufferedReader br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            ID = br.readLine();
        }catch (Exception e){
            try {
                this.clientSocket.close();
                System.out.println("Client timed out after not providing ID");
            }catch (IOException x){
                System.out.println(x.toString());
            }
        }
    }

    public int addResult(int row, int col, int R, int G, int B){
        try {
            results.add(String.format("%02d", row) + String.format("%02d", col) + String.format("%02d", R) + String.format("%02d", G) + String.format("%02d", B));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("00" + String.format("%02d", results.size() - 1) + results.get(results.size() - 1));

            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String respond = br.readLine();

            if(!respond.equals("")){
                ID = null;
                clientSocket.close();
                System.out.println("Client timed out");
            }

        }catch (Exception e){
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
