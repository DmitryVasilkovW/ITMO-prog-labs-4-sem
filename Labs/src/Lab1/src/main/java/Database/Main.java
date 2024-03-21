package Database;

import Database.Repositories.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{

    public static void a()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(UserRepository.class);
        rep.AddUser("tmp", "tmp", "2392281488");
    }

    public static void main(String[] args)
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(UserRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var rep = context.getBean(UserRepository.class);

//        rep.AddAddress("a", "239", 239, 1488, "2392281488");
//        rep.AddPassportDetails(239239, 228, "2392281488");

        var user = rep.GetUserByPassword("2392281488");

        System.out.println(user.get_name() + " " + user.get_surname());
    }
}
