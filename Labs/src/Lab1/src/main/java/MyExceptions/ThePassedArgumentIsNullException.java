package MyExceptions;

public class ThePassedArgumentIsNullException extends Exception
{
    public ThePassedArgumentIsNullException()
    {
        super("The passed argument is null");
    }

    public ThePassedArgumentIsNullException(String message)
    {
        super(message);
    }
}
