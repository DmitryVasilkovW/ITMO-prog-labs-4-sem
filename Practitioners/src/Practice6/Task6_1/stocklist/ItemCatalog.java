package Practice6.Task6_1.stocklist;

import Practice4.Task4_2.IItem;
import Practice6.Task6_1.MyExceptions.ItemAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemCatalog
{
    private HashMap<Integer, IItem> _catalog = new HashMap<>();
    private ArrayList<IItem> _aLCatalog = new ArrayList<>();

    public void AddItem(IItem item) throws ItemAlreadyExistsException
    {
        if (_catalog.containsKey(item.GetId()))
        {
            throw new ItemAlreadyExistsException("Item with ID " + item.GetId() + " already exists in the catalog");
        }
        else
        {
            _catalog.put(item.GetId(), item);
            _aLCatalog.add(item);
        }
    }

    public void PrintItems()
    {
        for(IItem i : _aLCatalog)
        {
            System.out.println(i.getClass().getSimpleName() + i.toString() + " hash = " + i.hashCode());
        }
    }

    public IItem FindItemByID(int id)
    {
        if(!_catalog.containsKey(id))
        {
            return null;
        }
        else
        {
            return _catalog.get(id);
        }
    }

    public IItem findItemByIDAL(int id)
    {
        for(IItem i : _aLCatalog)
        {
            if(i.GetId() == id) return i;
        }

        return null;
    }
}
