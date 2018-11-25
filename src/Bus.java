class Bus extends Thread {


    public static Bus currentBus = null;
    public static int busId;

    Bus(int busId) {
        this.busId = busId;
    }

    public void run() {

        Main.mutex.lock();
        Bus.currentBus = this;
        System.out.println("Bus " + busId + " arrived");

        int n = Math.min(Rider.waiting, 50);

        for (int i = 0; i < n; i++) {
            Main.busSemaphore.release();
            try {
                Main.boardedSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Rider.waiting = Math.max(Rider.waiting - 50, 0);

        System.out.println("Bus " + busId + " departed");
        Main.mutex.unlock();

    }

}