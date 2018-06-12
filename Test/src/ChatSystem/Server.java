package ChatSystem;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(30);

        ServerSocket server = null;
        try {
            server = new ServerSocket(5555);
            System.out.println("Der Server wurde gestartet !");

            while (true) {
                try {

                    Socket client = server.accept();

                    //Thread t = new Thread(new Handler(client));
                    //t.start();
                    executor.execute(new Handler(client));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
