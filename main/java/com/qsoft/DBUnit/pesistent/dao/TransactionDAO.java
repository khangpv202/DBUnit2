package com.qsoft.DBUnit.pesistent.dao;

import com.qsoft.DBUnit.pesistent.model.TransactionDTO;

import java.util.List;

/**
 * User: kpv
 * Date: 7/15/13
 * Time: 1:38 PM
 */

public interface TransactionDAO
{
    Object findByAccountNumber(Class clazz, String accountNumber);
    Object findByObject(Object obj);
    void delete(Object obj);
    void update(Object obj);
    void create(Object obj);
    List<TransactionDTO> getTransactionsOccurred(String accountNumber);
    List<TransactionDTO> getTransactionsOccurred(String accountNumber,long startTime,long stopTime);
    List<TransactionDTO> getTransactionsOccurred(String accountNumber,int numberNewestOfTransaction);
}
