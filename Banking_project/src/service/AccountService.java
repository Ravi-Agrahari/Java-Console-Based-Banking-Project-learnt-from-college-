
package service;

import Exceptions.InvalidAccountTypeException;
import dao.AccountDAO ;
import dao.AccountDAOImpl ; 
import java.sql.SQLException;
import model.Account;

public class AccountService {
    private final AccountDAO accountDAO; 
    
    public AccountService(){
        this.accountDAO = new AccountDAOImpl() ; 
    }
    
    
    public void createAccount(Account account) throws SQLException, InvalidAccountTypeException{
        accountDAO.creatAccount(account);
           
    }
    
    public void updateAccount(int old_cust_id , int new_cust_id) throws SQLException, InvalidAccountTypeException{
        accountDAO.updateAccount(old_cust_id , new_cust_id);
           
    }
    
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException{
        accountDAO.deleteAccount(cust_id);
           
    }
    
    
}
