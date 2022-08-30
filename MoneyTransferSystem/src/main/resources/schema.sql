DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
                          accountid VARCHAR PRIMARY KEY,
                          accountnumber VARCHAR(12) NOT NULL,
                          balance INTEGER NOT NULL,
                          name VARCHAR NOT NULL,
                          accounttype VARCHAR,
                          branch VARCHAR,
                          currency VARCHAR NOT NULL,
                          accountOpenDate date,
                          created VARCHAR,
                          modified VARCHAR
);

DROP TABLE IF EXISTS Transaction;
CREATE TABLE Transaction (
                         transactionid VARCHAR PRIMARY KEY,
                         debitaccountnumber VARCHAR,
                         foreign key (debitaccountnumber) references Account(accountnumber),
                         creditAccountNumber VARCHAR,
                         foreign key (creditAccountNumber) references Account(accountnumber),
                         amount INTEGER,
                         currency VARCHAR,
                         transactiontype VARCHAR,
                         transactionMethod VARCHAR,
                         created VARCHAR,
                         modified VARCHAR
);