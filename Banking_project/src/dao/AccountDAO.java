
package dao;

import Exceptions.InvalidAccountTypeException;
import model.Account ; 
import java.sql.* ; 


public interface AccountDAO {
    
    /**
     *
     * @param account
     * @throws java.sql.SQLException
     * @throws Exceptions.InvalidAccountTypeException
     */
    void creatAccount(Account account ) throws SQLException, InvalidAccountTypeException; 
    
    public void updateAccount(int old_cust_id , int new_cust_id) throws SQLException, InvalidAccountTypeException ; 
    
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException ; 
  
   
}
