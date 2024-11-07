
package model;

public class SavingsAccount extends Account{
    
    private double interestRate ; 

    public SavingsAccount(double interestRate, int accountId, int customerId, Bank bank, String accountType, double balance) {
        super(accountId, customerId, bank, accountType, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    
   
}
