package InteractiveMenu.Entities;

import InteractiveMenu.Models.Buttons.AccountButtons;
import InteractiveMenu.Models.Submenus.UserSubmenu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Menu
{
    private static AccountButtons _accountButtons = new AccountButtons();
    private static Map<String, String> _userPasswords = new HashMap<>();

    public static void main(String[] args) throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException
    {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            if ("Nimbus".equals(info.getName()))
            {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        var frame = new JFrame("Menu of the central bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        var menu = new JPopupMenu();
        menu.setBackground(Color.DARK_GRAY);

        var withdrawalButton = _accountButtons.GetWithdrawalButton();
        var checkBalanceButton = _accountButtons.GetCheckBalanceButton();
        var depositButton = _accountButtons.GetDepositButton();
        var infoAboutAllAccountsButton = _accountButtons.GetInfoAboutAllAccountsButton();

        menu.add(depositButton);
        menu.add(withdrawalButton);
        menu.add(checkBalanceButton);
        menu.add(infoAboutAllAccountsButton);

        var optionsButton = new JButton("Options without registration");
        optionsButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        optionsButton.addActionListener(e -> menu.show(optionsButton, optionsButton.getWidth() / 2, optionsButton.getHeight() / 2));

        var exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        exitButton.addActionListener(e -> System.exit(0));

        var userSubmenu = new UserSubmenu();
        var userRegistrationButton = userSubmenu.GetUserSubmenu();
        frame.add(userRegistrationButton);



        var adminRegistrationButton = new JButton("Register as Admin");
        adminRegistrationButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        adminRegistrationButton.addActionListener(e ->
        {
            String username = JOptionPane.showInputDialog(frame, "Enter username");
            String password = JOptionPane.showInputDialog(frame, "Enter password");
            _userPasswords.put(username, password);
            JOptionPane.showMessageDialog(frame, "Admin registered successfully!");


            var postRegistrationFrame = new JFrame("Admin Menu");
            postRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postRegistrationFrame.setSize(600, 400);
            postRegistrationFrame.setLayout(new BorderLayout());

            var showUsernameButton = new JButton("Show Username");
            showUsernameButton.addActionListener(e1 -> JOptionPane.showMessageDialog(postRegistrationFrame, "Username: " + username));

            var showPasswordButton = new JButton("Show Password");
            showPasswordButton.addActionListener(e1 -> JOptionPane.showMessageDialog(postRegistrationFrame, "Password: " + password));

            var closeButton = new JButton("Close Menu");
            closeButton.addActionListener(e1 -> postRegistrationFrame.dispose());

            var panel = new JPanel();
            panel.add(showUsernameButton);
            panel.add(showPasswordButton);
            panel.add(closeButton);
            postRegistrationFrame.add(panel, BorderLayout.CENTER);

            postRegistrationFrame.setVisible(true);
        });

        var panel = new JPanel();
        panel.add(optionsButton);
        panel.add(userRegistrationButton);
        panel.add(adminRegistrationButton);
        panel.add(exitButton);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
