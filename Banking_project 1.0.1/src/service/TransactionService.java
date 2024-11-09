
package service;

import Exceptions.InvalidTransactionAmountException;
import Exceptions.TransactionFaliureException ; 

import dao.TransactionDAO;
import dao.TransactionDAOImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.TransactionHistoryUtil;


public class TransactionService {
    private final TransactionDAO transactionDAO ; 
    private final ExecutorService executorService ; 
    
    public TransactionService(){
        this.transactionDAO = new TransactionDAOImpl() ;
        this.executorService = Executors.newFixedThreadPool(5) ;
        
    }
    
    public Future<?> deposit(int accountId , double amount){
        
        
        return executorService.submit(()->{
         
            try { 
                transactionDAO.deposit(accountId , amount) ;
            } catch (InvalidTransactionAmountException | TransactionFaliureException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
                TransactionHistoryUtil.saveTransaction("Deposit" , accountId, amount) ; 
            
            
           
        }) ;
    }

    
    
    
    
    public Future<?> withdraw(int accountId , double amount){
        
        
        return executorService.submit(()->{
         
            try { 
                transactionDAO.withdraw(accountId , amount) ;
            } catch (InvalidTransactionAmountException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
                TransactionHistoryUtil.saveTransaction("Withdraw" , accountId, amount) ; 
            
            
           
        }) ;
    }
    
    
    
    
    
    public Future<?> transferFunds(int fromAccount, int toAccount, double amount){
        
        
        return executorService.submit(()->{
         
            try { 
                transactionDAO.transferFunds(fromAccount, toAccount, amount) ;
            } catch (InvalidTransactionAmountException ex) {
                Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
            }
                TransactionHistoryUtil.saveTransactionTransfer("Transfer" ,fromAccount ,toAccount, amount) ; 
            
            
           
        }) ;
    }
    
    
    
     public void shutDownExecutorService(){
        executorService.shutdown() ; 
    }
    
}
