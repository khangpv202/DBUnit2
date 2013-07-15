package com.qsoft.DBUnit.business.Transaction;

import com.qsoft.DBUnit.pesistent.dao.TransactionDAO;
import com.qsoft.DBUnit.pesistent.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

/**
 * User: kpv
 * Date: 7/12/13
 * Time: 1:48 PM
 */

public class Transaction
{
    @Autowired
    private static TransactionDAO transactionDAO;


    public static void save(TransactionDTO transactionDTO) throws SQLException
    {
        transactionDAO.create(transactionDTO);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber,startTime,stopTime);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber,numberNewestOfTransaction);
    }
}
