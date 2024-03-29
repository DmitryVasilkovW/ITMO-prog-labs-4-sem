package InteractiveMenu.Services.Scenarios;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DebitAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import Database.AppConfig;
import Database.Repositories.AccountRepository;
import Database.Repositories.UserRepository;
import MyExceptions.IncorrectArgumentException;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountScenario
{

    private final AccountRepository _repository;
    private final UserRepository _userRepository;

    public AccountScenario()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(AccountRepository.class);
        context.register(UserRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        _repository = context.getBean(AccountRepository.class);
        _userRepository = context.getBean(UserRepository.class);
    }

    public BigDecimal Withdrawal(BigDecimal amount, Integer accountId, String name, String surname, String password) throws ShortageOfFundsException
    {
        if (_userRepository.CheckIfUserCanWithdraw(name, surname, password, accountId, amount) && _userRepository.CheckPassword(name, surname, password))
        {
            return _repository.Withdrawal(amount, accountId);
        }

        return null;
    }

    public BigDecimal GetBalance(Integer accountId, String name, String surname, String password)
    {
        if (_userRepository.CheckPassword(name, surname, password) && _userRepository.CheckIfUserCanUseThisAccount(name, surname, password, accountId))
        {
            return _repository.GetAccountBalance(accountId);
        }

        return null;
    }

    public void ReplenishmentOfFunds(BigDecimal amount, Integer accountId, String name, String surname, String password) throws IncorrectArgumentException
    {
        if (_userRepository.CheckPassword(name, surname, password))
        {
            _repository.ReplenishmentOfFunds(amount, accountId);

            return;
        }

        throw new IncorrectArgumentException();
    }

    public List<AccountBase> GetAccountsByUser(@NotNull User user, String password)
    {
        if (_userRepository.CheckPassword(user.get_name(), user.get_surname(), password))
        {
            return _repository.GetAccountsByUser(user, password);
        }

        return null;
    }


    public ArrayList<ArrayList<String>> GetAccountInfo(@NotNull User user, String password)
    {
        if (_userRepository.CheckPassword(user.get_name(), user.get_surname(), password))
        {
            var info = new ArrayList<ArrayList<String>>();
            var accounts = _repository.GetAccountsByUser(user, password);
            int i = 0;

            for (; i < accounts.size(); i++)
            {
                var list = new ArrayList<String>();

                list.add("Account type: " + accounts.get(i).getClass().getSimpleName());
                list.add("Account id: " + accounts.get(i).get_id().toString());
                list.add("Balance: " + accounts.get(i).get_balance().toString());

                if (accounts.get(i) instanceof CreditAccount)
                {
                    list.add("Credit limit: " + ((CreditAccount)accounts.get(i)).get_creditLimit().toString());
                    list.add("Commission: " + ((CreditAccount)accounts.get(i)).get_commission().toString());
                }

                else if (accounts.get(i) instanceof DebitAccount)
                {
                    list.add("Interest timer: " + ((DebitAccount)accounts.get(i)).get_interestTimer() != null ? "Timer is active" : "Timer is not active");
                }

                else if (accounts.get(i) instanceof DepositAccount)
                {
                    list.add("Deposit end date: " + ((DepositAccount)accounts.get(i)).get_depositEndDate().toString());
                }

                info.add(list);
            }

            return info;
        }

        return null;
    }
}
