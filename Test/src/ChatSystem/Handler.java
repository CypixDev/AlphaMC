package ChatSystem;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {

    private Socket client;

    public Handler(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        //Streams

        try {
            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //--------------------------

            String s = null;

            while ((s = reader.readLine()) != null) {
                System.out.println("Empfangen vom Client: " + s);
                writer.write(s + "\n");
                writer.flush();
            }
            writer.close();
            reader.close();

            client.close();
        }catch(IOException e1){

        }

    }


}
