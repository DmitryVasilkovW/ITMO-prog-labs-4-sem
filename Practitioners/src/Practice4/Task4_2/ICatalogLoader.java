package Practice4.Task4_2;

import Practice4.Task4_1.ItemCatalog;
import Practice6.Task6_1.MyExceptions.CatalogLoadException;

public interface ICatalogLoader
{
    void Load(ItemCatalog catalog) throws CatalogLoadException;
}
