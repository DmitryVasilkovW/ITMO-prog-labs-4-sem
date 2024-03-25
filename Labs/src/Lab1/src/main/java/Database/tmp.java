package Database;

public class tmp
{
    private static Initial _initilal = new  Initial();

    public static void up()
    {
        _initilal.GetUpSql();
    }

    public static void down()
    {
        _initilal.GetDownSql();
    }

    public static void main(String[] args)
    {
        up();
    }
}
