package ChatSystem;

import java.util.Scanner;

public class Test {

    public static String name = "";

    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            //console
            String eingabe = sc.nextLine();
            if(eingabe.equalsIgnoreCase("Hey")){
                System.err.println("Hallo, wie ist dein Name?");
            }
            if(eingabe.equalsIgnoreCase("wie gehts")){
                System.out.println("gut dir?");
            }
            if(eingabe.equalsIgnoreCase("auch")){
                System.out.println("Freut mich");
            }
            if(eingabe.equalsIgnoreCase("gut")){
                System.out.println("Freut mich");
            }
            if(eingabe.startsWith("mein name ist")){
                String[] namee = eingabe.split(" ");

                for(int i = 3;i<namee.length;i++){
                    name = name+namee[i]+" ";
                }
                System.err.println("Freut mich, "+name);
            }
            if(eingabe.equalsIgnoreCase("name")){
                if(name == null)name = "Unbekannt !";
                System.out.println("Dein Name ist: "+name);
                System.err.println("Mein Name ist nicht Cortana !");
            }
            if(eingabe.endsWith("lol")){
                System.err.println("neeee");
            }
            if(eingabe.equalsIgnoreCase("stop")){
                System.exit(1);
            }


            if(eingabe.equalsIgnoreCase("clear")){
                for(int i = 0;i<100;i++){
                    System.out.println(" ");
                }
            }
        }
    }

}
