
/*
    We are using abstraction 
    Defining Account as abstract class , which is to be inherited by SavingsAccount , CurrentAccount 

*/

package model;

public abstract class Account {
    private int accountId ; 
    private int customerId ; 
    private Bank bank ; 
    private String accountType ;
    private double balance ; 
    
    
// Constructor
    public Account(int accountId, int customerId, Bank bank, String accountType, double balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.bank = bank;
        this.accountType = accountType;
        this.balance = balance;
    }

   // Getters and Setters ...
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public abstract String getAccountDetails() ; 
    
    
}
