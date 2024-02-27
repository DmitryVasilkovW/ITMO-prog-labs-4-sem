package Practice5.Lesson2;

import java.math.BigDecimal;

public class MethodsChecksInJava
{
    public static void Check()
    {
        BigDecimal num1 = new BigDecimal("123.45");
        BigDecimal num2 = new BigDecimal("2");

        System.out.println(num1.add(num2));
        System.out.println(num1.divide(num2));
        System.out.println(num1.multiply(num2));
    }
}
