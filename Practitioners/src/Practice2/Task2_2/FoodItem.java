package Practice2.Task2_2;

import Practice2.Task2_1.GenericItemBase;
import Practice2.Task1_2.Category;

import java.util.Date;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return
                _productid == foodItem._productid &&
                Double.compare(foodItem._price, _price) == 0 &&
                _expires == foodItem._expires &&
                _name.equals(foodItem._name) &&
                _dateOfIncome.equals(foodItem._dateOfIncome) &&
                _category == foodItem._category;
    }

    @Override
    public FoodItem clone()
    {
        FoodItem cloned = (FoodItem) super.clone();
        cloned._dateOfIncome = (Date) this._dateOfIncome.clone();
        return cloned;
    }

    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, Price: %.2f, Category: %s, Date of Income: %s, Expires in days: %d",
                _productid, _name, _price, _category.name(), _dateOfIncome.toString(), _expires);
    }

    @Override
    public void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f, date of income %s, expires %d \n", _productid, _name, _price, _dateOfIncome, _expires);
    }

}
