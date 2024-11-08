
package model;

import java.util.Date;


public class DepositTransaction extends Transaction {
   public String depositMethod ; 

    public DepositTransaction(String depositMethod, int transactionId, int accountId, String transactionType, double amount, Date transactionDate) {
        super(transactionId, accountId, transactionType, amount, transactionDate);
        this.depositMethod = depositMethod;
    }

    public String getDepositMethod() {
        return depositMethod;
    }

    public void setDepositMethod(String depositMethod) {
        this.depositMethod = depositMethod;
    }
    
    
    @Override 
    public String getTransactionDetails(){
        
        return "Transaction type is " + this.depositMethod ;
    }
    
   
}
