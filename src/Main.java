import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static Semaphore busSemaphore;
    public static Semaphore boardedSemaphore;
    public static Semaphore mutex;

    public static void main(String args[]) {

        busSemaphore = new Semaphore(0);
        boardedSemaphore = new Semaphore(0);
        mutex = new Semaphore(1);

        Thread riderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    try {
                        int sleepTime = Main.getRandomNumber(5);
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Rider(count).start();
                    count += 1;

                }
            }
        });


        Thread busThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    try {
                        Thread.sleep(Main.getRandomNumber(200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Bus(count).start();
                    count += 1;

                }
            }
        });

        riderThread.start();
        busThread.start();
    }


    public static int getRandomNumber(int mean) {
        Random random = new Random();
        return (int) (Math.log(1 - random.nextDouble()) * 100 * mean * (-1));
    }

}



