package Users.Services.Builder;

import Users.Entites.User;
import Users.Models.Address;
import Users.Models.PassportDetails;
import org.jetbrains.annotations.Nullable;

public class UserBuilder implements
        IUserBuilder,
        IWithAddress,
        IWithSurname,
        IWithName,
        IWithPassportDetails
{
    private @Nullable String _name;
    private @Nullable String _surname;
    private @Nullable Address _address;
    private @Nullable PassportDetails _passportDetails;

    public User Build()
    {
        return new User(_name, _surname, _address, _passportDetails);
    }

    @Override
    public IWithSurname WithName(String name)
    {
        _name = name;

        return this;
    }

    @Override
    public IWithAddress WithSurname(String surname)
    {
        _surname = surname;

        return this;
    }

    @Override
    public IWithPassportDetails WithAddress(Address address)
    {
        _address = address;

        return this;
    }

    @Override
    public IUserBuilder WithPassportDetails(PassportDetails passportDetails)
    {
        _passportDetails = passportDetails;

        return this;
    }
}
