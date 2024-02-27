package Practice1.Task5_2;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        int[][] matrix = new int[3][3];

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = (int) Math.round(Math.random() * 10);
            }
        }

        for (int i = 0; i < matrix.length; i++)
        {
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println();

        Transposer transposer = new Transposer();

        transposer.Transpose(matrix);

        for (int i = 0; i < matrix.length; i++)
        {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
