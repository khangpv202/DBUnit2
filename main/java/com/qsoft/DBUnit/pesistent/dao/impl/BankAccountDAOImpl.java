package com.qsoft.DBUnit.pesistent.dao.impl;

import com.qsoft.DBUnit.pesistent.dao.BankAccountDAO;
import com.qsoft.DBUnit.pesistent.model.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * User: kpv
 * Date: 7/10/13
 * Time: 4:09 PM
 */
@Repository
@Transactional
public class BankAccountDAOImpl implements BankAccountDAO
{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Object findByAccountNumber(Class clazz, String accountNumber)
    {
        //System.out.println(entityManager);
        Query query = entityManager.createQuery("select d from BankAccountDTO d where d.account_number= :accountNumber");
        query.setParameter("accountNumber",accountNumber);
       if(query.getResultList().size()==0)
           return null;
       return query.getResultList().get(0);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object findByObject(Object obj)
    {
        BankAccountDTO bankAccountDTO = (BankAccountDTO) obj;
        return entityManager.find(BankAccountDTO.class,bankAccountDTO.getId());
    }

    @Override
    public void delete(Object obj)
    {
        entityManager.remove((BankAccountDTO)obj);
        entityManager.flush();

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
}
