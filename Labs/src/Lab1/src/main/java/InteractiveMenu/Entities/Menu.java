package InteractiveMenu.Entities;

import InteractiveMenu.Models.Buttons.AccountButtons;

import javax.swing.*;
import java.awt.*;

public class Menu
{
    private static AccountButtons _accountButtons = new AccountButtons();

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

        var frame = new JFrame("Simple Menu");
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

        var optionsButton = new JButton("Options");
        optionsButton.setFont(new Font("Arial", Font.PLAIN, 13));
        optionsButton.addActionListener(e -> menu.show(optionsButton, optionsButton.getWidth() / 2, optionsButton.getHeight() / 2));

        var panel = new JPanel();
        panel.add(optionsButton);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
