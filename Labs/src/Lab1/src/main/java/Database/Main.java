package Database;

import Database.Repositories.AccountRepository;
import Database.Repositories.UserRepository;
import MyExceptions.ShortageOfFundsException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main
{

    public static void adduser()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(UserRepository.class);
        rep.AddUser("tmp", "tmp", "2392281488");
    }

    public static void add()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(UserRepository.class);

        rep.AddAddress("a", "239", 239, 1488, "2392281488");
        rep.AddPassportDetails(239239, 228, "2392281488");
    }

    public static void addac()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(AccountRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(AccountRepository.class);

        rep.AddCreditAccount(1, new BigDecimal("239"), new BigDecimal("239"), new BigDecimal("1488"));
    }

    public static void main(String[] args) throws ShortageOfFundsException
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(AccountRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(UserRepository.class);
        var repp = context.getBean(AccountRepository.class);

        var user = rep.GetUserByPassword("2392281488");

        repp.Withdrawal(new BigDecimal("1"), 1);

        var acc = repp.GetAccount(user, "2392281488");

        System.out.println(acc.get_balance() + " " + acc.get_id());
    }
}
