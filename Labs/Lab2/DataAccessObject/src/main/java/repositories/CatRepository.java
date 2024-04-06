package repositories;

import abstractions.ICatRepository;
import models.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class CatRepository implements ICatRepository
{
    private SessionFactory _sessionFactory;

    public CatRepository()
    {
        var configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        _sessionFactory = configuration.buildSessionFactory();
    }

    public Cat getCatById(int id)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.get(Cat.class, id);
        }
    }

    public List<Cat> getCatsByName(String name)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Cat where name = :name", Cat.class)
                    .setParameter("name", name)
                    .list();
        }
    }

    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Cat where birthDate = :birthDate", Cat.class)
                    .setParameter("birthDate", birthDate)
                    .list();
        }
    }

    public List<Cat> getCatsByBreed(String breed)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Cat where breed = :breed", Cat.class)
                    .setParameter("breed", breed)
                    .list();
        }
    }

    public List<Cat> getCatsByColor(String color)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Cat where color = :color", Cat.class)
                    .setParameter("color", color)
                    .list();
        }
    }

    public void updateCatName(int id, String newName)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Cat set name = :newName where id = :id")
                    .setParameter("newName", newName)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Cat set birthDate = :newBirthDate where id = :id")
                    .setParameter("newBirthDate", newBirthDate)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void updateCatBreed(int id, String breed)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Cat set birthDate = :breed where id = :id")
                    .setParameter("breed", breed)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void updateCatColor(int id, String color)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Cat set birthDate = :color where id = :id")
                    .setParameter("color", color)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteCat(int id)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();

            session.createNativeQuery("delete from owners_cats where cat_id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.createQuery("delete from Cat where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            transaction.commit();
        }
    }
}
