package Practice6.Task6_1.stocklist;

import Practice4.Task4_2.Category;
import Practice4.Task4_2.IItem;

import java.util.Date;

public class FoodItem implements IItem
{
    private String _name;
    private int _id;
    private Category _category;
    private Date _date;
    private short _price;
    private float _expires;

    public FoodItem(String name, int id, Category category, Date date, short price, float expires)
    {
        _name = name;
        _id = id;
        _category = category;
        _date = date;
        _price = price;
        _expires = expires;
    }

    @Override
    public String GetName()
    {
        return _name;
    }

    @Override
    public int GetId()
    {
        return _id;
    }

    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, Price: %d, Category: %s, Warranty Time: %s days",
                _id, _name, _price, _category.name(), _date);
    }
}
