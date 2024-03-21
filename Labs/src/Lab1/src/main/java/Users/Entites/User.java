package Users.Entites;

import Users.Models.Address;
import Users.Models.PassportDetails;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public class User
{
    private String _name;
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