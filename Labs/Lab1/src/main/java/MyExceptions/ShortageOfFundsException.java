package MyExceptions;

public class ShortageOfFundsException extends Exception
{
    public ShortageOfFundsException()
    {
        super("Shortage of funds");
    }

    public ShortageOfFundsException(String message)
    {
        super(message);
    }
}
