package javalamda;

public class LambdaWithThread {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Dilanka");
            }
        }.start();

        new Thread(() -> {
            System.out.println("Nimsara");
        }).start();

        Runnable runnable = () -> {
            System.out.println("sss");
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
