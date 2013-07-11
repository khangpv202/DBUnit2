package com.qsoft.DBUnit;

import com.qsoft.DBUnit.pesistent.dao.BankAccountDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: kpv
 * Date: 7/10/13
 * Time: 4:04 PM
 */

public class MainAppTest
{
    public static void main(String[] args)
    {
        ApplicationContext appContex = new ClassPathXmlApplicationContext("SpringConfigTest.xml");
        BankAccountDAO bankAccountDAO = (BankAccountDAO)appContex.getBean("bankAccountDAOImpl");
        //.out.println(bankAccountDAO.findById(BankAccountDTO.class,0l));
        //BankAccountDTO bankAccountDTO = new BankAccountDTO("12345678",6000l,"new hehe");
        /*BankAccountDTO bankAccountDTO = (BankAccountDTO) bankAccountDAO.findById(BankAccountDTO.class,1l);
        bankAccountDTO.setBalance(6000);
        bankAccountDAO.update(bankAccountDTO);*/
        //the rest of functions is delete, it's not complete
    }
}
