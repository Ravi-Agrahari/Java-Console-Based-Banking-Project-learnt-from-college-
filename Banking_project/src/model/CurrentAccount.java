
package model;


public class CurrentAccount extends Account {
    
    private double OverDraftLimit ; 

    public CurrentAccount(double OverDraftLimit, int accountId, int customerId, Bank bank, String accountType, double balance) {
        super(accountId, customerId, bank, accountType, balance);
        this.OverDraftLimit = OverDraftLimit;
    }

    public double getOverDraftLimit() {
        return OverDraftLimit;
    }

    public void setOverDraftLimit(double OverDraftLimit) {
        this.OverDraftLimit = OverDraftLimit;
    }
    
    
}
