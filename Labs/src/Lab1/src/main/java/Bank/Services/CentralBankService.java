package Bank.Services;

import Bank.Entities.Bank;
import Bank.Entities.CentralBank;
import Database.Repositories.AccountRepository;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;

import java.math.BigDecimal;
import java.util.Map;

public class CentralBankService implements AutoCloseable
{
    private final CentralBank _bank;


    public CentralBankService(CentralBank bank)
    {
        _bank = bank;
    }

    public void ApplyDailyInterest(String bankName, Integer accountId)
    {
        _bank.ApplyDailyInterest(bankName, accountId);
    }

    public void AddBank(Bank bank)
    {
        _bank.AddBank(bank);
    }

    public void RegistrationNewBank(
            String name,
            BigDecimal interestRate,
            BigDecimal commission,
            AccountRepository repository,
            Map<String, User> users)
    {
        _bank.RegistrationNewBank(name, interestRate, commission, repository, users);
    }

    public void TransferFunds(
            String fromBankName,
            Integer fromAccountId,
            String toBankName,
            Integer toAccountId,
            BigDecimal amount) throws ShortageOfFundsException
    {
        _bank.TransferFunds(fromBankName, fromAccountId, toBankName, toAccountId, amount);
    }

    public BigDecimal Withdraw(String bankName, Integer accountId, BigDecimal amount) throws ShortageOfFundsException
    {
        return _bank.Withdraw(bankName, accountId, amount);
    }

    public void Deposit(String bankName, Integer accountId, BigDecimal amount)
    {
        _bank.Deposit(bankName, accountId, amount);
    }

    public void NotifyBanks()
    {
        _bank.NotifyBanks();
    }

    @Override
    public void close()
    {
        _bank.close();
    }
}
