package Tasks.Practitioners.Practice2.Task1_1;

public class AnalogForFirstItem implements IAnalog
{
    private String _name;

    AnalogForFirstItem(String name)
    {
        _name = name;
    }

    public String getName()
    {
        return _name;
    }
}