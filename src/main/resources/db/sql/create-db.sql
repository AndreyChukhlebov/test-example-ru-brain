SET DATABASE SQL SYNTAX ORA TRUE;

CREATE TABLE USERS (
  ID   INTEGER PRIMARY KEY,
  NAME VARCHAR (50)
);

CREATE TABLE DIEMENTIONS (
  ID   INTEGER PRIMARY KEY,
  USERS_ID INTEGER,
  GAS_INFO INTEGER,
  COLD_WATER_INFO INTEGER,
  HOT_WATER_INFO INTEGER,
  CREATE_DATA DATE ,
  FOREIGN KEY (USERS_ID)  REFERENCES USERS (ID)
);

CREATE SEQUENCE DIEMENTIONS_SEQUENCE START WITH 1 INCREMENT BY 1;

