package sea.top.imooc.threadpool;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2020/2/14 22:15
 */
public class Demo1ThreadPollTest {
    public static void main(String[] args) throws InterruptedException {
        // 1 使用循环来模拟很多的用户请求的场景
        // word转化为PDF非常的耗时间
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(()->{
                System.out.println("start");
                try {
                    Thread.sleep(1000*30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end");
            });
//            new Thread(()->{
//                System.out.println("start");
//                try {
//                    Thread.sleep(1000*30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("end");
//            }).start();


        }
        Thread.sleep(1000L*1000);
    }
}
