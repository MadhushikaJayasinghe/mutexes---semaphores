class Rider extends Thread {


    public static int waiting = 0;
    private int RiderId;

    Rider(int RiderId) {
        this.RiderId = RiderId;
    }

    public void run() {

        Main.mutex.lock();
        Rider.waiting += 1;

        Main.mutex.unlock();

        System.out.println("Rider " + RiderId + " arrived");

        try {
            Main.busSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Rider " + RiderId + " boarded to bus " + Bus.currentBus.busId );

        Main.boardedSemaphore.release();

    }
}