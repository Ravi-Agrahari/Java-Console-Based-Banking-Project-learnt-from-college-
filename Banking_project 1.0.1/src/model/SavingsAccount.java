
package model;

public class SavingsAccount extends Account{
    
    private double interestRate ; 

    
 // Constructor
    public SavingsAccount(int accountId, int customerId, Bank bank, String accountType, double balance , double interestRate ) {
        super(accountId, customerId, bank, accountType, balance);
        this.interestRate = interestRate;
    }
    
    
// Getter and setters
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

 // overriding the abstract method 
    @Override
    public String getAccountDetails() {
        
        return "this is savings account" + " with interest rate : " + this.interestRate; 
    }
    
    // overriding the toString method to print currentAccount detatils 
    @Override
    public String toString() {
        return "CurrentAccount Details : Account Id :" + this.getAccountId() + " Customer Id: " + this.getCustomerId() + " Bank: "  + this.getBank() + " Account type : " + this.getAccountType() + " Balance: " + this.getBalance() + " Interest rate: " + this.getInterestRate() ;
    }
    
    
    
   
}
