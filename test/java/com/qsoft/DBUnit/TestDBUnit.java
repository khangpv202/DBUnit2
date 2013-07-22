package com.qsoft.DBUnit;

import com.qsoft.DBUnit.business.BankAccount.BankAccount;
import com.qsoft.DBUnit.pesistent.dao.BankAccountDAO;
import com.qsoft.DBUnit.pesistent.model.BankAccountDTO;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * User: kpv
 * Date: 7/10/13
 * Time: 10:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:SpringConfigTest.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestDBUnit
{
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Autowired
    private BankAccount bankAccount;

    private Connection dbConnection;

    public static final String accountNumber = "123456789";
    public static final String newAccountNumber = "1234567890";
    private static final String resourcePath = new File("").getAbsolutePath() + "/src/test/resources";

    public static final String JDBC_DRIVER = org.postgresql.Driver.class.getName();
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/dbUnit";
    public static final String JDBC_SEVER_LOCATION = "localhost";
    public static final String JDBC_DATABASE = "dbUnit";
    public static final String JDBC_USERNAME = "dbUnit";
    public static final String JDBC_PASSWORD = "123456";

    // DBUnit setUp
    @Before
    public void cleanInsertData() throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        IDataSet dataSet = importData();
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DataSource initDS = dataSource();
        dbConnection = initDS.getConnection();
    }

    public IDataSet importData() throws Exception {
        String dataFile = resourcePath + "/dataset.xml";
        System.out.println(dataFile);
        return new FlatXmlDataSetBuilder().build(new FileInputStream(dataFile));
    }

    public DataSource dataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setServerName(JDBC_SEVER_LOCATION);
        dataSource.setDatabaseName(JDBC_DATABASE);
        dataSource.setUser(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        return dataSource;
    }

    @Test
    public void testFindAccountByAccountNumber()
    {
        BankAccountDTO bankAccountEntity = new BankAccountDTO(accountNumber,100l,"default");
        bankAccountEntity.setId(1);
        BankAccountDTO getBankAccount = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, accountNumber);
        assertEquals(bankAccountEntity,getBankAccount);
    }
    @Test
    public void testFindAccountWithInvalidAccountNumber(){
        BankAccountDTO bankAccountEntity = new BankAccountDTO(accountNumber,100l,"default");
        bankAccountDAO.create(bankAccountEntity);
        BankAccountDTO bankAccountGetFromDB = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, accountNumber+"abc");
        assertTrue(bankAccountGetFromDB==null);
    }
    @Test
    public void testCreateAccount(){
        BankAccountDTO bankAccountEntity = new BankAccountDTO(newAccountNumber,100l,"default");
        bankAccountDAO.create(bankAccountEntity);
        BankAccountDTO bankAccountGetFromDB = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class, bankAccountEntity.getAccount_number());
        System.out.println(bankAccountEntity);
        System.out.println(bankAccountGetFromDB);
        assertEquals(bankAccountEntity,bankAccountGetFromDB);
    }
    @Test
    public void testUpdateAfterDepositOrWithdraw() throws SQLException
    {
        BankAccountDTO bankAccountEntity = new BankAccountDTO(newAccountNumber,100l,"default");
        bankAccountDAO.create(bankAccountEntity);
        bankAccountEntity.setBalance(110l);
        bankAccountEntity.setDescription("not default");
        bankAccountDAO.update(bankAccountEntity);
        BankAccountDTO bankAccountGetFromDB = (BankAccountDTO) bankAccountDAO.findByAccountNumber(BankAccountDTO.class,bankAccountEntity.getAccount_number());
        assertEquals(bankAccountEntity,bankAccountGetFromDB);
    }
    @Test
    public void testDeleteAccount(){
        BankAccountDTO bankAccountEntity = new BankAccountDTO(newAccountNumber,100l,"default");
        bankAccountDAO.create(bankAccountEntity);
        bankAccountDAO.delete(bankAccountEntity);
        BankAccountDTO bankAccountGetFromDB = (BankAccountDTO) bankAccountDAO.findByObject(bankAccountEntity);
        assertTrue(bankAccountGetFromDB==null);
    }
    @Test
    public void testCreateTransaction(){

    }

}
