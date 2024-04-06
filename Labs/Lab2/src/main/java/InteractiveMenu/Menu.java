package InteractiveMenu;

import InteractiveMenu.Submenu.GetInfoSubmenu;
import InteractiveMenu.Submenu.SetterSubmenu;

import javax.swing.*;
import java.awt.*;

public class Menu
{

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

        var frame = new JFrame("KOTIKI!!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        var menu = new JPopupMenu();
        menu.setBackground(Color.DARK_GRAY);

        var exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        exitButton.addActionListener(e -> System.exit(0));

        var settingsSubmenu = new SetterSubmenu();
        var settingsButton = settingsSubmenu.GetSettings();
        frame.add(settingsButton);

        var infoSubmenu = new GetInfoSubmenu();
        var infoButton = infoSubmenu.GetInfoButton();
        frame.add(infoButton);

        var panel = new JPanel();
        panel.add(infoButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
