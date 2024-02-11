package Tasks.Task3;

public class NumberChanger
{
    public static long NumberChangeInTheRange(long start, long end)
    {
        long var = 0;

        for (long i = start; i <= end; i ++)
        {
            var = i;
        }

        return var;
    }

    public static long CycleTimeCalculation(long start, long finish)
    {
        long begin = new java.util.Date().getTime();

        for (long i = start; i <= finish; i++) {}

        long end = new java.util.Date().getTime();

        return (end - begin);
    }

    public static long CycleTimeCalculation(int start, int finish)
    {
        long begin = new java.util.Date().getTime();


        for (int i = start; i <= finish; i++) {}

        long end = new java.util.Date().getTime();

        return (end - begin);
    }
}
