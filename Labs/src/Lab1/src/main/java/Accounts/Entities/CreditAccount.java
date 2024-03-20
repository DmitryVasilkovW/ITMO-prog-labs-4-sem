package Accounts.Entities;

import Accounts.Models.AccountBase;

import java.math.BigDecimal;

public class CreditAccount extends AccountBase
{

    public CreditAccount(BigDecimal _balance)
    {
        super(_balance);
    }
}
