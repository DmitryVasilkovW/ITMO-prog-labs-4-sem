package Bank.Entities;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Bank
{
    private String name;
    private Map<String, AccountBase> accounts;

    public Bank(String name)
    {
        this.name = name;
        this.accounts = new HashMap<>();
    }

    public String getName() { return name;}

    public void createAccount(String accountId, AccountBase account)
    {
        accounts.put(accountId, account);
    }

    public void deposit(String accountId, BigDecimal amount)
    {
        AccountBase account = accounts.get(accountId);
        account.ReplenishmentOfFunds(amount);
    }

    public void withdraw(String accountId, BigDecimal amount) throws ShortageOfFundsException
    {
        AccountBase account = accounts.get(accountId);
        account.Withdrawal(amount);
    }

    public void applyInterestOrCommission()
    {
        for (AccountBase account : accounts.values())
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
