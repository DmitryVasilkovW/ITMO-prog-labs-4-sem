package Accounts.Entities;

import Accounts.Models.AccountBase;
import MyExceptions.ShortageOfFundsException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

public class DebitAccount extends AccountBase
{
    @Getter
    private BigDecimal _interestRate;
    @Getter
    private Timer _interestTimer;

    public DebitAccount(Integer id, BigDecimal _balance, BigDecimal interestRate)
    {
        super(id, _balance);
        _interestRate = interestRate;
        _interestTimer = new Timer();
        _interestTimer.schedule(new InterestTask(), 0, 60000);
    }

    class InterestTask extends TimerTask
    {
        public void run()
        {
            applyInterest();
        }
    }

    private void applyInterest()
    {
        BigDecimal interest = _balance.multiply(_interestRate);
        _balance = _balance.add(interest);
    }

    @Override
    public void ReplenishmentOfFunds(BigDecimal amount)
    {
        this._balance = this._balance.add(amount);
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
