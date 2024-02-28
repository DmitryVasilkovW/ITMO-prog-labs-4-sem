package Practice6.Task6_2.sync;

public class Bank
{
    int _intTo = 0;
    int _intFrom = 220;

    synchronized void calc(int intTransaction, long lngTimeout)
    {
        System.out.println("Before: " + Thread.currentThread().getName() + ", intTo: " + _intTo + ", intFrom: " + _intFrom);
        _intFrom -= intTransaction;

        try
        {
            Thread.sleep(lngTimeout);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        _intTo += intTransaction;
        System.out.println("After: " + Thread.currentThread().getName() + ", intTo: " + _intTo + ", intFrom: " + _intFrom);
    }

}
