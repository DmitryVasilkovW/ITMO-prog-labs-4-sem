package Practice1.Task5_2;

public class Transposer
{
    public static void Transpose(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = i + 1; j < matrix[i].length; j++)
            {

                int temp = matrix[i][j];

                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
