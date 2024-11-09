use bankingapp;

DELIMITER $$
CREATE PROCEDURE transfer_funds(
    IN from_account INT,
    IN to_account INT,
    IN amount DECIMAL(15,2)
)
BEGIN
    DECLARE from_balance DECIMAL(15,2);

    -- Fetch the balance of the from_account into from_balance
    SELECT balance INTO from_balance
    FROM account
    WHERE account_id = from_account;

    -- Check if from_balance is sufficient for the transfer
    IF from_balance >= amount THEN
        -- Deduct the amount from the from_account balance
        UPDATE account
        SET balance = balance - amount
        WHERE account_id = from_account;

        -- Add the amount to the to_account balance
        UPDATE account
        SET balance = balance + amount
        WHERE account_id = to_account;

        -- Log the outgoing transaction
        INSERT INTO transaction(account_id, transaction_type, amount)
        VALUES (from_account, 'out', amount);

        -- Log the incoming transaction
        INSERT INTO transaction(account_id, transaction_type, amount)
        VALUES (to_account, 'in', amount);
    ELSE
        -- Raise an error if there are insufficient funds
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Insufficient balance in from_account';
    END IF;

END $$
DELIMITER ;
        
        
        
        
-- Procedure to withdraw fund ...
DELIMITER $$

CREATE PROCEDURE withdraw_fund(
    IN fromAccount INT,
    IN Amount DECIMAL(15,2)
)
BEGIN
    DECLARE from_balance DECIMAL(15,2);
    DECLARE insufficient_funds CONDITION FOR SQLSTATE '45000';
    DECLARE transaction_type_out VARCHAR(10) DEFAULT 'withdraw';

    -- Get balance of fromAccount
    SELECT Balance INTO from_balance 
    FROM Account 
    WHERE Account_Id = fromAccount;

    -- Check if sufficient funds exist
    IF from_balance >= Amount THEN
        -- Deduct amount from fromAccount
        UPDATE Account
        SET Balance = Balance - Amount
        WHERE Account_Id = fromAccount;

        -- Record the withdrawal transaction
        INSERT INTO Transaction(Account_Id, Transaction_Type, Amount) 
        VALUES (fromAccount, transaction_type_out, Amount);
    ELSE
        -- Raise error if insufficient funds
        SIGNAL insufficient_funds
        SET MESSAGE_TEXT = 'Insufficient funds in fromAccount';
    END IF;
END$$
DELIMITER ;
		
		
drop procedure withdraw_fund ; 
     
     
-- procedure to deposite funds 
DELIMITER $$
CREATE PROCEDURE deposit_fund(
    IN account_id INT,
    IN amount DECIMAL(15,2)
)
BEGIN
    DECLARE account_exists INT DEFAULT 0;

    -- Check if the account exists
    SELECT COUNT(*)
    INTO account_exists
    FROM account
    WHERE account_id = account_id;

    IF account_exists = 0 THEN
        -- Raise an error if the account does not exist
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Account does not exist';
    ELSE
        -- Start a transaction
        START TRANSACTION;

        -- Add the deposit amount to the account balance
        UPDATE account
        SET balance = balance + amount
        WHERE account_id = account_id;

        -- Log the deposit transaction
        INSERT INTO transaction(account_id, transaction_type, amount)
        VALUES (account_id, 'deposit', amount);

        -- Commit the transaction
        COMMIT;
    END IF;

END $$

DELIMITER ;

-- View 









		
        
	
        

        

    


