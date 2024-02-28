package Practice6.Task6_1.client;

import Practice6.Task6_1.stocklist.ItemCatalog;
import Practice6.Task6_1.MyExceptions.CatalogLoadException;

public interface ICatalogLoader
{
    void Load(ItemCatalog catalog) throws CatalogLoadException;
}
