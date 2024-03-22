package Accounts.Entities;

import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;

import java.math.BigDecimal;

public class CreditAccount extends AccountBase
{
    private BigDecimal creditLimit;
    private BigDecimal commission;

    public CreditAccount(BigDecimal _balance, BigDecimal creditLimit, BigDecimal commission)
    {
        super(_balance);
        this.creditLimit = creditLimit;
        this.commission = commission;
    }

    @Override
    public void ReplenishmentOfFunds(BigDecimal amount)
    {
        this._balance = this._balance.add(amount);
    }

    @Override
    public BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException
    {
        BigDecimal newBalance = this._balance.subtract(amount);
        if (newBalance.compareTo(this.creditLimit.negate()) < 0)
        {
            throw new ShortageOfFundsException("Credit limit exceeded");
        }
        if (newBalance.compareTo(BigDecimal.ZERO) < 0)
        {
            this._balance = newBalance.subtract(this.commission);
        }
        else
        {
            this._balance = newBalance;
        }
        return amount;
    }
}

