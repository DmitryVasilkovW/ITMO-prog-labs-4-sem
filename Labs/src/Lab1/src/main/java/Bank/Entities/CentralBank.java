package Bank.Entities;

import Accounts.Models.AccountBase;
import Accounts.Models.IInterestBearingAccount;
import MyExceptions.ShortageOfFundsException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CentralBank implements AutoCloseable
{
    private Map<String, Bank> _banks;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CentralBank()
    {
        _banks = new HashMap<>();
        startInterestAccrual();
    }

    private void startInterestAccrual()
    {
        scheduler.scheduleAtFixedRate(this::applyDailyInterestToAllAccounts, 0, 5, TimeUnit.MINUTES);
    }

    private void applyDailyInterestToAllAccounts()
    {
        for (Bank bank : _banks.values())
        {
            for (Integer accountId : bank.GetAllAccountIds())
            {
                ApplyDailyInterest(bank.get_name(), accountId);
            }
        }
    }

    public void ApplyDailyInterest(String bankName, Integer accountId)
    {
        Bank bank = _banks.get(bankName);
        AccountBase account = bank.get_accounts().get(accountId);

        BigDecimal currentBalance = account.get_balance();
        BigDecimal dailyInterest = currentBalance.multiply(bank.get_interestRate());

        if (account instanceof IInterestBearingAccount)
        {
            ((IInterestBearingAccount) account).ApplyInterest(dailyInterest);
        }
    }

    public void AddBank(Bank bank)
    {
        _banks.put(bank.get_name(), bank);
    }

    public BigDecimal GetBalance(String bankName , Integer id)
    {
        return _banks.get(bankName).GetBalance(id);
    }

    public void TransferFunds(
            String fromBankName,
            Integer fromAccountId,
            String toBankName,
            Integer toAccountId,
            BigDecimal amount) throws ShortageOfFundsException
    {
        Bank fromBank = _banks.get(fromBankName);
        Bank toBank = _banks.get(toBankName);

        amount = fromBank.Withdraw(fromAccountId, amount);
        toBank.Deposit(toAccountId, amount);
    }

    public BigDecimal Withdraw(String bankName, Integer accountId, BigDecimal amount) throws ShortageOfFundsException
    {
        Bank bank = _banks.get(bankName);

        return bank.Withdraw(accountId, amount);
    }

    public void Deposit(String bankName, Integer accountId, BigDecimal amount)
    {
        Bank bank = _banks.get(bankName);

        bank.Deposit(accountId, amount);
    }

    public void NotifyBanks()
    {
        for (Bank bank : _banks.values())
        {
            bank.ApplyInterestOrCommission();
        }
    }

    @Override
    public void close()
    {
        for (Bank bank : _banks.values())
        {
            bank.close();
        }
    }
}
