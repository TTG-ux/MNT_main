package THREAD;

import java.util.concurrent.Phaser;

public class PhaserThread {
    
    public static void main(String[] args) {
        
        Phaser phaser = new Phaser();

        new Thread(new PhaserThread_n(phaser, "PhaseThread 1")).start();
        new Thread(new PhaserThread_n(phaser, "PhaseThread 2")).start();

        //ждем завершение фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
        
        //ждем завершение фазы 0
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
        
        //ждем завершение фазы 0
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
    }
}

class PhaserThread_n implements Runnable{

    Phaser phaser;

    String name;
    
    PhaserThread_n(Phaser p, String n){

        this.phaser = p;
        this.name = n;

        phaser.register();
    }

    public void run(){

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
         
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты 
    }
}
