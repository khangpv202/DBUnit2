package com.qsoft.DBUnit.pesistent.model;

import javax.persistence.*;

/**
 * User: kpv
 * Date: 7/12/13
 * Time: 1:52 PM
 */
@Entity
@Table(name= "transaction")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
public class TransactionDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "account_number")
    private  String accountNumber;
    @Column(name = "amount")
    private  double amount;
    @Column(name = "description")
    private  String description;
    @Column(name = "timestemps")
    private long timestemp;

    public TransactionDTO(String accountNumber, int amount, String descreption)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = descreption;
        this.timestemp = System.currentTimeMillis();
    }

    public TransactionDTO()
    {
    }

    public String toString(){
        return "accountNumber: "+accountNumber+"amount: "+ amount+"description: "+description+"timestemp: "+timestemp;
    }
    public long getTimestamp()
    {
        return timestemp;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public long getTimestemp()
    {
        return timestemp;
    }

    public void setTimestemp(long timestemp)
    {
        this.timestemp = timestemp;
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
