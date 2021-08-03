package pl.edu.wszib.client_browser.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.client_browser.dao.ICustomerDAO;
import pl.edu.wszib.client_browser.model.Customer;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewCustomer(final Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(customer);
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
    public void deleteCustomer(final Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(customer);
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
    public Customer getCustomerById(final Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.client_browser.model.Customer WHERE id = :id");
        query.setParameter("id", id);

        Customer result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {

        }
        session.close();
        return result;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.client_browser.model.Customer");
        List<Customer> customers = query.getResultList();

        session.close();
        return customers;
    }
}
