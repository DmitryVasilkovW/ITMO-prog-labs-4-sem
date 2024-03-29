package Users.Entites;

import Users.Models.Address;
import Users.Models.PassportDetails;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

public class User
{
    @Getter
    private String _name;
    @Getter
    private String _surname;
    private @Nullable Address _address;
    private @Nullable PassportDetails _passportDetails;

    public User(String name, String surname, @Nullable Address address, @Nullable PassportDetails passportDetails)
    {
        _name = name;
        _surname = surname;
        _address = address;
        _passportDetails = passportDetails;
    }

    public PassportDetails GetPassportDetails()
    {
        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();

        if (callingClassName.equals("InteractiveMenu.Models.Submenus.UserSubmenu"))
        {
            return _passportDetails;
        }
        else
        {
            throw new SecurityException("Access denied");
        }
    }

    public Address GetAddress()
    {
        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();

        if (callingClassName.equals("InteractiveMenu.Models.Submenus.UserSubmenu"))
        {
            return _address;
        }
        else
        {
            throw new SecurityException("Access denied");
        }
    }

    public void InitAddress(Address address)
    {
        if (_address == null)
        {
            _address = address;
        }
    }

    public void InitPassportDetails(PassportDetails details)
    {
        if (_passportDetails == null)
        {
            _passportDetails = details;
        }
    }
}