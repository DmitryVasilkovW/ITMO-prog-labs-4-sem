package Tasks.Practitioners.Practice4.Task4_2;

import Tasks.Practitioners.Practice4.Task4_1.GenericItem;
import Tasks.Practitioners.Practice4.Task4_1.ItemCatalog;

public class Main
{

    public static void main(String[] args)
    {
        var catalog = new ItemCatalog();

        var loader = new CatalogStubLoader();
        loader.Load(catalog);

        catalog.PrintItems();
    }
}
