package Users.Services.Builder;

import MyExceptions.ThePassedArgumentIsNullException;
import Users.Entites.User;
import Users.Models.Address;
import Users.Models.PassportDetails;
import org.jetbrains.annotations.Nullable;

public class UserBuilder implements
        IUserBuilder,
        IWithAddress,
        IWithSurname,
        IWithName,
        IWithPassportDetails,
        IWithSurnameAndWithoutOtherParameters
{
    private @Nullable String _name;
    private @Nullable String _surname;
    private @Nullable Address _address;
    private @Nullable PassportDetails _passportDetails;

    public User Build() throws ThePassedArgumentIsNullException
    {
        if (_name != null && _surname != null) return new User(_name, _surname, _address, _passportDetails);
        throw new ThePassedArgumentIsNullException();
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

    @Override
    public IUserBuilder WithSurnameAndWithoutOtherParameters(String surname)
    {
        _surname = surname;

        return this;
    }
}
