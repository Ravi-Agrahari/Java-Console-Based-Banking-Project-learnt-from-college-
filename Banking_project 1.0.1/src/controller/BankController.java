
package controller;

import Exceptions.BankingException;
import Exceptions.InvalidAccountTypeException;
import service.AccountService;
import java.io.BufferedReader ; 
import java.io.IOException;
import java.io.InputStreamReader ; 
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.Future;
import model.Account;
import model.Bank;
import model.SavingsAccount ;
import model.CurrentAccount ;
import service.BankService;
import service.TransactionService;


/**
 *
 * @author Dell
 */
public class BankController {
    private final AccountService accountService ;
    private final BankService bankService ;
    private final TransactionService transactionService ;
//    private final ExecutorService executorService ; 
    private final BufferedReader br ;
    
    public BankController(){
        this.accountService = new AccountService() ; 
        this.bankService = new BankService() ;
        this.transactionService = new TransactionService() ; 
        
        
        this.br = new BufferedReader(new InputStreamReader(System.in)) ; 
    }
   
    
    public void start() throws IOException , NumberFormatException, SQLException, BankingException, InvalidAccountTypeException, InterruptedException, ExecutionException{
        boolean running = true ; 
        
        while(running){
            displayMenu();
            int choice = Integer.parseInt(br.readLine()) ;  // important to know autoboxing ... 
            
            switch(choice){
                case 1:
                    createAccount() ; 
                    break; 
                
                case 2 :
                    updateAccount() ; 
                    break ; 
                    
                case 3 :
                    viewAccountDetails() ; 
                    break ;
                 
                case 4 :
                    deleteAccount() ; 
                    break ; 
                case 5 :
                    deposit();
                    break ; 
                
                case 6 :
                    withdraw() ;
                    break ; 
                
                case 7 :
                    tratnsferFunds();
                    break ;
                 
                    
                case 0 :
                   running = false ; 
                   transactionService.shutDownExecutorService();
                   System.out.println("Exiting the app! See you again ...") ; 
                   break ; 
                default :
                    System.out.println("Invalid choic , Choose again ") ; 
                  
            }
        }
    }
    
    
    
    
    public void displayMenu(){
        System.out.println("---------- Welcome to Bishal Banking Application ----------"); 
        System.out.println("1.Create Account");
        System.out.println("2.Update Account");
        System.out.println("3.View Account Details");
        System.out.println("4.Delete Account");
        System.out.println("5.Deposit Amount") ; 
        System.out.println("6.Withdraw Amount");
        System.out.println("7.Transfer Amount") ; 
        
        System.out.println("0.Exit");
        
        System.out.println("Enter the choice : ");
       
    }
    
    
    
    
// function to createAccount .... 
    private void createAccount() throws IOException, SQLException, BankingException, InvalidAccountTypeException {
        
        System.out.println("Enter Customer ID: ");
        int cusId = Integer.parseInt(br.readLine()) ;
        
        System.out.println("Enter the bank ID: ");
        int bankId = Integer.parseInt(br.readLine()) ; 
        
        Bank bank = bankService.getBankById(bankId) ;
        
        
        System.out.println("Enter Account Type(Savings / Current): ") ; 
        String accountType = br.readLine()  ; 
        
        System.out.println("Enter Initial Balance: ");
        double bal = Double.parseDouble(br.readLine());
        
        
        if("Savings".equalsIgnoreCase(accountType)){
            System.out.println("Enter Interest Rate: ");
            double interest = Double.parseDouble(br.readLine()); 
            accountService.createAccount(new SavingsAccount(0,cusId,bank,accountType,bal,interest));
            
        }
        else if("Current".equalsIgnoreCase(accountType)){
            System.out.println("Enter OverdraftLimit: ") ; 
            double overdraft = Double.parseDouble(br.readLine()) ; 
            accountService.createAccount(new CurrentAccount(0,cusId,bank,accountType,bal,overdraft));
        }
        else{
            System.out.println("Invalid Account type");
        }
    }

    
    
    
    
 
 // method to updateAccount 
    private void updateAccount() throws SQLException, InvalidAccountTypeException {
        
        Scanner scanner = new Scanner(System.in);

        // Prompt user for account details to update
        System.out.println("Enter Account ID to update:");
        int accountId = scanner.nextInt();
        
        System.out.println("What would you like to update?");
        System.out.println("1. Account Type");
        System.out.println("2. Balance");
        System.out.println("3. Customer ID");
        
        int updateChoice = scanner.nextInt();
        
        switch (updateChoice) {
            case 1:
                System.out.println("Enter new Account Type (e.g., Savings, Current):");
                String newAccountType = scanner.next();
                accountService.updateAccountType(accountId, newAccountType);
                break;
            case 2:
                System.out.println("Enter new Balance:");
                double newBalance = scanner.nextDouble();
                accountService.updateAccountBalance(accountId, newBalance);
                break;
            case 3:
                System.out.println("Enter new Customer ID:");
                int newCustomerId = scanner.nextInt();
                accountService.updateCustomerId(accountId, newCustomerId);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    
    
    
    private void viewAccountDetails() throws SQLException, BankingException {
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for account ID
        System.out.println("Enter Account ID to view details:");
        int accountId = scanner.nextInt();

        // Get account details from the service layer
        Account account = accountService.getAccountDetails(accountId);
        
        // Display the account details
        if (account != null) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Customer ID: " + account.getCustomerId());
        } else {
            System.out.println("Account not found with ID: " + accountId);
        }
    }
    
    
    
    private void deleteAccount() throws SQLException, InvalidAccountTypeException {
        Scanner sc = new Scanner(System.in) ;
        
        System.out.println("Enter the cust_id of account to be delete : "); 
        int cust_id = sc.nextInt() ; 
        
        accountService.deleteAccount(cust_id);
        
    }
    
    
    
    
    public void deposit() throws IOException, InterruptedException, ExecutionException{
        System.out.println("Enter Account ID: ") ; 
        int id = Integer.parseInt(br.readLine()) ; 
        System.out.println("Enter the Amount to be Deposited:") ; 
        double amount = Double.parseDouble(br.readLine()) ; 
        
        Future<?> future = transactionService.deposit(id, amount) ; 
        future.get();  // wait for the deposit opearation to be compete
        
    }
    
    
    public void withdraw() throws IOException, InterruptedException, ExecutionException{
        System.out.println("Enter Account ID: ") ; 
        int id = Integer.parseInt(br.readLine()) ; 
        System.out.println("Enter the Amount to be Withdrawn :") ; 
        double amount = Double.parseDouble(br.readLine()) ; 
        
        Future<?> future = transactionService.withdraw(id, amount) ; 
        future.get();  // wait for the deposit opearation to be compete
        
    }
    
    public void tratnsferFunds() throws IOException, InterruptedException, ExecutionException{
        
        
        System.out.println("Enter From Account ID: ") ; 
        int fromid = Integer.parseInt(br.readLine()) ; 
        
        System.out.println("Enter To Account ID: ") ; 
        int toid = Integer.parseInt(br.readLine()) ;
        
        System.out.println("Enter the Amount to be transfered :") ; 
        double amount = Double.parseDouble(br.readLine()) ; 
        
        Future<?> future = transactionService.transferFunds(fromid , toid, amount) ; 
        future.get();  // wait for the deposit opearation to be compete
        
    }
    
    
}
