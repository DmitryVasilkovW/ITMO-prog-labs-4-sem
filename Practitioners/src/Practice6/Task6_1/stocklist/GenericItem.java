package Practice6.Task6_1.stocklist;

import Practice4.Task4_2.Category;
import Practice4.Task4_2.IItem;

public class GenericItem implements IItem
{
    private int _id;
    private String _name;
    private Category _category;

    public GenericItem(String name, int id, Category category)
    {
        _id = id;
        _name = name;
        _category = category;
    }

    public GenericItem()
    {
        _category = Category.General;
    }

    @Override
    public int GetId()
    {
        return _id;
    }

    @Override
    public String GetName()
    {
        return _name;
    }
    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, Category: %s",
                _id, _name, _category.name());
    }
}
