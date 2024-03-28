package InteractiveMenu.Models.Buttons;

import InteractiveMenu.Services.Scenarios.AccountScenario;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountButtons
{
    AccountScenario _scenario = new AccountScenario();
    JFrame _frame;
    public AccountButtons()
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());
    }

    public JMenuItem GetWithdrawalButton()
    {
        var withdrawalButton = new JMenuItem("Withdrawal");

        withdrawalButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        withdrawalButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String accountIdStr = JOptionPane.showInputDialog(_frame, "Input account id");
                    var accountId = Integer.parseInt(accountIdStr);

                    String amountStr = JOptionPane.showInputDialog(_frame, "Input amount for withdrawal");
                    var amount = new BigDecimal(amountStr);

                    _scenario.Withdrawal(amount, accountId);

                    BigDecimal balance = _scenario.GetBalance(accountId);

                    JOptionPane.showMessageDialog(_frame, "The balance is " + balance.toString());
                }
                catch (ShortageOfFundsException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        return withdrawalButton;
    }

    public JMenuItem GetCheckBalanceButton()
    {
        var checkBalanceButton = new JMenuItem("Check balance");

        checkBalanceButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        checkBalanceButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String accountIdStr = JOptionPane.showInputDialog(_frame, "Input account id");
                var accountId = Integer.parseInt(accountIdStr);

                BigDecimal balance = _scenario.GetBalance(accountId);

                JOptionPane.showMessageDialog(_frame, "The balance is " + balance.toString());
            }
        });

        return checkBalanceButton;
    }

    public JMenuItem GetDepositButton()
    {
        var depositButton = new JMenuItem("Deposit");

        depositButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        depositButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String accountIdStr = JOptionPane.showInputDialog(_frame, "Input account id");
                var accountId = Integer.parseInt(accountIdStr);

                String amountStr = JOptionPane.showInputDialog(_frame, "Input amount for deposit");
                var amount = new BigDecimal(amountStr);

                _scenario.ReplenishmentOfFunds(amount, accountId);

                BigDecimal balance = _scenario.GetBalance(accountId);

                JOptionPane.showMessageDialog(_frame, "The balance is " + balance.toString());
            }
        });

        return depositButton;
    }

    public JMenuItem GetInfoAboutAllAccountsButton()
    {
        var depositButton = new JMenuItem("Show info about all accounts");

        depositButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        depositButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = JOptionPane.showInputDialog(_frame, "Input your name");
                String surname = JOptionPane.showInputDialog(_frame, "Input your surname");
                String password = JOptionPane.showInputDialog(_frame, "Input your password");
                var info = _scenario.GetAccountInfo(new User(name, surname, null, null), password);

                StringBuilder sb = new StringBuilder();
                for (ArrayList<String> list : info)
                {
                    for (String s : list)
                    {
                        sb.append(s);
                        sb.append("\n");
                    }
                    sb.append("\n");
                }

                JOptionPane.showMessageDialog(_frame, sb.toString());
            }
        });

        return depositButton;
    }

}
