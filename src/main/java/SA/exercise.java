package SA;

public class exercise {
    public static void task1() {
        Thread currentThread = Thread.currentThread();

        System.out.println("Имя потока: " + currentThread.getName());
        System.out.println("Id потока: " + currentThread.getId());
        System.out.println("Приоритет потока: " + currentThread.getPriority());
        System.out.println("Состояние потока: " + currentThread.getState());
        System.out.println("Демон или нет: " + currentThread.isDaemon());
        System.out.println("Жив или нет: " + currentThread.isAlive());
        System.out.println("Группа потока: " + currentThread.getThreadGroup());
    }

    public static void task2() {
        System.out.println("Главный поток начал работу: " + Thread.currentThread().getName());

        Thread childThread = new Thread(() -> {
            System.out.println("Дочерний поток начал работу: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Дочерний поток завершил работу");
        });

        childThread.start();
        System.out.println("Главный поток ждет завершения дочернего...");
        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Главный поток продолжил работу и завершается");
    }

    public static void task3() {
        MyRunnable1 run1 = new MyRunnable1();
        MyRunnable2 run2 = new MyRunnable2();
        MyRunnable3 run3 = new MyRunnable3();

        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);
        Thread thread3 = new Thread(run3);
        System.out.println("Главный поток начал работу: " + Thread.currentThread().getName());
        System.out.println("Запуск дочерних потоков");

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Главный поток ждет завершения дочерних потоков");

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Все дочерние потоки завершены. Главный поток продолжил работу и завершается");
    }
}