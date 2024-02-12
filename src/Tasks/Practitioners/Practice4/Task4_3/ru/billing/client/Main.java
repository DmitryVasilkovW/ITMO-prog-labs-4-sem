package Tasks.Practitioners.Practice4.Task4_3.ru.billing.client;


import Tasks.Practitioners.Practice4.Task4_3.ru.billing.stocklist.GenericItem;

public class Main
{

    public static void main(String[] args)
    {
        var catalogloader = new CatalogStubLoader();
        var item = new GenericItem();

        System.out.println(item.toString());
    }
}
