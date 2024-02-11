package Tasks.Practitioners.Practice2.Task2_2;

import Tasks.Practitioners.Practice2.Task1_2.Category;
import Tasks.Practitioners.Practice2.Task2_1.GenericItemBase;

public class TechnicalItem extends GenericItemBase implements Cloneable
{
    short _warrantyTime;

    TechnicalItem(int productid, String name, double price, Category category, short warrantyTime)
    {
        super(productid, name, price, category);
        _warrantyTime = warrantyTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalItem that = (TechnicalItem) o;
        return _productid == that._productid &&
                Double.compare(that._price, _price) == 0 &&
                _warrantyTime == that._warrantyTime &&
                _name.equals(that._name) &&
                _category == that._category;
    }

    @Override
    public TechnicalItem clone()
    {
        return (TechnicalItem) super.clone();
    }

    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, Price: %.2f, Category: %s, Warranty Time: %d days",
                _productid, _name, _price, _category.name(), _warrantyTime);
    }


    @Override
    public void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f, warranty time %d \n", _productid, _name, _price, _warrantyTime);
    }
}
