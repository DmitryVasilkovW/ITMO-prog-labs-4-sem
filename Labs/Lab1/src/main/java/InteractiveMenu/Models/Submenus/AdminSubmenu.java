package InteractiveMenu.Models.Submenus;

import Database.AppConfig;
import Database.Repositories.AdminRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;


public class AdminSubmenu
{
    private AdminRepository _adminRepository;
    private JFrame _frame;

    public AdminSubmenu()
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());

        var context = new AnnotationConfigApplicationContext();

        context.register(AdminRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        _adminRepository = context.getBean(AdminRepository.class);
    }

    public void AddPassportDetails()
    {
        String seriesStr = JOptionPane.showInputDialog(_frame, "Enter series");
        String numberStr = JOptionPane.showInputDialog(_frame, "Enter number");
        String userIdStr = JOptionPane.showInputDialog(_frame, "Enter user id");
        int series = Integer.parseInt(seriesStr);
        int number = Integer.parseInt(numberStr);
        Integer userId = Integer.parseInt(userIdStr);

        try
        {
            _adminRepository.AddPassportDetails(series, number, userId);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(_frame, e.getMessage());
        }
    }

    public void AddBank()
    {
        String name = JOptionPane.showInputDialog(_frame, "Enter name for bank");
        String reserveFundStr = JOptionPane.showInputDialog(_frame, "Enter reserve fund");
        String commissionStr = JOptionPane.showInputDialog(_frame, "Enter commission");
        var reserveFund = new BigDecimal(reserveFundStr);
        var commission = new BigDecimal(commissionStr);

        try
        {
            _adminRepository.AddBank(name, reserveFund, commission);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(_frame, e.getMessage());
        }
    }

    public void AddUser()
    {
        String name = JOptionPane.showInputDialog(_frame, "Enter name for user");
        String surname = JOptionPane.showInputDialog(_frame, "Enter surname");
        String password = JOptionPane.showInputDialog(_frame, "Enter password");

        try
        {
            _adminRepository.AddUser(name, surname, password);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(_frame, e.getMessage());
        }
    }

    public void AddAddress()
    {
        String street = JOptionPane.showInputDialog(_frame, "Enter street");
        String house = JOptionPane.showInputDialog(_frame, "Enter house");
        String floreStr = JOptionPane.showInputDialog(_frame, "Enter flore");
        String numberOfApartmentStr = JOptionPane.showInputDialog(_frame, "Enter number of apartment");
        String userIdStr = JOptionPane.showInputDialog(_frame, "Enter user id");
        Integer userId = Integer.parseInt(userIdStr);
        int flore = Integer.parseInt(floreStr);
        int numberOfApartment = Integer.parseInt(numberOfApartmentStr);

        try
        {
            _adminRepository.AddAddress(street, house, flore, numberOfApartment, userId);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(_frame, e.getMessage());
        }
    }

    public void AddCreditAccount()
    {
        String bankName = JOptionPane.showInputDialog(_frame, "Enter name of bank");
        String idStr = JOptionPane.showInputDialog(_frame, "Enter user id");
        String balanceStr = JOptionPane.showInputDialog(_frame, "Enter balance");
        String creditLimitStr = JOptionPane.showInputDialog(_frame, "Enter credit limit");
        String commissionStr = JOptionPane.showInputDialog(_frame, "Enter commission");
        Integer id = Integer.parseInt(idStr);
        BigDecimal balance = new BigDecimal(balanceStr);
        BigDecimal creditLimit = new BigDecimal(creditLimitStr);
        BigDecimal commission = new BigDecimal(commissionStr);

        _adminRepository.AddCreditAccount(id, balance, creditLimit, commission, bankName);
    }

    public void AddDebitAccount()
    {
        String bankName = JOptionPane.showInputDialog(_frame, "Enter name of bank");
        String idStr = JOptionPane.showInputDialog(_frame, "Enter user id");
        String balanceStr = JOptionPane.showInputDialog(_frame, "Enter balance");
        Integer id = Integer.parseInt(idStr);
        BigDecimal balance = new BigDecimal(balanceStr);

        _adminRepository.AddDebitAccount(id, balance, bankName);
    }

    public void AddDepositAccount()
    {
        String bankName = JOptionPane.showInputDialog(_frame, "Enter name of bank");
        String idStr = JOptionPane.showInputDialog(_frame, "Enter user id");
        String balanceStr = JOptionPane.showInputDialog(_frame, "Enter balance");
        String depositEndDateStr = JOptionPane.showInputDialog(_frame, "Enter deposit end date (yyyy-mm-dd)");
        Integer id = Integer.parseInt(idStr);
        BigDecimal balance = new BigDecimal(balanceStr);
        LocalDate depositEndDate = LocalDate.parse(depositEndDateStr);

        _adminRepository.AddDepositAccount(id, balance, depositEndDate, bankName);
    }

    public boolean RegisterAdmin()
    {
        String adminName = JOptionPane.showInputDialog(_frame, "Enter your username");
        String adminPassword = JOptionPane.showInputDialog(_frame, "Enter your password");

        if (_adminRepository.CheckAdmin(adminName, adminPassword))
        {
            JOptionPane.showMessageDialog(_frame, "Admin registered successfully!");

            return true;
        }

        JOptionPane.showMessageDialog(_frame, "Admin unconfirmed!");

        return false;
    }

    public JButton GetAdminSubmenu()
    {
        var adminRegistrationButton = new JButton("Register as Admin");

        adminRegistrationButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        adminRegistrationButton.addActionListener(e ->
        {
            boolean check = RegisterAdmin();

            var postRegistrationFrame = new JFrame("Admin Menu");
            postRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postRegistrationFrame.setSize(600, 400);
            postRegistrationFrame.setLayout(new BorderLayout());

            var addAddressButton = new JButton("Add address");
            addAddressButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddAddress();
                }
            });

            var addCreditAccountButton = new JButton("Add credit account");
            addCreditAccountButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddCreditAccount();
                }
            });

            var addDebitAccountButton = new JButton("Add debit account");
            addDebitAccountButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddDebitAccount();
                }
            });

            var addDepositAccountButton = new JButton("Add deposit account");
            addDepositAccountButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddDepositAccount();
                }
            });

            var addPassportDetailsButton = new JButton("Add passport details");
            addPassportDetailsButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddPassportDetails();
                }
            });

            var addBankButton = new JButton("Add bank");
            addBankButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddBank();
                }
            });

            var addUserButton = new JButton("Add user");
            addUserButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    AddUser();
                }
            });


            var closeButton = new JButton("Close Menu");
            closeButton.addActionListener(e1 ->
            {
                postRegistrationFrame.dispose();
            });

            var panel = new JPanel();
            panel.add(addBankButton);
            panel.add(addUserButton);
            panel.add(addAddressButton);
            panel.add(addPassportDetailsButton);
            panel.add(addCreditAccountButton);
            panel.add(addDebitAccountButton);
            panel.add(addDepositAccountButton);
            panel.add(closeButton);
            postRegistrationFrame.add(panel, BorderLayout.CENTER);

            postRegistrationFrame.setVisible(true);

            if (!check)
            {
                postRegistrationFrame.dispose();
            }
        });

        return adminRegistrationButton;
    }
}
