package Tasks.Practitioners.Practice3.Task2_1;

public class Main
{
    public static void main(String[] args)
    {

        Integer[] intArr = {10, 20, 15};


        Float[] floatArr = new Float[10];
        for (int i = 0; i < floatArr.length; i++)
        {
            floatArr[i] = (float) Math.random();
        }


        U0901WorkArray<Integer> insWorkArrayInt = new U0901WorkArray<>(intArr);
        System.out.println("Сумма Integer: " + insWorkArrayInt.sum());


        U0901WorkArray<Float> insWorkArrayFloat = new U0901WorkArray<>(floatArr);
        System.out.println("Сумма Float: " + insWorkArrayFloat.sum());


        //String[] strArr = {"1", "2", "3"};
        //U0901WorkArray<String> insWorkArrayStr = new U0901WorkArray<>(strArr);
    }
}
