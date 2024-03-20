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

    public void ReplenishmentOfFunds(BigDecimal amount)
    {
        _balance = _balance.add(amount);
    }

    public BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException
    {
        if (_balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0)
        {
            _balance = _balance.subtract(amount);

            return amount;
        }

        throw new ShortageOfFundsException();
    }
}