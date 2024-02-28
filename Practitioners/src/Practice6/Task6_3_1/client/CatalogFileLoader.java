package Practice6.Task6_3_1.client;

import Practice4.Task4_2.Category;
import Practice6.Task6_1.MyExceptions.CatalogLoadException;
import Practice6.Task6_1.MyExceptions.ItemAlreadyExistsException;
import Practice6.Task6_1.client.ICatalogLoader;
import Practice6.Task6_1.stocklist.ItemCatalog;
import Practice6.Task6_1.stocklist.FoodItem ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class CatalogFileLoader extends ItemCatalog implements ICatalogLoader
{
    private String _fileName;

    public CatalogFileLoader(String fileName)
    {
        this._fileName = fileName;
    }

    @Override
    public void Load(ItemCatalog catalog) throws CatalogLoadException
    {
        File f = new File(_fileName);
        FileInputStream fis;
        String line;

        try
        {
            fis = new FileInputStream(f);
            var s = new Scanner(fis);

            while(s.hasNextLine())
            {
                line = s.nextLine();

                if(line.length()==0) break;

                String[] item_fld = line.split(";");
                String name = item_fld[0];

                float price = Float.parseFloat(item_fld[1]);
                short expires = Short.parseShort(item_fld[2]);
                Random rand = new Random();

                var item = new FoodItem(name, rand.nextInt(), Category.None, new Date(), expires, price);

                catalog.AddItem(item);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new CatalogLoadException(e.getMessage());
        }
        catch (ItemAlreadyExistsException e)
        {
            e.printStackTrace();
            throw new CatalogLoadException(e.getMessage());
        }

    }
}
