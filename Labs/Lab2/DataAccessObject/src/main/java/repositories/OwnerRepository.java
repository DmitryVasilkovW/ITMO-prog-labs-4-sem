package repositories;

import abstractions.IOwnerRepository;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class OwnerRepository implements IOwnerRepository
{
    private SessionFactory _sessionFactory;

    public OwnerRepository()
    {
        var configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        _sessionFactory = configuration.buildSessionFactory();
    }

    public Owner getOwnerById(int id)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.get(Owner.class, id);
        }
    }

    public List<Owner> getOwnersByName(String name)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Owner where name = :name", Owner.class)
                    .setParameter("name", name)
                    .list();
        }
    }

    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        try (Session session = _sessionFactory.openSession())
        {
            return session.createQuery("from Owner where birthDate = :birthDate", Owner.class)
                    .setParameter("birthDate", birthDate)
                    .list();
        }
    }

    public void updateOwnerName(int id, String newName)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Owner set name = :newName where id = :id")
                    .setParameter("newName", newName)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void updateOwnerBirthDate(int id, LocalDate newBirthDate)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("update Owner set birthDate = :newBirthDate where id = :id")
                    .setParameter("newBirthDate", newBirthDate)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteOwner(int ownerId)
    {
        try (Session session = _sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();

            session.createNativeQuery("delete from owners_cats where owner_id = :ownerId")
                    .setParameter("ownerId", ownerId)
                    .executeUpdate();

            session.createQuery("delete from Cat where owner.id = :ownerId")
                    .setParameter("ownerId", ownerId)
                    .executeUpdate();

            session.createQuery("delete from Owner where id = :ownerId")
                    .setParameter("ownerId", ownerId)
                    .executeUpdate();

            transaction.commit();
        }
    }
}
