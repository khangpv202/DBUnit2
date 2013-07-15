package com.qsoft.DBUnit.business.BankAccount;

import com.qsoft.DBUnit.business.Transaction.Transaction;
import com.qsoft.DBUnit.pesistent.dao.BankAccountDAO;
import com.qsoft.DBUnit.pesistent.model.BankAccountDTO;
import com.qsoft.DBUnit.pesistent.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * User: kpv
 * Date: 7/12/13
 * Time: 1:50 PM
 */
@Component
@Transactional
public class BankAccount
{
    @Autowired
    private static BankAccountDAO bankAccountDAO ;

    private final static String accountNumber = "123456789";


    public static BankAccountDTO open(String accountNumber) throws SQLException
    {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.create(account);
        return account;
    }

    public static BankAccountDTO getAccountNumber(String accountNumber) throws SQLException
    {
        return (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class,accountNumber);
    }

    public static TransactionDTO deposit(String accountNumber, int amount, String descreption) throws SQLException
    {
        //BankAccountDTO bankAccountDTO = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, accountNumber);
        System.out.println(accountNumber);
        System.out.println(bankAccountDAO);
        BankAccountDTO bankAccountDTO = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, accountNumber);
        System.out.println(bankAccountDTO);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.update(bankAccountDTO);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,descreption);
        Transaction.save(transaction);
        return transaction;
    }

    public static TransactionDTO withdraw(String accountNumber, int amount, String description) throws SQLException
    {
        BankAccountDTO bankAccountDTO = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, accountNumber);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.update(bankAccountDTO);
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transactionDTO);
        return  transactionDTO;
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return Transaction.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO>  getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return Transaction.getTransactionsOccurred(accountNumber, startTime, stopTime);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return Transaction.getTransactionsOccurred(accountNumber,numberNewestOfTransaction);
    }
}
