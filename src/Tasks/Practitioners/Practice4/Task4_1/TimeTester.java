package Tasks.Practitioners.Practice4.Task4_1;

import Tasks.Practitioners.Practice4.Task4_1.ItemCatalog;

import java.util.Date;

public class TimeTester
{
    public void Test(ItemCatalog cat)
    {
        long begin = new Date().getTime();

        for(int i = 0; i < 100000; i++)
            cat.FindItemByID(10);

        long end = new Date().getTime();
        System.out.println("In HashMap: "+(end - begin)); begin = new Date().getTime();

        for(int i = 0; i < 100000; i++)
            cat.findItemByIDAL(10);

        end = new Date().getTime();
        System.out.println("In ArrayList: " + (end - begin));
    }
}
