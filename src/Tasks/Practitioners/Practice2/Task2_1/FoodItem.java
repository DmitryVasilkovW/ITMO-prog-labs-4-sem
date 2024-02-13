package Tasks.Practitioners.Practice2.Task2_1;

import Tasks.Practitioners.Practice2.Task1_2.Category;
import java.util.Date;

public class FoodItem extends GenericItemBase
{

    private Date _dateOfIncome;
    private short _expires;

    FoodItem(int productid, String name, double price, Category category, Date dateOfIncome, short expires)
    {
        super(productid, name, price, category);
        _dateOfIncome = dateOfIncome;
        _expires = expires;
    }

    @Override
    public void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f, date of income %s, expires %d \n", _productid, _name, _price, _dateOfIncome, _expires);
    }

}
