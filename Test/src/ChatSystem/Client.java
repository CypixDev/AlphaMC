package ChatSystem;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private static int a;

    public static void main(String[] args){
        a = 1;

        new Thread(new Client()).start();
        new Thread(new Client()).start();
        new Thread(new Client()).start();
        new Thread(new Client()).start();


    }


    @Override
    public void run() {
        //Scanner eingabe = new Scanner(System.in);

        try {
            Socket client = new Socket("localhost", 5555);
            System.out.println("Der Client wurde gestartet !");



            //Streams

            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //--------------------------

            //System.out.print("Eingabe: ");
            //String anServer = eingabe.nextLine();

            writer.write("Hallo vom "+a+". Client\n");
            writer.flush();
            a++;

            String s = null;

            while ((s = reader.readLine()) != null){
                System.out.println("Empfangen vom Server: "+ s);
            }

            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
