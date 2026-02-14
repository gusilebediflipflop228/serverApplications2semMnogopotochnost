package SA;

public class exercise1 {
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
}