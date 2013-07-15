package com.qsoft.DBUnit.pesistent.model;

import javax.persistence.*;

/**
 * User: kpv
 * Date: 7/10/13
 * Time: 4:07 PM
 */
@Entity
@Table(name= "bank_account")
@SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
public class BankAccountDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "account_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "account_number")
    private String account_number;
    @Column(name = "balance")
    private long balance;
    @Column(name = "description")
    private String description;
    @Column(name = "timestamps")
    private long timestamps;

    public BankAccountDTO()
    {
    }

    public BankAccountDTO(String accountNumber)
    {
        this.account_number= accountNumber;

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public long getTimestamps()
    {
        return timestamps;
    }

    public void setTimestamps(long timestamps)
    {
        this.timestamps = timestamps;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getBalance()
    {
        return balance;
    }

    public void setBalance(long balance)
    {
        this.balance = balance;
    }

    public String getAccount_number()
    {
        return account_number;
    }

    public void setAccount_number(String account_number)
    {
        this.account_number = account_number;
    }

    public BankAccountDTO(String account_number, long balance, String description)
    {
        this.account_number = account_number;
        this.balance = balance;
        this.description = description;
        this.timestamps = 0;
    }
    public String toString(){
        return "id: "+id+" accountNumber: "+account_number+" balance: "+balance+
                " description: "+description+" timestamps: "+timestamps;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BankAccountDTO))
        {
            return false;
        }

        BankAccountDTO that = (BankAccountDTO) o;

        if (balance != that.balance)
        {
            return false;
        }
        if (timestamps != that.timestamps)
        {
            return false;
        }
        if (account_number != null ? !account_number.equals(that.account_number) : that.account_number != null)
        {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return 1;
    }
}
