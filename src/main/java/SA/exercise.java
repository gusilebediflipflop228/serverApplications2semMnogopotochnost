package SA;

import java.util.ArrayList;
import java.util.List;

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

    public static void task4(){
        List<Integer> list = new ArrayList<>();

        AddingThread addingThread = new AddingThread(list);
        RemovingThread removingThread = new RemovingThread(list);

        Thread thread1 = new Thread(addingThread);
        Thread thread2 = new Thread(removingThread);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Размер списка в конце: " + list.size());
    }

    public static void task5(){
        ChangedList changedList = new ChangedList();

        ChangedThreads thread1 = new ChangedThreads(changedList, true);
        ChangedThreads thread2 = new ChangedThreads(changedList, false);

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Размер списка: " + changedList.getSize());
    }
}