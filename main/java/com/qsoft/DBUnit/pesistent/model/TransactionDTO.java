package com.qsoft.DBUnit.pesistent.model;

/**
 * User: kpv
 * Date: 7/12/13
 * Time: 1:52 PM
 */

public class TransactionDTO
{
    private  String accountNumber;
    private  double amount;
    private  String description;
    private long timestemp;

    public TransactionDTO(String accountNumber, int amount, String descreption)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = descreption;
    }

    public String toString(){
        return "accountNumber: "+accountNumber+"amount: "+ amount+"description: "+description+"timestemp: "+timestemp;
    }
    public long getTimestamp()
    {
        return timestemp;
    }

    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    public long getTimeStamp()
    {
        return this.timestemp;
    }

    public double amount()
    {
        return this.amount;
    }

    public String getDescription()
    {
        return description;
    }
}
