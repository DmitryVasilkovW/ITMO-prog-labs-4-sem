package Accounts.Entities;

import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;

public class CreditAccount extends AccountBase
{
    @Getter
    private BigDecimal _creditLimit;
    @Getter
    private BigDecimal _commission;

    public CreditAccount(Integer id, BigDecimal _balance, BigDecimal creditLimit, BigDecimal commission)
    {
        super(id, _balance);
        this._creditLimit = creditLimit;
        this._commission = commission;
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
        if (newBalance.compareTo(this._creditLimit.negate()) < 0)
        {
            throw new ShortageOfFundsException("Credit limit exceeded");
        }
        if (newBalance.compareTo(BigDecimal.ZERO) < 0)
        {
            this._balance = newBalance.subtract(_commission);
        }
        else
        {
            this._balance = newBalance;
        }
        return amount;
    }

    public void applyCommission()
    {
        if (this._balance.compareTo(BigDecimal.ZERO) < 0)
        {
            this._balance = this._balance.subtract(_commission);
        }
    }

}

