package Practice4.Task4_1;

public class Main
{

    public static void main(String[] args)
    {
        var catalog = new ItemCatalog();

        for (int i = 0; i < 11; i ++)
        {
            catalog.AddItem(new GenericItem());
        }

        var test = new TimeTester();

        test.Test(catalog);
    }
}
