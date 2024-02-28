package Practice6.Task6_3_1.client;

import Practice6.Task6_1.MyExceptions.CatalogLoadException;
import Practice6.Task6_1.stocklist.ItemCatalog;

public class Main
{

    public static void main(String[] args)
    {
        String fileName = "/Users/dmitryvasilkov/IdeaProjects/ITMO-prog-labs-4-semm/Practitioners/src/Practice6/Task6_3_1/client/items.txt";
        var loader = new CatalogFileLoader(fileName);
        var catalog = new ItemCatalog();

        try
        {
            loader.Load(catalog);
            System.out.println("Catalog loaded successfully:");
            catalog.PrintItems();
        }
        catch (CatalogLoadException e)
        {
            System.err.println("Failed to load catalog: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
