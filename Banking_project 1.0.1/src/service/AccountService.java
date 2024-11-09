
package service;

import Exceptions.BankingException;
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
    
    
    
    public void updateAccountType(int accountId, String newAccountType) throws SQLException, InvalidAccountTypeException {
        // Business logic to validate the account type and update it
        if (newAccountType == null || newAccountType.isEmpty()) {
            throw new InvalidAccountTypeException("Account type cannot be empty.");
        }
        // Call DAO method to update the account type in the database
        accountDAO.updateAccountType(accountId, newAccountType);
    }

    
    
    public void updateAccountBalance(int accountId, double newBalance) throws SQLException, InvalidAccountTypeException {
        // Business logic to update the account balance
        if (newBalance < 0) {
            throw new InvalidAccountTypeException("Balance cannot be negative.");
        }
        accountDAO.updateAccountBalance(accountId, newBalance);
    }

    
    
    public void updateCustomerId(int accountId, int newCustomerId) throws SQLException, InvalidAccountTypeException {
        // Business logic to update the customer ID
        accountDAO.updateCustomerId(accountId, newCustomerId);
    }
    
    
    
    
    public Account getAccountDetails(int accountId) throws SQLException, BankingException {
        // Call DAO method to get account details by ID
        return accountDAO.getAccountDetails(accountId);
    }
    
    
    
    
    
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException{
        accountDAO.deleteAccount(cust_id);
           
    }
    
    
}
