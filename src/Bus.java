class Bus extends Thread {

    public static int busId;

    Bus(int busId) {
        this.busId = busId;
    }

    public void run() {

        try {
            Main.mutex.acquire();
            System.out.println("Bus " + busId + " arrived");

            int n = Math.min(Rider.waiting, 50);
            for (int i = 0; i < n; i++) {
                Main.busSemaphore.release();
                Main.boardedSemaphore.acquire();
            }

            Rider.waiting = Math.max(Rider.waiting - 50, 0);

            System.out.println("Bus " + busId + " departed");
            Main.mutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}