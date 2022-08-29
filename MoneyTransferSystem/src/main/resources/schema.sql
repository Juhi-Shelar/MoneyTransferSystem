DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
                          accountid VARCHAR PRIMARY KEY,
                          accountnumber VARCHAR,
                          balance integer
);

DROP TABLE IF EXISTS Transaction;
CREATE TABLE Transaction (
                         transactionid VARCHAR PRIMARY KEY,
                         debitaccountnumber VARCHAR,
                         creditAccountNumber VARCHAR,
                         amount integer,
                         transactiontype VARCHAR,
                         transactionMethod VARCHAR
);