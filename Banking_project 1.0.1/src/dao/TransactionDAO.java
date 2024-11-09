/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Exceptions.InvalidTransactionAmountException;
import Exceptions.TransactionFaliureException;

/**
 *
 * @author Dell
 */
public interface TransactionDAO {
    
    void deposit(int accountId , double amount) throws InvalidTransactionAmountException ,TransactionFaliureException  ;  
    
    void withdraw(int accountId , double amount) throws InvalidTransactionAmountException    ; 
    
    void transferFunds(int fromAccount, int toAccount, double amount) throws InvalidTransactionAmountException ; 
}
