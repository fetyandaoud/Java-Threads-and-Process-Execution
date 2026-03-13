public class Stopwatch implements Runnable {
    @Override
    public void run() {
        try {
            long startTime=System.currentTimeMillis();
            while (true) {
                long elapsedTime=System.currentTimeMillis() - startTime;
                double seconds=elapsedTime / 1000.0;
                System.out.printf("Stopwatch thread. Elapsed: %.2f seconds.\n", seconds);
                if (seconds >= 60) {
                    break;
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Stopwatch interrupted.");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread. Waiting for stopwatch thread...");
        Thread stopwatchThread=new Thread(new Stopwatch());
        stopwatchThread.start();
        stopwatchThread.join();
        System.out.println("Main thread. Finished stopwatch thread.");
    }
}
