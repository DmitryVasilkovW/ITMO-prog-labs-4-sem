package Tasks.Practitioners.Practice2.Task1_1;

import java.util.ArrayList;
import java.util.List;

public class GenericItem
{
    private int _productid;
    private String _name;
    private double _price;
    private List<IAnalog> _listOfAnalogs = new ArrayList<>();

    GenericItem(int productid, String name, double price)
    {
        _productid = productid;
        _name = name;
        _price = price;
    }

    GenericItem(int productid, String name, double price, List<IAnalog> listOfAnalogs)
    {
        _productid = productid;
        _name = name;
        _price = price;
        _listOfAnalogs = listOfAnalogs;
    }

    public void PrintAll()
    {
        System.out.printf("ID: %d , Name: %-3s , price:%5.2f \n", _productid, _name, _price);
    }

    public void AddNewAnalog(IAnalog analog)
    {
        if (!_listOfAnalogs.contains(analog))
        {
            _listOfAnalogs.add(analog);
        }
    }

    public void PrintAnalogs()
    {
        System.out.println();

        for (IAnalog analog : _listOfAnalogs)
        {
            System.out.println("Type is " + analog.getClass().getSimpleName() + "Name is " + analog.getName());
        }
    }
}
