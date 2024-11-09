/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Exceptions.BankingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Bank;
import utility.DBConnection;
import java.sql.* ;
import java.sql.ResultSet ; 

/**
 *
 * @author Dell
 */
public class BankDAOImpl implements BankDAO {
    
    public Bank bank ; 

    @Override
    public Bank getBankById(int bank_id) throws SQLException ,BankingException  {
        
        
       String sql = "select * from bank where bank_id = ?" ; 
       
       try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql); 
                       
            )
            
        {
            ps.setInt(1, bank_id); 
            ResultSet rs = ps.executeQuery();
            
//            if(rs == null){
//                throw new BankingException("Bank id is not there is database") ; 
//            }
//            
            if(rs.next()){
                bank = new Bank(rs.getInt("bank_id") , rs.getString("bank_name") , rs.getString("bank_branch"));
            }
            else{
                throw new BankingException("given bank id "+ bank_id + " is not there int database");
            }
           
        } 
       
       return bank ;
    }
    
}
