package Accounts.Models;

import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;

public abstract class AccountBase
{
    @Getter
    protected BigDecimal _balance;
    @Getter
    protected Integer _id;

    public AccountBase(Integer id, BigDecimal _balance)
    {
        this._balance = _balance;
        _id = id;
    }

    public abstract void ReplenishmentOfFunds(BigDecimal amount);

    public abstract BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException;
}