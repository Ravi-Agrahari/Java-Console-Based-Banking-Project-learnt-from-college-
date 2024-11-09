
package dao;
import Exceptions.BankingException;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;

import model.Bank ; 
import service.BankService ; 


import java.sql.* ; 
import Exceptions.InvalidAccountTypeException ; 
import model.CurrentAccount;
import utility.DBConnection;

public class AccountDAOImpl implements AccountDAO {

    private final BankService bankService = new BankService() ;
    
    
    @Override
    public void creatAccount(Account account) throws SQLException,InvalidAccountTypeException  {
        String sql = "insert into Account(cutomer_id , bank_id , account_type ,balance) values (?,?,?,?)" ; 
        
        try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql); 
                       
            )
            
        {
            ps.setInt(1, account.getCustomerId()) ;
            ps.setInt(2, account.getBank().getBank_id()) ; 
            ps.setString(3, account.getAccountType()) ; 
            ps.setDouble(4, account.getBalance()) ; 
            
            int result = ps.executeUpdate() ; 
            
            if(result == 0){
                throw new InvalidAccountTypeException(" Account type not recognized ") ; 
            }
        } 
                
    }
    
    
    
    
    
    
    @Override
     // Method to update account type in the database
    public void updateAccountType(int accountId, String newAccountType) throws SQLException, InvalidAccountTypeException {
        String sql = "UPDATE account SET account_type = ? WHERE account_id = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newAccountType);
            ps.setInt(2, accountId);
            int result = ps.executeUpdate();

            if (result == 0) {
                throw new InvalidAccountTypeException("Account type update failed. Account not found.");
            }
        }
    }

    
    
    @Override
    // Method to update account balance in the database
    public void updateAccountBalance(int accountId, double newBalance) throws SQLException, InvalidAccountTypeException {
        String sql = "UPDATE account SET balance = ? WHERE account_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);
            int result = ps.executeUpdate();

            if (result == 0) {
                throw new InvalidAccountTypeException("Balance update failed. Account not found.");
            }
        }
    }
    
    
    
    

    @Override 
    // Method to update customer ID in the database
    public void updateCustomerId(int accountId, int newCustomerId) throws SQLException, InvalidAccountTypeException {
        String sql = "UPDATE account SET cutomer_id = ? WHERE account_id = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, newCustomerId);
            ps.setInt(2, accountId);
            int result = ps.executeUpdate();

            if (result == 0) {
                throw new InvalidAccountTypeException("Customer ID update failed. Account not found.");
            }
        }
    }
    
    
    
    
    @Override
    public Account getAccountDetails(int accountId) throws SQLException, BankingException {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        Account account = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Retrieve the common fields
                int id = rs.getInt("account_id");
                int bank_id = rs.getInt("bank_id") ;
                
                Bank bank = bankService.getBankById(bank_id);
                
                String type = rs.getString("account_type");
                double balance = rs.getDouble("balance");
                int customerId = rs.getInt("customer_id");

                // Instantiate the correct Account subclass based on account_type
                if ("Current".equalsIgnoreCase(type)) {
                    // For Current Account, fetch the overdraft limit
                    double overdraftLimit = rs.getDouble("overdraft_limit");
                    account = new CurrentAccount(id, customerId, bank ,  type, balance, overdraftLimit);
                } else if ("Savings".equalsIgnoreCase(type)) {
                    // For Savings Account, fetch the interest rate
                    double interestRate = rs.getDouble("interest_rate");
                    account = new SavingsAccount(id, customerId, bank, type,   balance,  interestRate);
                } else {
                    throw new BankingException("Invalid account type: " + type);
                }
            } else {
                throw new BankingException("Account with ID " + accountId + " not found.");
            }
        }
        return account;
    }
    
    
    
    
    @Override
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException {
        
        String sql = "delete from account where cutomer_id = ? " ; 
        
        try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);          
            )
            
        {    
            ps.setInt(1, cust_id);
 
            int result = ps.executeUpdate() ; 
            
            if(result == 0){
                throw new InvalidAccountTypeException(" Something error in execution ") ; 
            }
            
        } 
    }
}
