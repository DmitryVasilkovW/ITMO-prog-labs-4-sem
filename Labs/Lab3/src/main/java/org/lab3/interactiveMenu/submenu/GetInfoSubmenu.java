package org.lab3.interactiveMenu.submenu;

import org.lab3.controllers.CatController;
import org.lab3.controllers.OwnerController;
import org.lab3.models.Cat;
import org.lab3.models.Owner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class GetInfoSubmenu
{
    private final OwnerController _ownerController;
    private final CatController _catController;
    private final JFrame _frame;

    public GetInfoSubmenu()
    {
        _frame = new JFrame("Simple Menu");

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(600, 400);
        _frame.setLayout(new BorderLayout());

        _catController = new CatController();
        _ownerController = new OwnerController();
    }

    public String showInfoAboutCat(Cat cat)
    {
        var info = new StringBuilder();

        if (cat != null)
        {
            info.append( "Cat ID: " + cat.getId() + "\n" +
                    "Name: " + cat.getName() + "\n" +
                    "Birth Date: " + cat.getBirthDate() + "\n" +
                    "Breed: " + cat.getBreed() + "\n" +
                    "Color: " + cat.getColor() + "\n" +
                    "Owner: " + cat.getOwner().getName() + "\n\n");

            List<Cat> friends = cat.getFriends();

            if (friends != null && !friends.isEmpty())
            {
                StringBuilder friendsInfo = new StringBuilder("Friends:\n");

                for (Cat friend : friends)
                {
                    friendsInfo.append("Friend ID: ").append(friend.getId()).append("\n")
                            .append("Name: ").append(friend.getName()).append("\n")
                            .append("Birth Date: ").append(friend.getBirthDate()).append("\n")
                            .append("Breed: ").append(friend.getBreed()).append("\n")
                            .append("Color: ").append(friend.getColor()).append("\n")
                            .append("Owner: ").append(friend.getOwner().getName()).append("\n\n");
                }
                info.append(friendsInfo.toString());
            }
        }

        return info.toString();
    }

    public void showInfoAboutCat()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter cat_id");
        int id = Integer.parseInt(idStr);

        Cat cat = _catController.getCatById(id);

        if (cat != null)
        {
            JOptionPane.showMessageDialog(_frame, "Cat ID: " + cat.getId() + "\n" +
                    "Name: " + cat.getName() + "\n" +
                    "Birth Date: " + cat.getBirthDate() + "\n" +
                    "Breed: " + cat.getBreed() + "\n" +
                    "Color: " + cat.getColor() + "\n" +
                    "Owner: " + cat.getOwner().getName());

            List<Cat> friends = cat.getFriends();

            if (friends != null && !friends.isEmpty())
            {
                StringBuilder friendsInfo = new StringBuilder("Friends:\n");
                for (Cat friend : friends)
                {
                    friendsInfo.append("Friend ID: ").append(friend.getId()).append("\n")
                            .append("Name: ").append(friend.getName()).append("\n")
                            .append("Birth Date: ").append(friend.getBirthDate()).append("\n")
                            .append("Breed: ").append(friend.getBreed()).append("\n")
                            .append("Color: ").append(friend.getColor()).append("\n")
                            .append("Owner: ").append(friend.getOwner().getName()).append("\n\n");
                }
                JOptionPane.showMessageDialog(_frame, friendsInfo.toString());
            }
            else
            {
                JOptionPane.showMessageDialog(_frame, "This cat has no friends.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No cat found with ID: " + id);
        }
    }

    public void getCatsByName()
    {
        var info = new StringBuilder();
        String name = JOptionPane.showInputDialog(_frame, "Enter cat name");
        List<Cat> cats = _catController.getCatsByName(name);

        if (cats != null && !cats.isEmpty())
        {
            for (Cat cat : cats)
            {
                info.append(showInfoAboutCat(cat));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No cats found with name: " + name);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public void getCatsByBirthDate()
    {
        var info = new StringBuilder();
        String birthDateStr = JOptionPane.showInputDialog(_frame, "Enter cat birth date (format: yyyy-mm-dd)");
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        List<Cat> cats = _catController.getCatsByBirthDate(birthDate);

        if (cats != null && !cats.isEmpty())
        {
            for (Cat cat : cats)
            {
                info.append(showInfoAboutCat(cat));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No cats found with birth date: " + birthDate);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public void getCatsByBreed()
    {
        var info = new StringBuilder();
        String breed = JOptionPane.showInputDialog(_frame, "Enter cat breed");
        List<Cat> cats = _catController.getCatsByBreed(breed);

        if (cats != null && !cats.isEmpty())
        {
            for (Cat cat : cats)
            {
                info.append(showInfoAboutCat(cat));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No cats found with breed: " + breed);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public void getCatsByColor()
    {
        var info = new StringBuilder();
        String color = JOptionPane.showInputDialog(_frame, "Enter cat color");
        List<Cat> cats = _catController.getCatsByColor(color);

        if (cats != null && !cats.isEmpty())
        {
            for (Cat cat : cats)
            {
                info.append(showInfoAboutCat(cat));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No cats found with color: " + color);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public void showOwnerById()
    {
        String idStr = JOptionPane.showInputDialog(_frame, "Enter owner_id");
        int id = Integer.parseInt(idStr);
        Owner owner = _ownerController.getOwnerById(id);

        if (owner != null)
        {
            JOptionPane.showMessageDialog(_frame, "Owner ID: " + owner.getId() + "\n" +
                    "Name: " + owner.getName() + "\n" +
                    "Birth Date: " + owner.getBirthDate());
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No owner found with ID: " + id);
        }
    }

    public void showOwnersByName()
    {
        var info = new StringBuilder();
        String name = JOptionPane.showInputDialog(_frame, "Enter owner name");
        List<Owner> owners = _ownerController.getOwnersByName(name);

        if (owners != null && !owners.isEmpty())
        {
            for (Owner owner : owners)
            {
                info.append("Owner ID: " + owner.getId() + "\n" +
                        "Name: " + owner.getName() + "\n" +
                        "Birth Date: " + owner.getBirthDate() + "\n\n");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No owners found with name: " + name);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public void showOwnersByBirthDate()
    {
        var info = new StringBuilder();
        String birthDateStr = JOptionPane.showInputDialog(_frame, "Enter owner birth date (format: yyyy-mm-dd)");
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        List<Owner> owners = _ownerController.getOwnersByBirthDate(birthDate);

        if (owners != null && !owners.isEmpty())
        {
            for (Owner owner : owners)
            {
                info.append("Owner ID: " + owner.getId() + "\n" +
                        "Name: " + owner.getName() + "\n" +
                        "Birth Date: " + owner.getBirthDate() + "\n\n");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(_frame, "No owners found with birth date: " + birthDate);
        }

        JOptionPane.showMessageDialog(_frame, info.toString());
    }

    public JButton GetInfoButton()
    {
        var InfoButton = new JButton("Info");

        InfoButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        InfoButton.addActionListener(e ->
        {
            var postRegistrationFrame = new JFrame("Info");
            postRegistrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postRegistrationFrame.setSize(600, 400);
            postRegistrationFrame.setLayout(new BorderLayout());

            var showInfoAboutCatButton = new JButton("Show info about cat");
            showInfoAboutCatButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    showInfoAboutCat();
                }
            });

            var getCatsByNameButton = new JButton("Show info about cats by name");
            getCatsByNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getCatsByName();
                }
            });

            var getCatsByBirthDateButton = new JButton("Show info about cats by birthday");
            getCatsByBirthDateButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getCatsByBirthDate();
                }
            });

            var getCatsByBreedButton = new JButton("Show info about cats by breed");
            getCatsByBreedButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getCatsByBreed();
                }
            });

            var getCatsByColorButton = new JButton("Show info about cats by color");
            getCatsByColorButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getCatsByColor();
                }
            });

            var showOwnerByIdButton = new JButton("Show info about owner");
            showOwnerByIdButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    showOwnerById();
                }
            });

            var showOwnersByNameButton = new JButton("Show info about owners by name");
            showOwnersByNameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    showOwnersByName();
                }
            });

            var showOwnersByBirthDateButton = new JButton("Show info about owners by birthday");
            showOwnersByBirthDateButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    showOwnersByBirthDate();
                }
            });

            var closeButton = new JButton("Close Menu");
            closeButton.addActionListener(e1 ->
            {
                postRegistrationFrame.dispose();
            });

            var panel = new JPanel();
            panel.add(showInfoAboutCatButton);
            panel.add(getCatsByNameButton);
            panel.add(getCatsByBirthDateButton);
            panel.add(getCatsByBreedButton);
            panel.add(getCatsByColorButton);
            panel.add(showOwnerByIdButton);
            panel.add(showOwnersByBirthDateButton);
            panel.add(showOwnersByNameButton);
            panel.add(closeButton);
            postRegistrationFrame.add(panel, BorderLayout.CENTER);

            postRegistrationFrame.setVisible(true);
        });

        return InfoButton;
    }
}