package Tasks.Practitioners.Practice3.Task2_1;

public class NumberArray
{
    public static double sum(Number[] numbers)
    {
        double sum = 0.0;

        for (Number number : numbers)
        {
            sum += number.doubleValue();
        }

        return sum;
    }
}

