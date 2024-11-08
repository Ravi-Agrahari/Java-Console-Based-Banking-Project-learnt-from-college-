
package utility;

import java.sql.Connection ; 
import java.sql.DriverManager ; 
import java.sql.SQLException ;


public  class DBConnection {
     private static final String url = "jdbc:mysql://localhost:3308/bankingapp" ; 
     private static final String username = "root" ; 
     private static final String password = "root" ; 
     
    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static Connection getConnection()throws SQLException  {
         return DriverManager.getConnection(url , username , password) ; 
     }
     
     
     public static void closeConnection(Connection con) {
         if(con != null){
             try { 
                 con.close() ;
             } catch (SQLException ex) {
                 System.out.println("Error Closing Connection " + ex.getMessage()); 
             }
             
         }
     }
}
