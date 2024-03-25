package Scenarios;


import Users.Entites.User;

public class TMP
{
    public static void main(String[] args)
    {
        var user = new User("a", "a", null, null);

        System.out.println(user.get_name() + " " + user.get_surname());
    }
}
