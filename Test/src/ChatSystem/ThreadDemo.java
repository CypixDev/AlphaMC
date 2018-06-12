package ChatSystem;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    static class Zaehlen implements Runnable{
        @Override
        public void run() {

            for(int i = 0; i< 20;i++){
                System.out.println(Thread.currentThread().getName()+" zählt: "+ i);


                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void main(String[] args){

        System.out.println("START");

        ExecutorService executor = Executors.newFixedThreadPool(2);


        Thread t1 = new Thread(new Zaehlen());
        Thread t2 = new Thread(new Zaehlen());

        //Threads einen name geben
        t1.setName("ERSTER");
        t2.setName("ZWEITER");

        //Threads starten
        //t1.start();
        //t2.start();
        executor.execute(t1);
        executor.execute(t2);
        executor.shutdown();

        //auf Threads warten
        try {
            //t1.join();
            //t2.join();
            while(!executor.awaitTermination(1, TimeUnit.SECONDS)){
                System.out.println("Dass Hählen läuft noch !");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ENDE");


    }

}
