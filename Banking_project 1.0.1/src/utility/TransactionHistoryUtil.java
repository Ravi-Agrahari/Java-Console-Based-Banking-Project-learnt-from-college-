/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Dell
 */

import java.io.FileWriter;
import java.io.BufferedWriter ; 
import java.io.BufferedReader ;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat ; 
import java.util.ArrayList;
import java.util.Date ; 
import java.util.List;

public class TransactionHistoryUtil {
    private static final String FILE_PATH = "transaction_history.txt" ; 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ; 
    
    
    //Method to save transaction details 
    public static void saveTransaction(String transactionType , int accountId , double amount ) {
        String timestamp = dateFormat.format(new Date()); 
        String record = String.format("%s | %s | Account ID : %d | Amount : %.2f" , timestamp , transactionType , accountId , amount  ) ; 
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH , true          ))){
            writer.write(record);
            writer.newLine(); 
        }
        catch(IOException e){
            System.err.println("Error writing to transaction history file") ;

        }
    } 
    
    
     public static void saveTransactionTransfer(String transactionType , int from_accountId , int to_accountId , double amount ) {
        String timestamp = dateFormat.format(new Date()); 
        String record = String.format("%s | %s | From_Account ID : %d | To_Account Id : %d | Amount : %.2f" , timestamp , transactionType , from_accountId , to_accountId , amount  ) ; 
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH , true          ))){
            writer.write(record);
            writer.newLine(); 
        }
        catch(IOException e){
            System.err.println("Error writing to transaction history file") ;

        }
    }
    
    
    // Method to retrive and display transaction  history 
    
    public static List<String> retriveTransactionHistory(){
        
        List<String> history = new ArrayList<>(); 
        
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            
            String line ; 
            while((line = reader.readLine()) != null){
                history.add(line) ;
            }
        }
        catch(IOException e){
            System.err.println("Error reading transaction history file");
        }
        
        
        return history ;
    }
   
    
}
