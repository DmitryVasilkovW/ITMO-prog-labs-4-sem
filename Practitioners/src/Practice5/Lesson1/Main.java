package Practice5.Lesson1;

public class Main
{
    public static void main(String[] args)
    {
        String s = "Hello, World!";
        System.out.println(new InstanceChecker().StringInstanceChecker(s));

        Integer i = 123;

        System.out.println(new InstanceChecker().IntegerInstanceChecker(i));

        System.out.println(new InstanceChecker().IntegerInstanceChecker(s));

        System.out.println(new InstanceChecker().IntegerInstanceChecker(null));
    }
}


