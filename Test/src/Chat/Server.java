package Chat;

import org.bukkit.ChatColor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args){

        try {
            ServerSocket server = new ServerSocket(5555);
            System.out.println(ChatColor.RED+"Der Server wurde gestartet !");

            Socket client = server.accept();

            OutputStream out = client.getOutputStream();
            PrintWriter write = new PrintWriter(out);

            InputStream in = client.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            String s = null;

            while((s = read.readLine()) != null){
                System.err.println(s+"\n");
            }
            write.close();
            read.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
