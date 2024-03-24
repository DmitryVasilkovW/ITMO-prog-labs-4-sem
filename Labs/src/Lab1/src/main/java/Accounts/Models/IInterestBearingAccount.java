package Accounts.Models;

import java.math.BigDecimal;

public interface IInterestBearingAccount
{
    void ApplyInterest(BigDecimal bankInterestRate);
}
