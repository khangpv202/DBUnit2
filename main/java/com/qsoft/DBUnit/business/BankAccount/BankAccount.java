package com.qsoft.DBUnit.business.BankAccount;

import com.qsoft.DBUnit.pesistent.dao.BankAccountDAO;
import com.qsoft.DBUnit.pesistent.model.BankAccountDTO;
import com.qsoft.DBUnit.pesistent.model.TransactionDTO;
import org.h2.mvstore.TransactionStore;

import java.sql.SQLException;
import java.util.List;

/**
 * User: kpv
 * Date: 7/12/13
 * Time: 1:50 PM
 */

public class BankAccount
{
    private BankAccountDAO bankAccountDAO ;


    public static BankAccountDTO open(String accountNumber) throws SQLException
    {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        return account;
    }

    public static BankAccountDTO getAccountNumber(String accountNumber) throws SQLException
    {
        return bankAccountDAO.getAccountNumber(accountNumber);
    }

    public static TransactionDTO deposit(String accountNumber, int amount, String descreption) throws SQLException
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccountNumber(accountNumber);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,descreption);
        Transaction.save(transaction);
        return transaction;
    }

    public static TransactionDTO withdraw(String accountNumber, int amount, String description) throws SQLException
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccountNumber(accountNumber);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transactionDTO);
        return  transactionDTO;
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return TransactionStore.Transaction.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO>  getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return TransactionStore.Transaction.getTransactionsOccurred(accountNumber, startTime, stopTime);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return Transaction.getTransactionsOccurred(accountNumber,numberNewestOfTransaction);
    }
}
