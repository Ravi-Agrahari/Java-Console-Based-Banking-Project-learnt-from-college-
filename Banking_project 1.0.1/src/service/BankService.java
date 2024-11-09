/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Dell
 */


import Exceptions.BankingException;
import dao.BankDAO;
import dao.BankDAOImpl;
import java.sql.SQLException;
import model.Bank;



public class BankService {
    private final BankDAO bankDAO ; 
    
    public BankService(){
        this.bankDAO = new BankDAOImpl() ;
        
    }
    
    public Bank getBankById(int bank_id) throws SQLException, BankingException {
        Bank bank = bankDAO.getBankById(bank_id) ; 
        return bank ;
    }
}
