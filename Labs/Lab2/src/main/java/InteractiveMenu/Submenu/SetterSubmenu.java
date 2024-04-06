package InteractiveMenu.Submenu;

import controllers.CatController;
import controllers.OwnerController;
import repositories.CatRepository;
import repositories.OwnerRepository;
import services.CatService;
import services.OwnerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SetterSubmenu
{
    private OwnerController _ownerController;
    private CatController _catController;
    private JFrame _frame;

    public SetterSubmenu()
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());

        _catController = new CatController(new CatService(new CatRepository()));
        _ownerController = new OwnerController(new OwnerService(new OwnerRepository()));
    }

    public void updateCatName()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        String name = JOptionPane.showInputDialog(_frame, "Enter new name for cat");
        int id = Integer.parseInt(idStr);

        _catController.updateCatName(id, name);
    }

    public void updateCatBirthDate()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        String birthDateStr = JOptionPane.showInputDialog(_frame, "Enter new BirthDate for cat (format: yy-mm-dd)");
        int id = Integer.parseInt(idStr);
        LocalDate birthDate = LocalDate.parse(birthDateStr);

        _catController.updateCatBirthDate(id, birthDate);
    }

    public void updateCatBreed()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        String breed = JOptionPane.showInputDialog(_frame, "Enter new breed for cat");
        int id = Integer.parseInt(idStr);

        _catController.updateCatBreed(id, breed);
    }

    public void updateCatColor()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        String color = JOptionPane.showInputDialog(_frame, "Enter new color for cat");
        int id = Integer.parseInt(idStr);

        _catController.updateCatColor(id, color);
    }

    public void deleteCat()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        int id = Integer.parseInt(idStr);

        _catController.deleteCat(id);
    }

    public void updateOwnerName()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter owner_id");
        String name = JOptionPane.showInputDialog(_frame, "Enter new name for owner");
        int id = Integer.parseInt(idStr);

        _ownerController.updateOwnerName(id, name);
    }

    public void updateOwnerBirthDate()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter owner_id");
        String birthDateStr = JOptionPane.showInputDialog(_frame, "Enter new birth date for owner (format: yyyy-mm-dd)");
        int id = Integer.parseInt(idStr);
        LocalDate birthDate = LocalDate.parse(birthDateStr);

        _ownerController.updateOwnerBirthDate(id, birthDate);
    }

    public void deleteOwner()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter owner_id");
        int id = Integer.parseInt(idStr);

        _ownerController.deleteOwner(id);
    }


    public JButton GetSettings()
    {
        var settingsButton = new JButton("Settings");

        settingsButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        settingsButton.addActionListener(e ->
        {
            var postRegistrationFrame = new JFrame("Settings");
            postRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postRegistrationFrame.setSize(600, 400);
            postRegistrationFrame.setLayout(new BorderLayout());

            var updateCatNameButton = new JButton("Update name of cat");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateCatName();
                }
            });

            var updateCatBirthDateButton = new JButton("Update BirthDate of cat");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateCatBirthDate();
                }
            });

            var updateCatBreedButton = new JButton("Update breed of cat");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateCatBreed();
                }
            });

            var updateCatColorButton = new JButton("Update color of cat");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateCatColor();
                }
            });

            var deleteCatButton = new JButton("Delete cat");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    deleteCat();
                }
            });

            var updateOwnerNameButton = new JButton("Update name of owner");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateOwnerName();
                }
            });

            var updateOwnerBirthDateButton = new JButton("Update birthDate of owner");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    updateOwnerBirthDate();
                }
            });

            var deleteOwnerButton = new JButton("Delete owner");
            updateCatNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    deleteOwner();
                }
            });

            var closeButton = new JButton("Close Menu");
            closeButton.addActionListener(e1 ->
            {
                postRegistrationFrame.dispose();
            });

            var panel = new JPanel();
            panel.add(updateCatNameButton);
            panel.add(updateCatBirthDateButton);
            panel.add(updateCatBreedButton);
            panel.add(updateCatColorButton);
            panel.add(deleteCatButton);
            panel.add(updateOwnerNameButton);
            panel.add(updateOwnerBirthDateButton);
            panel.add(deleteOwnerButton);
            panel.add(closeButton);
            postRegistrationFrame.add(panel, BorderLayout.CENTER);

            postRegistrationFrame.setVisible(true);
        });

        return settingsButton;
    }
}
