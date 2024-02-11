package Tasks.Practitioners.Practice3.Task2_1;

public class U0901WorkArray<T extends Number>
{
    private T[] arrNums;

    public U0901WorkArray(T[] numP)
    {
        arrNums = numP;
    }

    public double sum()
    {
        double doubleWork = 0;
        for (T num : arrNums)
        {
            doubleWork += num.doubleValue();
        }

        return doubleWork;
    }
}
