package Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        try {
            Socket client = new Socket("localhost", 5555);

            OutputStream out = client.getOutputStream();
            PrintWriter write = new PrintWriter(out);

            InputStream in = client.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));


            System.out.print("Eingabe: ");
            String anServer = scanner.nextLine();

            write.write(anServer);
            write.flush();

            write.close();
            read.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
