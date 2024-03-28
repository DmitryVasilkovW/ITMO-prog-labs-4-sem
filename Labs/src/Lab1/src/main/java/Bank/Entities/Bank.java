package Bank.Entities;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DebitAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import Accounts.Models.IInterestBearingAccount;
import Database.AppConfig;
import Database.Repositories.AccountRepository;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank implements AutoCloseable
{
    private final AccountRepository _accountRepository;
    @Getter
    private String _name;
    @Getter
    private Integer _id;
    @Getter
    private BigDecimal _interestRate;
    @Getter
    private BigDecimal _commission;
    @Getter
    private HashMap<Integer, AccountBase> _accounts;
    @Nullable
    public HashMap<String, User> _users;

    public Bank(Integer id, String name, BigDecimal interestRate, BigDecimal commission, @Nullable HashMap<String, User> users)
    {
        _commission = commission;
        _interestRate = interestRate;
        _name = name;
        _id = id;
        _accounts = new HashMap<>();

        var context = new AnnotationConfigApplicationContext();

        context.register(AccountRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        _accountRepository = context.getBean(AccountRepository.class);
    }

    public User GetUserByPasswordAndFullName(String name, String surname, String password)
    {
        assert _users != null;
        User user = _users.get(password);

        if (user != null)
        {
            if (user.get_name().equals(name) && user.get_surname().equals(surname))
            {
                return user;
            }
            else
            {
                for (User tempuser : _users.values())
                {
                    if (tempuser.get_name().equals(name) && tempuser.get_surname().equals(surname))
                    {
                        return tempuser;
                    }
                }
            }
        }

        return null;
    }

    public void ActivateBank(HashMap<String, User> users)
    {
        _users = users;

        for (Map.Entry<String, User> entry : users.entrySet())
        {
            String password = entry.getKey();
            User user = entry.getValue();
            var accounts = _accountRepository.GetAccountsByUser(user, password);

            for (AccountBase acc : accounts)
            {
                _accounts.put(acc.get_id(), acc);
            }
        }
    }

    public BigDecimal GetBalance(Integer id)
    {
        return _accounts.get(id).get_balance();
    }

    @Override
    public void close()
    {
        for (AccountBase account : _accounts.values())
        {
            _accountRepository.SaveAccountBalance(account);
        }
    }

    public List<Integer> GetAllAccountIds()
    {
        var ids = new ArrayList<Integer>();

        for (AccountBase account : _accounts.values())
        {
            ids.add(account.get_id());
        }

        return ids;
    }

    public void AddAccount(Integer accountId, AccountBase account)
    {
        _accounts.put(accountId, account);
    }

    public void Deposit(Integer accountId, BigDecimal amount)
    {
        AccountBase account = _accounts.get(accountId);
        account.ReplenishmentOfFunds(amount);
    }

    public BigDecimal Withdraw(Integer accountId, BigDecimal amount) throws ShortageOfFundsException
    {
        AccountBase account = _accounts.get(accountId);

        return account.Withdrawal(amount);
    }

    public void ApplyInterestOrCommission()
    {
        for (AccountBase account : _accounts.values())
        {
            if (account instanceof DepositAccount || account instanceof DebitAccount)
            {
                ((IInterestBearingAccount) account).ApplyInterest(_interestRate);
            }
            else if (account instanceof CreditAccount)
            {
                ((CreditAccount) account).applyCommission(_commission);
            }
        }
    }
}
