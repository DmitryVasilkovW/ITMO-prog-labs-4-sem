package Accounts.Entities;

import Accounts.Models.AccountBase;
import Accounts.Models.IInterestBearingAccount;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends AccountBase implements IInterestBearingAccount
{
    @Getter
    private LocalDate _depositEndDate;

    public DepositAccount(Integer id, BigDecimal _balance, LocalDate depositEndDate)
    {
        super(id, _balance);
        _depositEndDate = depositEndDate;
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

    public void ApplyInterest(BigDecimal bankInterestRate)
    {
        if (ChronoUnit.DAYS.between(LocalDate.now(), _depositEndDate) <= 0)
        {
            BigDecimal interest = _balance.multiply(bankInterestRate);
            _balance = _balance.add(interest);
        }
    }
}
