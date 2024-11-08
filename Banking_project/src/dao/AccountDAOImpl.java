
package dao;
import model.Account;
import java.sql.* ; 
import Exceptions.InvalidAccountTypeException ; 
import utility.DBConnection;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void creatAccount(Account account) throws SQLException,InvalidAccountTypeException  {
        String sql = "insert into Account(cutomer_id , bank_id , account_type ,balance) values (?,?,?,?)" ; 
        
        try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql); 
                       
            )
            
        {
            ps.setInt(1, account.getCustomerId()) ;
            ps.setInt(2, account.getBank().getBank_id()) ; 
            ps.setString(3, account.getAccountType()) ; 
            ps.setDouble(4, account.getBalance()) ; 
            
            int result = ps.executeUpdate() ; 
            
            if(result == 0){
                throw new InvalidAccountTypeException(" Account type not recognized ") ; 
            }
        } 
                
    }
    
    @Override
    public void updateAccount(int old_cust_id , int new_cust_id) throws SQLException,InvalidAccountTypeException {
        String sql = "update account set cutomer_id = ? where cutomer_id = ? " ; 
        
        try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);          
            )
            
        {    
            ps.setInt(2, old_cust_id);
            ps.setInt(1, new_cust_id); 
            
            int result = ps.executeUpdate() ; 
            
            if(result == 0){
                throw new InvalidAccountTypeException(" Something error in execution ") ; 
            }
            
        } 
    }
    
    
    @Override
    public void deleteAccount(int cust_id) throws SQLException, InvalidAccountTypeException {
        
        String sql = "delete from account where cutomer_id = ? " ; 
        
        try(
                Connection con =  DBConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);          
            )
            
        {    
            ps.setInt(1, cust_id);
 
            int result = ps.executeUpdate() ; 
            
            if(result == 0){
                throw new InvalidAccountTypeException(" Something error in execution ") ; 
            }
            
        } 
    }
}
