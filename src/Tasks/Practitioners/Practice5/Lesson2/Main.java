package Tasks.Practitioners.Practice5.Lesson2;

import java.math.BigDecimal;

public class Main
{

    public static void main(String[] args)
    {
        BigDecimal num1 = new BigDecimal("10");
        BigDecimal num2 = new BigDecimal("10");

        boolean sameMemoryLocation = num1 == num2;
        System.out.println("Do the variables point to the same location in memory: " + sameMemoryLocation);



        boolean equals = num1.equals(num2);
        System.out.println("Are the values of the variables equal to: " + equals);

        BigDecimal sum = num1.add(num2);
        BigDecimal div = num1.divide(num2);
        BigDecimal mult = num1.multiply(num2);

        System.out.println("Sum: " + sum);
        System.out.println("Dividing: " + div);
        System.out.println("Multiplication: " + mult);
    }
}
