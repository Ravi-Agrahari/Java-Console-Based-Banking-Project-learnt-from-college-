
package controller;

import Exceptions.BankingException;
import Exceptions.InvalidAccountTypeException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Main {
    
    public static void main(String[] args) throws IOException , NumberFormatException, SQLException, BankingException, InvalidAccountTypeException, InterruptedException, ExecutionException{
        BankController bc = new BankController() ;
        bc.start() ;
    }
    
}
