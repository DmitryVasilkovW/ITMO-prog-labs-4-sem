package Practice6.Task6_2.sync;

public class Main
{
    public static void main(String[] args)
    {
        Bank bankMain = new Bank();

        U1901Thread threadOne = new U1901Thread(bankMain, 100, 2000);
        threadOne.setName("ThreadOne");
        threadOne.setPriority(Thread.MAX_PRIORITY);
        threadOne.start();

        U1901Thread threadTwo = new U1901Thread(bankMain, 50, 300);
        threadTwo.setName("ThreadTwo");
        threadTwo.setPriority(Thread.MAX_PRIORITY);
        threadTwo.start();

        System.out.println("Current Thread: " + Thread.currentThread().getName());
    }
}
