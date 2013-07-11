package com.qsoft.DBUnit.pesistent.dao;

/**
 * User: kpv
 * Date: 7/10/13
 * Time: 4:06 PM
 */

public interface BankAccountDAO
{
    Object findById(Class clazz, String accountNumber);
    Object findByObject(Object obj);
    void delete(Object obj);
    void update(Object obj);
    void create(Object obj);
}
