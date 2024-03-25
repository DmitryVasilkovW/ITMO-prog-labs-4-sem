package MyExceptions;

public class IncorrectArgumentException extends Exception
{
    public IncorrectArgumentException()
    {
        super("Incorrect argument");
    }

    public IncorrectArgumentException(String message)
    {
        super(message);
    }
}
