package Practice6.Task6_3_2;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        readAndPrintFile("/Users/dmitryvasilkov/IdeaProjects/ITMO-prog-labs-4-semm/Practitioners/src/Practice6/Task6_3_2/utf8file.txt", "UTF-8");
        readAndPrintFile("/Users/dmitryvasilkov/IdeaProjects/ITMO-prog-labs-4-semm/Practitioners/src/Practice6/Task6_3_2/windows1251file.txt", "windows-1251");
    }

    private static void readAndPrintFile(String fileName, String charsetName)
    {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, charsetName);
             BufferedReader br = new BufferedReader(isr))
        {

            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
