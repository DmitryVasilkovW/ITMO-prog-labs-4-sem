package Users.Services.Builder;

import MyExceptions.ThePassedArgumentIsNullException;
import Users.Entites.User;

public interface IUserBuilder
{
    User Build() throws ThePassedArgumentIsNullException;
}
