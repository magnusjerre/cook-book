-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: db/liquibase/db.changelog-master.xml
-- Ran at: 19.09.2020, 15:53
-- Against: postgres@jdbc:postgresql://localhost:5432/postgres
-- Liquibase version: 4.0.0
-- *********************************************************************

SET SEARCH_PATH TO public;

SET SEARCH_PATH TO public;

-- Create Database Lock Table
CREATE TABLE databasechangeloglock (ID INTEGER NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT DATABASECHANGELOGLOCK_PKEY PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM databasechangeloglock;

INSERT INTO databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'DESKTOP-TCDMQH1 (192.168.1.161)', LOCKGRANTED = '2020-09-19 15:53:04.928' WHERE ID = 1 AND LOCKED = FALSE;

SET SEARCH_PATH TO public;

-- Create Database Change Log Table
CREATE TABLE databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INTEGER NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

SET SEARCH_PATH TO public;

SET SEARCH_PATH TO public;

-- Changeset ./db/liquibase/changelog-1.0.xml::changelog-1.0::Magnus Jerre
SET SEARCH_PATH TO public;

CREATE TABLE COOK_BOOK_USER (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, EXTERNAL_USER_PROVIDER_ID VARCHAR, CONSTRAINT COOK_BOOK_USER_PKEY PRIMARY KEY (ID), UNIQUE (ID));

CREATE TABLE RECIPE (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NAME VARCHAR, OWNER_ID INTEGER NOT NULL, INTRO VARCHAR, BASE_UNIT INTEGER, ACTIVE_TIME_MIN INTEGER, ACTIVE_TIME_MAX INTEGER, TOTAL_TIME_MIN INTEGER, TOTAL_TIME_MAX INTEGER, CONSTRAINT RECIPE_PKEY PRIMARY KEY (id), CONSTRAINT fk_recipe_user FOREIGN KEY (OWNER_ID) REFERENCES COOK_BOOK_USER(ID), UNIQUE (id));

CREATE TABLE SECTION (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, RECIPE_ID INTEGER NOT NULL, NAME VARCHAR, SEQUENCE_NUMBER SMALLINT, CONSTRAINT SECTION_PKEY PRIMARY KEY (id), CONSTRAINT fk_reipce_group_recipe FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID), UNIQUE (id));

CREATE TABLE INGREDIENT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, SECTION_ID INTEGER NOT NULL, NAME VARCHAR NOT NULL, MEASURE_UNIT VARCHAR, MEASUREMENT FLOAT, CONSTRAINT INGREDIENT_PKEY PRIMARY KEY (ID), CONSTRAINT fk_ingredient_section FOREIGN KEY (SECTION_ID) REFERENCES SECTION(ID), UNIQUE (ID));

CREATE TABLE SECTION_STEP (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, SEQUENCE_NUMBER SMALLINT, DESCRIPTION VARCHAR, SECTION_ID INTEGER NOT NULL, CONSTRAINT SECTION_STEP_PKEY PRIMARY KEY (ID), CONSTRAINT fk_step_section FOREIGN KEY (SECTION_ID) REFERENCES SECTION(ID), UNIQUE (ID));

INSERT INTO databasechangelog (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('changelog-1.0', 'Magnus Jerre', './db/liquibase/changelog-1.0.xml', NOW(), 1, '8:7fa54889d21c1ecb4ed481c0dcca3e68', 'createTable tableName=COOK_BOOK_USER; createTable tableName=RECIPE; createTable tableName=SECTION; createTable tableName=INGREDIENT; createTable tableName=SECTION_STEP', '', 'EXECUTED', NULL, NULL, '4.0.0', '0523585713');

-- Release Database Lock
SET SEARCH_PATH TO public;

UPDATE databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

SET SEARCH_PATH TO public;

