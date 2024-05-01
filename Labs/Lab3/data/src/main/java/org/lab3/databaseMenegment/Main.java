package org.lab3.databaseMenegment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{

    public static void main(String[] args)
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(Initial.class);
        context.register(AppConfig.class);
        context.refresh();

        var a = context.getBean(Initial.class);

        a.getUpSql();
    }
}
