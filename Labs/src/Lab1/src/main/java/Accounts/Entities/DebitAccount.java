package Accounts.Entities;

import Accounts.Models.AccountBase;
import Accounts.Models.IInterestBearingAccount;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Timer;

public class DebitAccount extends AccountBase implements IInterestBearingAccount
{
    @Getter
    private Timer _interestTimer;

    public DebitAccount(Integer id, BigDecimal _balance)
    {
        super(id, _balance);
        _interestTimer = new Timer();
    }

    public void ApplyInterest(BigDecimal bankInterestRate)
    {
        BigDecimal interest = _balance.multiply(bankInterestRate);
        _balance = _balance.add(interest);
    }

    @Override
    public void ReplenishmentOfFunds(BigDecimal amount)
    {
        _balance = _balance.add(amount);
    }

    @Override
    public BigDecimal Withdrawal(BigDecimal amount) throws ShortageOfFundsException
    {
        if (_balance.compareTo(amount) <= 0)
        {
            throw new ShortageOfFundsException();
        }

        _balance = _balance.subtract(amount);
        return amount;
    }
}
