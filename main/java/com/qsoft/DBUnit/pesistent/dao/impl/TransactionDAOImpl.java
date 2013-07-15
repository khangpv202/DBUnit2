package com.qsoft.DBUnit.pesistent.dao.impl;

import com.qsoft.DBUnit.pesistent.dao.TransactionDAO;
import com.qsoft.DBUnit.pesistent.model.TransactionDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public Object findByAccountNumber(Class clazz, String accountNumber)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object findByObject(Object obj)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Object obj)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Object obj)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Object obj)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
