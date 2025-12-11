package THREAD;

import org.w3c.dom.css.Counter;

// // Здесь определен класс CommonResource, который представляет общий ресурс и в котором определено
// // одно целочисленное поле x.
// // Этот ресурс используется классом потока CountThread. Этот класс просто увеличивает в цикле значение x на единицу.
// // Причем при входе в поток значение x=1:
// // 1 res.x=1;
// // То есть в итоге мы ожидаем, что после выполнения цикла res.x будет равно 4.
// // В главном классе программы запускается пять потоков. То есть мы ожидаем, что каждый
// // поток будет увеличивать значение res.x с 1 до 4 и так пять раз. Но если мы посмотрим
// // на результат работы программы, то он будет иным:
// public class MySynchronized {
    
//     public static void main(String[] args) {
        
//         CommonResurce commonResurce = new CommonResurce();

//         for(int i = 1; i < 6; i++)
//             new Thread(new CountThread(commonResurce), "Thread " + i).start();
//     }
// }

// class CommonResurce{

//     int x = 0;
// }

// class CountThread implements Runnable{

//     CommonResurce res;

//     CountThread(CommonResurce res){

//         this.res = res;
//     }

//     public void run(){

//         res.x = 1;

//         for(int i = 1; i < 5; i++)
//         {
//             System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
//             res.x++;

//             try {
//                 Thread.sleep(100);
//             } catch (InterruptedException _) {}

//         }
//     }

// }




//Упорядочим код выше с помощью synhronized блоков
public class MySynchronized {
  
    public static void main(String[] args) {
          
        CommonResource commonResource= new CommonResource();
        for (int i = 1; i < 6; i++){
              
            new Thread(new CountThread(commonResource), "Thread "+ i).start();
        }
    }
}
  
class CommonResource{
      
    int x=0;
}
  
class CountThread implements Runnable{
  
    CommonResource res;
 
    CountThread(CommonResource res){ this.res = res; }
 
    public void run(){
 
        synchronized(res){  // определяем синхронизированный блок
 
            // до конца блок идет критическая секция
            res.x = 1;
 
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
 
                res.x++;
 
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException _){}
            }
        }
    }
}