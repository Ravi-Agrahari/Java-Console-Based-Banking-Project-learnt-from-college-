
package dao;

import Exceptions.BankingException;
import Exceptions.InvalidAccountTypeException;

import model.Account ; 
import java.sql.* ; 


public interface AccountDAO {
    
    /**
     *
     * @param account
     * @throws java.sql.SQLException
     * @throws Exceptions.InvalidAccountTypeException
     */
    void creatAccount(Account account ) throws SQLException, InvalidAccountTypeException; 
    
    
    public void updateAccountType(int accountId, String newAccountType) throws SQLException, InvalidAccountTypeException ;
    
    
    public void updateAccountBalance(int accountId, double newBalance) throws SQLException, InvalidAccountTypeException ;
    
    
    public void updateCustomerId(int accountId, int newCustomerId) throws SQLException, InvalidAccountTypeException ;
    
    
    public Account getAccountDetails(int accountId) throws SQLException, BankingException ;
    
    
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException ; 
  
   
}
