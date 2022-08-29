DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
                          accountid VARCHAR PRIMARY KEY,
                          accountnumber VARCHAR,
                          balance integer
);
INSERT INTO Account VALUES ('324c11a8-cb9d-4282-bcbc-21f2141f653a','223456755', 1000);