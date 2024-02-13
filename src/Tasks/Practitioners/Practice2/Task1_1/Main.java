package Tasks.Practitioners.Practice2.Task1_1;

public class Main
{
    public static void main(String[] args)
    {
        var FirstItem = new GenericItem(239, "First", 2.339);
        var SecondItem = new GenericItem(2339, "Second", 2.399);
        var ThirdItem = new GenericItem(2239, "Third", 2222.33399);

        GenericItem[] arrayOfItems = new GenericItem[3];

        arrayOfItems[0] = FirstItem;
        arrayOfItems[1] = SecondItem;
        arrayOfItems[2] = ThirdItem;

        for (GenericItem item : arrayOfItems)
        {
            item.PrintAll();
        }

        var Analog = new AnalogForFirstItem("AnalogForFirstItem");

        FirstItem.AddNewAnalog(Analog);

        FirstItem.PrintAnalogs();
    }
}
