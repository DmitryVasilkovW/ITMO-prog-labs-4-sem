package Accounts.Models;

import MyExceptions.ShortageOfFundsException;

import java.math.BigDecimal;

public abstract class AccountBase
{
    protected BigDecimal _balance;

    public AccountBase(BigDecimal _balance)
    {
        this._balance = _balance;
    }

    public abstract void ReplenishmentOfFunds(BigDecimal amount);

    public abstract BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException;
}