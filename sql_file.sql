
create database bankingApp ;

use bankingApp ; 

create table Bank(
bank_id INT PRIMARY KEY auto_increment, 
bank_name VARCHAR(100) NOT NULL, 
bank_branch VARCHAR(100) NOT NULL
);


INSERT INTO Bank(bank_name , bank_branch)
values('NIC ASIA' , 'Parasi') ;

INSERT INTO Bank(bank_name , bank_branch)
values('SBI NEPAL' , 'Parasi') ;

SELECT * from Bank ; 


create table Account(
account_id INT PRIMARY KEY unique auto_increment,
cutomer_id int not null, 
bank_id int, 
account_type varchar(50) not null,
balance decimal(15,2) not null,
created_at timestamp default current_timestamp,
foreign key(bank_id) references Bank(bank_id)
);

desc Account ;  


create table SavingsAccount(
account_id int primary key,
interest_rate decimal(5,2) not null,
foreign key(account_id) references Account(account_id)
);


create table CurrentAccount(
account_id int primary key,
overdraft_limit decimal(15,2) not null,
foreign key(account_id) references Account(account_id)
);


create table Transaction(
transaction_id int primary key auto_increment,
account_id int not null, 
transaction_type varchar(50) not null, 
amount decimal(15,2) not null, 
transaction_date timestamp default current_timestamp,
foreign key(account_id) references Account(account_id) 
);


create table DepositTransaction(
transaction_id int primary key,
deposit_method varchar(50) not null, 
foreign key(transaction_id) references Transaction(transaction_id) 
);

create table WithdrawTransaction(
transaction_id int primary key,
withdraw_method varchar(50) not null, 
foreign key(transaction_id) references Transaction(transaction_id) 
);



