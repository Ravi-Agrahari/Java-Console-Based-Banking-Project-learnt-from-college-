/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Exceptions.BankingException;
import model.Bank;
import java.sql.* ; 

/**
 *
 * @author Dell
 */


public interface BankDAO {
    Bank getBankById(int bank_id) throws SQLException , BankingException ; 
}
