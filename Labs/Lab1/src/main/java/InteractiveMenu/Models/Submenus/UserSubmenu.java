package InteractiveMenu.Models.Submenus;

import Bank.Entities.Bank;
import Bank.Entities.CentralBank;
import Database.AppConfig;
import Database.Repositories.BankRepository;
import Database.Repositories.UserRepository;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserSubmenu
{
    private static Map<String, String> _userPasswords = new HashMap<>();
    private UserRepository _userRepository;
    private BankRepository _bankRepository;
    private JFrame _frame;
    private CentralBank _centralBank;
    private String _username;
    private String _password;
    private String _surname;

    public UserSubmenu()
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());

        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(BankRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        _userRepository = context.getBean(UserRepository.class);
        _bankRepository = context.getBean(BankRepository.class);
    }

    public void ShowAccountInfo()
    {
        JOptionPane.showMessageDialog(_frame, _centralBank.GetInfoAboutAccounts(_username, _surname, _password));
    }

    public void DisplayUserInfo()
    {
        User user = _centralBank.GetUserByPasswordAndFullName(_username, _surname, _password);

        assert user != null;
        String info = "Name: " + user.get_name() + "\n"
                + "Surname: " + user.get_surname() + "\n";

        if (user.GetAddress() != null)
        {
            info += "Street: " + user.GetAddress()._street() + "\n"
                    + "House: " + user.GetAddress()._house() + "\n"
                    + "Flore: " + user.GetAddress()._flore() + "\n"
                    + "Number of apartment: " + user.GetAddress()._numberOfApartment() + "\n";
        }

        if (user.GetPassportDetails() != null)
        {
            info += "Series of passport: " + user.GetPassportDetails()._series() + "\n"
                    + "Number of passport: " + user.GetPassportDetails()._number();
        }

        JOptionPane.showMessageDialog(_frame, info);
    }

    public void ShowPassword()
    {
        PasswordInfoDialog.showDialog(_password);
    }


    public void Withdrawal()
    {
        String bankName = JOptionPane.showInputDialog(_frame, "Enter bank");
        String accountIdStr = JOptionPane.showInputDialog(_frame, "Enter account id");
        String amountStr = JOptionPane.showInputDialog(_frame, "Enter amount to withdrawal");
        Integer accountId = Integer.parseInt(accountIdStr);
        var amount = new BigDecimal(amountStr);

        try
        {
            BigDecimal chek = _centralBank.Withdraw(bankName, accountId, amount);

            if (chek == null)
            {
                JOptionPane.showMessageDialog(_frame, "incorrect user information!");

                return;
            }
        }
        catch (ShortageOfFundsException ex)
        {
            throw new RuntimeException(ex);
        }

        JOptionPane.showMessageDialog(_frame, "Balance: " + _centralBank.GetBalance(bankName, accountId).toString());
    }

    public void Deposit()
    {
        String bankName = JOptionPane.showInputDialog(_frame, "Enter bank");
        String accountIdStr = JOptionPane.showInputDialog(_frame, "Enter account id");
        String amountStr = JOptionPane.showInputDialog(_frame, "Enter amount to deposit");
        Integer accountId = Integer.parseInt(accountIdStr);
        var amount = new BigDecimal(amountStr);

        _centralBank.Deposit(bankName, accountId, amount);
        JOptionPane.showMessageDialog(_frame, "Balance: " + _centralBank.GetBalance(bankName, accountId).toString());
    }

    public void TransferFunds()
    {
        String fromBankName = JOptionPane.showInputDialog(_frame, "Enter name of the bank from which to transfer");
        String fromAccountIdStr = JOptionPane.showInputDialog(_frame, "Enter account id from which to transfer");
        String toBankName = JOptionPane.showInputDialog(_frame, "Enter name of the bank to which to deposit");
        String toAccountIdStr = JOptionPane.showInputDialog(_frame, "Enter account id to which to deposit");
        String amountStr = JOptionPane.showInputDialog(_frame, "Enter amount to transfer");
        Integer fromAccountId = Integer.parseInt(fromAccountIdStr);
        Integer toAccountId = Integer.parseInt(toAccountIdStr);
        var amount = new BigDecimal(amountStr);

        try
        {
            _centralBank.TransferFunds(fromBankName, fromAccountId, toBankName, toAccountId, amount);
        }
        catch (ShortageOfFundsException | RuntimeException e)
        {
            JOptionPane.showMessageDialog(_frame, "incorrect user information!");

            return;
        }

        JOptionPane.showMessageDialog(
                _frame, "Bank: " + fromBankName
                        + "\nAccount ID: " + fromAccountId
                        + "\nCharge card balance: "
                        + _centralBank.GetBalance(fromBankName, fromAccountId).toString()
                        + "\n\nBank: " + toBankName
                        + "\nAccount ID: " + toAccountId
                        + "\nRecharge card balance: "
                        + _centralBank.GetBalance(toBankName, toAccountId).toString());
    }


    public boolean RegisterUser()
    {
        _username = JOptionPane.showInputDialog(_frame, "Enter your username");
        _surname = JOptionPane.showInputDialog(_frame, "Enter your surname");
        _password = JOptionPane.showInputDialog(_frame, "Enter your password");
        _centralBank = new CentralBank();

        if (_bankRepository.CheckPassword(_username, _surname, _password))
        {
            var banks = _bankRepository.GetAllBanks(_password);

            for (Bank bank : banks)
            {
                _centralBank.AddBank(bank);
            }


            _userPasswords.put(_username, _password);
            JOptionPane.showMessageDialog(_frame, "User registered successfully!");

            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "incorrect user information!");

            return false;
        }
    }

    public JButton GetUserSubmenu()
    {
        var userRegistrationButton = new JButton("Register as User");

        userRegistrationButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        userRegistrationButton.addActionListener(e ->
        {
            boolean check = RegisterUser();

            var postRegistrationFrame = new JFrame("User Menu");
            postRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postRegistrationFrame.setSize(600, 400);
            postRegistrationFrame.setLayout(new BorderLayout());

            var withdrawalButton = new JButton("Withdrawal");
            withdrawalButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Withdrawal();
                }
            });

            var depositButton = new JButton("Deposit");
            depositButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Deposit();
                }
            });

            var passwordButton = new JButton("Show password");
            passwordButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ShowPassword();
                }
            });

            var accountInfoButton = new JButton("Show info about accounts");
            accountInfoButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ShowAccountInfo();
                }
            });

            var userInfoButton = new JButton("Show info about user");
            userInfoButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    DisplayUserInfo();
                }
            });

            var transferFundsButton = new JButton("Transfer funds");
            transferFundsButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        TransferFunds();
                    }
                    catch (RuntimeException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            });

            var closeButton = new JButton("Close Menu");
            closeButton.addActionListener(e1 ->
            {
                _centralBank.close();
                postRegistrationFrame.dispose();
            });


            var panel = new JPanel();
            panel.add(withdrawalButton);
            panel.add(depositButton);
            panel.add(transferFundsButton);
            panel.add(userInfoButton);
            panel.add(passwordButton);
            panel.add(accountInfoButton);
            panel.add(closeButton);
            postRegistrationFrame.add(panel, BorderLayout.CENTER);

            postRegistrationFrame.setVisible(true);

            if (!check)
            {
                postRegistrationFrame.dispose();
            }
        });

        return userRegistrationButton;
    }
}
