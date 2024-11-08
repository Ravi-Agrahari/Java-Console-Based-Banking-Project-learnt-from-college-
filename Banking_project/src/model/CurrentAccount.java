
package model;


public class CurrentAccount extends Account {
    
    private double OverDraftLimit ; 

    // constructor
    public CurrentAccount( int accountId, int customerId, Bank bank, String accountType, double balance ,double OverDraftLimit) {
        super(accountId, customerId, bank, accountType, balance);
        this.OverDraftLimit = OverDraftLimit;
    }

    
    // getters and setters 
    public double getOverDraftLimit() {
        return OverDraftLimit;
    }

    public void setOverDraftLimit(double OverDraftLimit) {
        this.OverDraftLimit = OverDraftLimit;
    }

    
    
    // Overriding abstract methods 
    
    @Override
    public String getAccountDetails() {
        
        return "This is current Account" + " with over draft limit : " + this.OverDraftLimit ; 
    }

    
    // overriding the toString method to print currentAccount detatils 
    @Override
    public String toString() {
        return "CurrentAccount Details : Account Id :" + this.getAccountId() + " Customer Id: " + this.getCustomerId() + " Bank: "  + this.getBank() + " Account type : " + this.getAccountType() + " Balance: " + this.getBalance() + " OverDraftLimt: " + this.getOverDraftLimit() ;
    }
    
    
    
    
    
    
}
