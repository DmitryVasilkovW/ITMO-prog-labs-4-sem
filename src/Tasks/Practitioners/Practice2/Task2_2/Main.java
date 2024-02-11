package Tasks.Practitioners.Practice2.Task2_2;

import Tasks.Practitioners.Practice2.Task1_2.Category;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        var FirstFood = new FoodItem(239, "First", 2239, Category.Food, new Date(), (short) 239);
        var SecondFood = new FoodItem(238, "First", 2239, Category.Food, new Date(), (short) 239);
        var ThirdFood = new FoodItem(239, "First", 2239, Category.Food, new Date(), (short) 239);

        if (FirstFood.equals(SecondFood))
        {
            System.out.println(FirstFood.getClass().getSimpleName() + " is equal to " + SecondFood.getClass().getSimpleName());
        }

        if (FirstFood.equals(ThirdFood))
        {
            System.out.println(FirstFood.getClass().getSimpleName() + " is equal to " + ThirdFood.getClass().getSimpleName());
        }

        var Clone = FirstFood.clone();

        System.out.println(Clone.equals(FirstFood));
        System.out.println(Clone.toString());
    }
}
