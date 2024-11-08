
package controller;

import Exceptions.BankingException;
import Exceptions.InvalidAccountTypeException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) throws IOException , NumberFormatException, SQLException, BankingException, InvalidAccountTypeException{
        BankController bc = new BankController() ;
        bc.start() ;
    }
    
}
