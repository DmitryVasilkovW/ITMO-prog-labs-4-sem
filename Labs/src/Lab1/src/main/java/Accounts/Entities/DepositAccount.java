package Accounts.Entities;

import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends AccountBase
{
    @Getter
    private LocalDate _depositEndDate;
    @Getter
    private BigDecimal _interestRate;

    public DepositAccount(Integer id, BigDecimal _balance, LocalDate depositEndDate, BigDecimal interestRate)
    {
        super(id, _balance);
        _depositEndDate = depositEndDate;
        _interestRate = interestRate;
    }

    @Override
    public void ReplenishmentOfFunds(BigDecimal amount)
    {
        this._balance = this._balance.add(amount);
    }

    @Override
    public BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException
    {
        if (LocalDate.now().isBefore(_depositEndDate))
        {
            throw new ShortageOfFundsException("You cannot withdraw money before the end of the deposit period");
        }
        if (this._balance.compareTo(amount) < 0)
        {
            throw new ShortageOfFundsException();
        }

        this._balance = this._balance.subtract(amount);
        return amount;
    }

    public void applyInterest()
    {
        if (ChronoUnit.DAYS.between(LocalDate.now(), _depositEndDate) <= 0)
        {
            BigDecimal interest = _balance.multiply(_interestRate);
            _balance = _balance.add(interest);
        }
    }
}
