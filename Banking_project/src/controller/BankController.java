/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Exceptions.BankingException;
import Exceptions.InvalidAccountTypeException;
import service.AccountService;
import java.io.BufferedReader ; 
import java.io.IOException;
import java.io.InputStreamReader ; 
import java.sql.SQLException;
import java.util.Scanner;
import model.Account;
import model.Bank;
import model.SavingsAccount ;
import model.CurrentAccount ;
import service.BankService;


/**
 *
 * @author Dell
 */
public class BankController {
    private final AccountService accountService ;
    private final BankService bankService ;
    private final BufferedReader br ;
    
    public BankController(){
        this.accountService = new AccountService() ; 
        this.bankService = new BankService() ;
        this.br = new BufferedReader(new InputStreamReader(System.in)) ; 
    }
   
    
    public void start() throws IOException , NumberFormatException, SQLException, BankingException, InvalidAccountTypeException{
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
                    deleteAccount() ; 
                    break ; 
                    
                case 0 :
                   running = false ; 
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
        System.out.println("3.Delete Account");
        
        System.out.println("0.Exit");
        
        System.out.println("Enter the choice : ");
       
    }
    
    
    
    

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

    
    private void updateAccount() throws SQLException, InvalidAccountTypeException {
        Scanner sc = new Scanner(System.in) ;
        
        System.out.println("Enter the old cust_id : "); 
        int old_cust_id = sc.nextInt() ; 
        
        System.out.println("Enter the new cust_id : "); 
        int new_cust_id = sc.nextInt() ;
        
        accountService.updateAccount(old_cust_id , new_cust_id);
        
    }
    
    private void deleteAccount() throws SQLException, InvalidAccountTypeException {
        Scanner sc = new Scanner(System.in) ;
        
        System.out.println("Enter the cust_id of account to be delete : "); 
        int cust_id = sc.nextInt() ; 
        
        accountService.deleteAccount(cust_id);
        
    }
    
    
}
