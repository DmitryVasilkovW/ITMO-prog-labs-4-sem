package Practice2.Task2_1;

import Practice2.Task1_2.Category;

import java.util.ArrayList;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        var FirstFoodItem = new FoodItem(2399, "Food", 22.339, Category.Food, new Date(), (short) 239);
        var FirstTechnicalItem = new TechnicalItem(239, "Technica", 239, Category.Print,  (short) 239);

        var listOfItems = new ArrayList<GenericItemBase>();

        listOfItems.add(FirstFoodItem);
        listOfItems.add(FirstTechnicalItem);

        for (GenericItemBase item : listOfItems)
        {
            item.PrintAll();
        }
    }
}
