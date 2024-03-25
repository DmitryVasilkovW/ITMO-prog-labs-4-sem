package Bank.Entities;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DebitAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import Accounts.Models.IInterestBearingAccount;
import Database.Repositories.AccountRepository;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;
import lombok.Getter;

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
    private BigDecimal _interestRate;
    @Getter
    private BigDecimal _commission;
    @Getter
    private Map<Integer, AccountBase> _accounts;

    public Bank(String name, BigDecimal interestRate, BigDecimal commission, Map<String, User> users, AccountRepository accountRepository)
    {
        _commission = commission;
        _interestRate = interestRate;
        _name = name;
        _accountRepository = accountRepository;
        _accounts = new HashMap<>();

        for (Map.Entry<String, User> entry : users.entrySet())
        {
            String password = entry.getKey();
            User user = entry.getValue();

            AccountBase account = _accountRepository.GetAccount(user, password);

            _accounts.put(account.get_id(), account);
        }
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
