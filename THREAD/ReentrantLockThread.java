package THREAD;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockThread {
    
    public static void main(String[] args) {
        
        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock();

        for(int i = 1; i < 6; i++){
            new Thread(new CountThread(commonResource, locker), "Thread " + i).start();
        }
    }
}

class CommonResource{

    int x = 0;
}

class CountThread extends Thread{

    CommonResource res;
    ReentrantLock locker;

    CountThread(CommonResource res, ReentrantLock lock){
        this.res = res;
        this.locker = lock;
    }

    public void run(){
        
        locker.lock(); //Устанавливаем блокировку

        try {
            res.x = 1;
            
            for(int i = 1; i < 5; i++){

                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                
                res.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        finally {
            locker.unlock(); //Снимаем блокировку
        }
    }

}
