package Accounts.Entities;

import Accounts.Models.AccountBase;

import java.math.BigDecimal;

public class DebitAccount extends AccountBase
{
    public DebitAccount(BigDecimal _balance)
    {
        super(_balance);
    }
}
