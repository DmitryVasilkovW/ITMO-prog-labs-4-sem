package Tasks.Practitioners.Practice1.Task3;

public class Main
{
    public static void main(String[] args)
    {
        DisplayingCharacters Display = new DisplayingCharacters();

        char start = 'a';
        char end = 'z';

        Display.DisplayingCharactersInaGap(start, end);

        System.out.println();

        NumberChanger changer = new NumberChanger();

        long longstart = 0L;
        long longend = 100000000L;

        int intstart = 0;
        int intend = 100000000;

        System.out.println(changer.CycleTimeCalculation(longstart, longend));

        System.out.println(changer.CycleTimeCalculation(intstart, intend));
    }
}
