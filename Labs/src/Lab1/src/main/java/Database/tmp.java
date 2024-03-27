package Database;

import InteractiveMenu.Services.Scenarios.AccountScenario;
import Users.Entites.User;

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
        var a = new AccountScenario();

        var b = a.GetAccountInfo(new User("tmp", "tmp", null, null), "2392281488");

        for (var t : b)
        {
            for (var tt : t)
            {
                System.out.println(tt);
            }
        }
    }
}
