package Practice6.Task6_1.client;

import Practice4.Task4_1.GenericItem;
import Practice6.Task6_1.stocklist.ItemCatalog ;
import Practice4.Task4_2.Category;
import Practice4.Task4_2.FoodItem;
import Practice6.Task6_1.MyExceptions.CatalogLoadException;
import Practice6.Task6_1.MyExceptions.ItemAlreadyExistsException;

import java.util.Date;

public class CatalogStubLoader implements ICatalogLoader
{
    @Override
    public void Load(ItemCatalog catalog) throws CatalogLoadException
    {
        try
        {
            GenericItem item1 = new GenericItem("Sony TV", 23000, Category.General);
            FoodItem item2 = new FoodItem("Bread", 12, Category.General, new Date(), (short) 10);
            catalog.AddItem(item1);
            catalog.AddItem(item2);
            catalog.AddItem(item2);
        }
        catch (ItemAlreadyExistsException e)
        {
            throw new CatalogLoadException("Error loading catalog: " + e.getMessage());
        }
    }
}