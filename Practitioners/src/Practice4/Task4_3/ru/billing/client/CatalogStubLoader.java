package Practice4.Task4_3.ru.billing.client;

import Practice4.Task4_2.FoodItem;
import Practice4.Task4_2.ICatalogLoader;
import Practice4.Task4_1.GenericItem;
import Practice4.Task4_1.ItemCatalog;
import Practice4.Task4_2.Category;

import java.util.Date;

public class CatalogStubLoader implements ICatalogLoader
{

    @Override
    public void Load(ItemCatalog catalog)
    {
        GenericItem item1 = new GenericItem("Sony TV", 23000, Category.General);
        FoodItem item2 = new FoodItem("Bread", 12, Category.General, new Date(), (short) 10);
        catalog.AddItem(item1);
        catalog.AddItem(item2);

    }
}