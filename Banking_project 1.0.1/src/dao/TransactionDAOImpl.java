/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Exceptions.InvalidTransactionAmountException;
import Exceptions.TransactionFaliureException;
import utility.DBConnection ; 
import java.sql.* ;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TransactionDAOImpl implements TransactionDAO {

    /**
     *
     * @param accountId
     * @param amount
     * @throws InvalidTransactionAmountException
     * @throws Exceptions.TransactionFaliureException
     */
    @Override
    public synchronized void deposit(int accountId, double amount) throws InvalidTransactionAmountException ,TransactionFaliureException  {
        
        if(amount <= 0){
            throw new InvalidTransactionAmountException("Deposit amount must be greater than 0 "); 
        }
        
        try (Connection  con = DBConnection.getConnection()){
            CallableStatement st = con.prepareCall("{CALL deposit_fund(?,?)}");
            
               st.setInt(1, accountId);
               st.setDouble(2,amount) ; 
               st.execute();
                       
        }
        catch(SQLException e){
            throw new TransactionFaliureException("Deposite failed: " + e.getMessage()) ;
        }
    }

    @Override
    public synchronized void withdraw(int accountId, double amount) throws InvalidTransactionAmountException  {
         if(amount <= 0){
            throw new InvalidTransactionAmountException("Withdraw amount must be greater than 0 "); 
        }
        
        try (Connection  con = DBConnection.getConnection()){
            CallableStatement st = con.prepareCall("{CALL withdraw_fund(?,?)}");
            
               st.setInt(1, accountId);
               st.setDouble(2,amount) ; 
               st.execute();
                       
        }
        catch(SQLException e){
             try {
                 throw new TransactionFaliureException("Withdraw failed: " + e.getMessage()) ;
             } catch (TransactionFaliureException ex) {
                 Logger.getLogger(TransactionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }

    @Override
    public synchronized void transferFunds(int fromAccount, int toAccount, double amount) throws InvalidTransactionAmountException {
        
        if(amount <= 0){
            throw new InvalidTransactionAmountException("tranfer amount must be greater than 0 "); 
        }
        
        try (Connection  con = DBConnection.getConnection()){
            CallableStatement st = con.prepareCall("{CALL transfer_funds(?,?,?)}");
            
               st.setInt(1, fromAccount);
               st.setInt(2, toAccount) ; 
               st.setDouble(3, amount ) ; 
               
               st.execute();
                       
        }
        catch(SQLException e){
             try {
                 throw new TransactionFaliureException("transaction failed: " + e.getMessage()) ;
             } catch (TransactionFaliureException ex) {
                 Logger.getLogger(TransactionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        
    }
    
}
