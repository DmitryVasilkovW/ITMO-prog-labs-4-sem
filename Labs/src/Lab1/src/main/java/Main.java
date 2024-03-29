import Bank.Entities.Bank;
import Bank.Entities.CentralBank;
import Database.AppConfig;
import Database.Repositories.BankRepository;
import Database.Repositories.UserRepository;
import MyExceptions.ShortageOfFundsException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{

    public static void main(String[] args) throws ShortageOfFundsException
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(BankRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var _bankRepository = context.getBean(BankRepository.class);

        var _centralBank = new CentralBank();

        var banks = _bankRepository.GetAllBanks("1");

        for (Bank bank : banks)
        {
            _centralBank.AddBank(bank);
        }

        System.out.println(_centralBank.GetInfoAboutAccounts("a", "a", "1"));
    }
}
