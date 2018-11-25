class Rider extends Thread {

    public static int waiting = 0;
    private int RiderId;

    Rider(int RiderId) {
        this.RiderId = RiderId;
    }

    public void run() {
        try {
            Main.mutex.acquire();
            Rider.waiting += 1;
            Main.mutex.release();

            System.out.println("Rider " + RiderId + " arrived");
            Main.busSemaphore.acquire();

            System.out.println("Rider " + RiderId + " boarded to bus " + Bus.busId);
            Main.boardedSemaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}