package com.qsoft.DBUnit.pesistent.dao.impl;

import com.qsoft.DBUnit.pesistent.dao.TransactionDAO;
import com.qsoft.DBUnit.pesistent.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * User: kpv
 * Date: 7/15/13
 * Time: 1:39 PM
 */
@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO
{
    @Autowired
    EntityManager entityManager;

    @Override
    public Object findByAccountNumber(Class clazz, String accountNumber)
    {
        Query query = entityManager.createQuery("select d from TransactionDTO d where d.accountNumber= :accountNumber");
        query.setParameter("accountNumber", accountNumber);
        if (query.getResultList().size() == 0)
        {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public Object findByObject(Object obj)
    {
        TransactionDTO transactionDTO = (TransactionDTO) obj;
        return entityManager.find(TransactionDTO.class, transactionDTO.getId());
    }

    @Override
    public void delete(Object obj)
    {
        entityManager.remove(obj);
    }

    @Override
    public void update(Object obj)
    {
        entityManager.merge(obj);
    }

    @Override
    public void create(Object obj)
    {
        entityManager.persist(obj);
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        Query query = entityManager.createQuery("select p from TransactionDTO p where p.accountNumber=:accountNumber");
        query.setParameter("accountNumber", accountNumber);
        return query.getResultList();
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        Query query = entityManager.createQuery("select p from TransactionDTO p where p.accountNumber=:accountNumber");
        query.setParameter("accountNumber", accountNumber);
        List<TransactionDTO> result = new ArrayList<TransactionDTO>();
        for (Object i : query.getResultList())
        {
            if (((TransactionDTO) i).getTimestamp() > startTime && ((TransactionDTO) i).getTimestamp() < stopTime)
            {
                result.add((TransactionDTO) i);
            }
        }
        return result;
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        Query query = entityManager.createQuery("select p from TransactionDTO p where p.accountNumber=:accountNumber");
        query.setParameter("accountNumber", accountNumber);
        List<TransactionDTO> result = new ArrayList<TransactionDTO>();
        int count = 0;
        int lengthOfListTransaction = query.getResultList().size();
        if (numberNewestOfTransaction < lengthOfListTransaction)
        {
            for (int i = 0; i < numberNewestOfTransaction; i++)
            {
                result.add((TransactionDTO) query.getResultList().get(lengthOfListTransaction - i - 1));
            }
        }
        else
        {
            for (int i = 0; i < lengthOfListTransaction; i++)
            {
                result.add((TransactionDTO) query.getResultList().get(lengthOfListTransaction - 1 - i));
            }
        }
        return result;
    }
}
