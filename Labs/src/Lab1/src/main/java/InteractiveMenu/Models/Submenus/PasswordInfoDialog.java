package InteractiveMenu.Models.Submenus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordInfoDialog extends JDialog
{
    private JPanel contentPane = new JPanel();
    private JButton buttonOK = new JButton("OK");
    private JButton showPasswordButton = new JButton("Show Password");
    private JFrame _frame;

    public PasswordInfoDialog(String password)
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        showPasswordButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(_frame, "Password: " + password);
            }
        });

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        contentPane.add(showPasswordButton);
        contentPane.add(buttonOK);
    }


    public static void showDialog(String password)
    {
        PasswordInfoDialog dialog = new PasswordInfoDialog(password);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onOK()
    {
        dispose();
    }
}
