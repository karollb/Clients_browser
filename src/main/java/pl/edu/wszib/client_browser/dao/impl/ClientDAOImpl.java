package pl.edu.wszib.client_browser.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.client_browser.dao.IClientDAO;
import pl.edu.wszib.client_browser.model.Client;

import javax.persistence.NoResultException;

@Repository
public class ClientDAOImpl implements IClientDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewClient(final Client client) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteClient(final Client client) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public Client getClientById(final Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Client> query = session.createQuery("FROM pl.edu.wszib.client_browser.model.Client WHERE id = :id");
        query.setParameter("id", id);

        Client result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }
}
