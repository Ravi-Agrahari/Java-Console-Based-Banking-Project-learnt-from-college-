/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class WithdrawTransaction extends Transaction {
    
   private String withdrawalMethod ; 

    public WithdrawTransaction(String withdrawalMethod, int transactionId, int accountId, String transactionType, double amount, Date transactionDate) {
        super(transactionId, accountId, transactionType, amount, transactionDate);
        this.withdrawalMethod = withdrawalMethod;
    }

    public String getWithdrawalMethod() {
        return withdrawalMethod;
    }

    public void setWithdrawalMethod(String withdrawalMethod) {
        this.withdrawalMethod = withdrawalMethod;
    }
    
// overriding the abstract method 
    @Override
    public String getTransactionDetails(){
        return "Transaction type is " + this.withdrawalMethod ; 
    }
    

}
