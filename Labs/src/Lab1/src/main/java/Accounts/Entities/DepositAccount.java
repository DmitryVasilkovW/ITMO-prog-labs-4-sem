package Accounts.Entities;

import Accounts.Models.AccountBase;

import java.math.BigDecimal;

public class DepositAccount extends AccountBase
{
    public DepositAccount(BigDecimal _balance)
    {
        super(_balance);
    }
}
