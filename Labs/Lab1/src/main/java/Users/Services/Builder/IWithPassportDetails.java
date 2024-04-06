package Users.Services.Builder;

import Users.Models.PassportDetails;

public interface IWithPassportDetails
{
    IUserBuilder WithPassportDetails(PassportDetails passportDetails);
}
