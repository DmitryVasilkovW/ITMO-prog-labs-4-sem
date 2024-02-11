package Tasks.Practitioners.Practice2.Task2_1;

import Tasks.Practitioners.Practice2.Task1_1.IAnalog;
import Tasks.Practitioners.Practice2.Task1_2.Category;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericItemBase
{
    protected int _productid;
    protected String _name;
    protected double _price;
    protected List<IAnalog> _listOfAnalogs = new ArrayList<>();
    protected Category _category;

    GenericItemBase(int productid, String name, double price, Category category)
    {
        _productid = productid;
        _name = name;
        _price = price;
        _category = category;
    }

    protected  void PrintAll()
    {
        System.out.printf("ID: %d, Name: %-3s, price:%5.2f \n", _productid, _name, _price);
    }

    protected void AddNewAnalog(IAnalog analog)
    {
        if (!_listOfAnalogs.contains(analog))
        {
            _listOfAnalogs.add(analog);
        }
    }

    protected void PrintCategory()
    {
        System.out.println("Category is " + _category);
    }

    protected void PrintAnalogs()
    {
        System.out.println();

        for (IAnalog analog : _listOfAnalogs)
        {
            System.out.println("Type is " + analog.getClass().getSimpleName() + "Name is " + analog.getName());
        }
    }
}
