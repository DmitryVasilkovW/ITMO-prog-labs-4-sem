package Tasks.Practitioners.Practice3.Task1_1;

import Tasks.Practitioners.Practice2.Task1_1.IAnalog;
import Tasks.Practitioners.Practice2.Task1_2.Category;
import Tasks.Practitioners.Practice2.Task2_1.GenericItemBase;

import java.util.Date;
import java.util.List;

public class FoodItem extends GenericItemBase implements Cloneable
{

    private Date _dateOfIncome;
    private short _expires;

    public FoodItem(int productid, String name, double price, Category category, Date dateOfIncome, short expires)
    {
        super(productid, name, price, category);
        _dateOfIncome = dateOfIncome;
        _expires = expires;
    }

    public FoodItem(String name, float price, List<IAnalog> analogs, Date date, short expires)
    {
        super(name, price, analogs);
        _dateOfIncome = date;
        _expires = expires;

    }
    public FoodItem(String name, float price, short expires)
    {
        super(name);
        _price = price;
        _expires = expires;

    }
    public FoodItem(String name)
    {
        super(name);
    }


    @Override
    public void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f, date of income %s, expires %d \n", _productid, _name, _price, _dateOfIncome, _expires);
    }

}

