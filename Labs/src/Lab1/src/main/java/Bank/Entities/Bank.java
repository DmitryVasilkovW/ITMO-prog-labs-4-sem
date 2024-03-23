package Bank.Entities;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Bank
{
    @Getter
    private String _name;
    private Map<String, AccountBase> _accounts;

    public Bank(String name)
    {
        _name = name;
        _accounts = new HashMap<>();
    }

    public void createAccount(String accountId, AccountBase account)
    {
        _accounts.put(accountId, account);
    }

    public void deposit(String accountId, BigDecimal amount)
    {
        AccountBase account = _accounts.get(accountId);
        account.ReplenishmentOfFunds(amount);
    }

    public void withdraw(String accountId, BigDecimal amount) throws ShortageOfFundsException
    {
        AccountBase account = _accounts.get(accountId);
        account.Withdrawal(amount);
    }

    public void applyInterestOrCommission()
    {
        for (AccountBase account : _accounts.values())
        {
            if (account instanceof DepositAccount)
            {
                ((DepositAccount) account).applyInterest();
            }
            else if (account instanceof CreditAccount)
            {
                ((CreditAccount) account).applyCommission();
            }
        }
    }
}
