package Bank.Entities;

import MyExceptions.ShortageOfFundsException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CentralBank
{
    private Map<String, Bank> banks;

    public CentralBank()
    {
        this.banks = new HashMap<>();
    }

    public void registerBank(Bank bank)
    {
        banks.put(bank.get_name(), bank);
    }

    public void transferFunds(String fromBankName, String toBankName, BigDecimal amount) throws ShortageOfFundsException
    {
        Bank fromBank = banks.get(fromBankName);
        Bank toBank = banks.get(toBankName);
        fromBank.withdraw("tmp", amount);
        toBank.deposit("tmp", amount);
    }

    public void notifyBanks()
    {
        for (Bank bank : banks.values())
        {
            bank.applyInterestOrCommission();
        }
    }
}
