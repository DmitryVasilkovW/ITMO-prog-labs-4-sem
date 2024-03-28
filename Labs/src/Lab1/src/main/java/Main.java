import Bank.Entities.CentralBank;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;

import java.math.BigDecimal;
import java.util.HashMap;

public class Main
{

    public static void main(String[] args) throws ShortageOfFundsException
    {
        var centralBank = new CentralBank();
        var user = new User("tmp", "tmp", null, null);
        var users = new HashMap<String, User>();

        users.put("2392281488", user);

        centralBank.Withdraw("Pablo bank", 1, new BigDecimal("1"));
    }
}
