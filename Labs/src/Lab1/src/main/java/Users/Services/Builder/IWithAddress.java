package Users.Services.Builder;

import Users.Models.Address;

public interface IWithAddress
{
    IWithPassportDetails WithAddress(Address address);
}
