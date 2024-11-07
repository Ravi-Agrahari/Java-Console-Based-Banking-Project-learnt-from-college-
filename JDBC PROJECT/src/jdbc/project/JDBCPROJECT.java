
package jdbc.project;

import java.sql.Connection ; 
import java.sql.Statement ;
import java.sql.ResultSet ; 
import java.sql.PreparedStatement  ;
import java.sql.DriverManager ;
import java.sql.SQLException;
import java.util.Scanner;


public class JDBCPROJECT {
    
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3308/sample" ; 
        String username = "root" ; 
        String password = "root" ;
        
        
        Connection con  = DriverManager.getConnection(url, username, password) ;
        
        if(con != null){
            System.out.println("Connection is established sucessfully"); 
        }
        else{
            System.out.println("Failed to connect"); 
        }
        
        Scanner sc = new Scanner(System.in) ; 
        // inserting value in user table in database
        System.out.println("Enter User Details"); 
        String name = sc.nextLine() ; 
        String pass = sc.nextLine() ; 
        int id =  sc.nextInt(); 
        
        String sql = "insert into users(username, password , user_id) values(?,?,?)" ; 
        PreparedStatement ps ; 
        ps = con.prepareStatement(sql);
        
        ps.setString(1,name);
        ps.setString(2,pass);
        ps.setInt(3,id);
        
        
        int res = ps.executeUpdate();
        
        if(res > 0){
            System.out.println("A new user created successfully");
        }
        else{
            System.out.println("Failed to add the user") ; 
        }
        
        // Read the users 
        
        String query  = "select * from users" ; 
        Statement st = con.createStatement() ; 
        ResultSet rs = st.executeQuery(query) ; 
     
        while(rs.next()){
            System.out.println("UserName: " + rs.getString("username")) ; 
            System.out.println("Password: " + rs.getString("password")) ;
            System.out.println("User_id: " + rs.getInt("user_id")) ; 
            System.out.println("\n") ; 
        }       
        
        
        
        
        
    }
    
    
    
    
}
