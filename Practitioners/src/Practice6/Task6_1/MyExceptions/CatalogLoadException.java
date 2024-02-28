package Practice6.Task6_1.MyExceptions;

import java.io.FileNotFoundException;

public class CatalogLoadException extends Exception
{
    public CatalogLoadException(String message)
    {
        super(message);
    }
}
