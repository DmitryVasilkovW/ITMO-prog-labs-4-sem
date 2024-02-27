package Practice3.Task3_1;

import Practice2.Task1_2.Category;
import Practice2.Task2_2.FoodItem;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {

        String line = "Конфеты ’Маска’;45;120";


        String[] item_fld;


        item_fld = line.split(";");


        int productid = 1;
        Category category = Category.Food;
        Date dateOfIncome = new Date();
        short expires = 10;

        var foodItem = new FoodItem(productid, item_fld[0], Double.parseDouble(item_fld[1]), category, dateOfIncome, expires);
        foodItem.PrintAll();
    }
}
