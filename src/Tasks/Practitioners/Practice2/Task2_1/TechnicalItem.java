package Tasks.Practitioners.Practice2.Task2_1;

import Tasks.Practitioners.Practice2.Task1_2.Category;

public class TechnicalItem extends GenericItemBase
{
    short _warrantyTime;

    TechnicalItem(int productid, String name, double price, Category category, short warrantyTime)
    {
        super(productid, name, price, category);
        _warrantyTime = warrantyTime;
    }

    @Override
    public void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f, warranty time %d \n", _productid, _name, _price, _warrantyTime);
    }
}
