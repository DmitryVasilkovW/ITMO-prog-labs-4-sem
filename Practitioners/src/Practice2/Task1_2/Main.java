package Practice2.Task1_2;

public class Main
{
    public static void main(String[] args)
    {
        var FirstCategory = Category.General;

        var FirstItem = new GenericItem(239, "FirstItem", 22339, FirstCategory);

        FirstItem.PrintCategory();
    }
}
