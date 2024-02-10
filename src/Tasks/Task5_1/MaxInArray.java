package Tasks.Task5_1;

public class MaxInArray
{
    public static int Max(int[] array)
    {
        int max = -239;

        for (int num : array)
        {
            if (num > max)
            {
                max = num;
            }
        }

        return max;
    }
}
