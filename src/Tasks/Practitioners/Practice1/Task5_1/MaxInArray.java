package Tasks.Practitioners.Practice1.Task5_1;

public class MaxInArray
{
    public static int Max(int[] array)
    {
        int max = Integer.MIN_VALUE;

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
