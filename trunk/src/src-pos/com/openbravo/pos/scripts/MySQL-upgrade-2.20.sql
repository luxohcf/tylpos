


--

--




--




--



-- Database upgrade script for MYSQL

-- v2.20 - v2.30beta

INSERT INTO RESOURCES(ID, NAME, RESTYPE, CONTENT) VALUES('30', 'Printer.PartialCash', 0, $FILE{/com/openbravo/pos/templates/Printer.PartialCash.xml});

-- Charset (UTF-8 Unicode), collation (utf8_general_ci), engine (InnoDB) modification
ALTER TABLE APPLICATIONS ENGINE=InnoDB;
ALTER TABLE CATEGORIES ENGINE=InnoDB;
ALTER TABLE CLOSEDCASH ENGINE=InnoDB;
ALTER TABLE CUSTOMERS ENGINE=InnoDB;
ALTER TABLE FLOORS ENGINE=InnoDB;
ALTER TABLE LOCATIONS ENGINE=InnoDB;
ALTER TABLE PAYMENTS ENGINE=InnoDB;
ALTER TABLE PEOPLE ENGINE=InnoDB;
ALTER TABLE PLACES ENGINE=InnoDB;
ALTER TABLE PRODUCTS ENGINE=InnoDB;
ALTER TABLE PRODUCTS_CAT ENGINE=InnoDB;
ALTER TABLE PRODUCTS_COM ENGINE=InnoDB;
ALTER TABLE RECEIPTS ENGINE=InnoDB;
ALTER TABLE RESERVATIONS ENGINE=InnoDB;
ALTER TABLE RESERVATION_CUSTOMERS ENGINE=InnoDB;
ALTER TABLE RESOURCES ENGINE=InnoDB;
ALTER TABLE ROLES ENGINE=InnoDB;
ALTER TABLE SHAREDTICKETS ENGINE=InnoDB;
ALTER TABLE STOCKCURRENT ENGINE=InnoDB;
ALTER TABLE STOCKDIARY ENGINE=InnoDB;
ALTER TABLE TAXCATEGORIES ENGINE=InnoDB;
ALTER TABLE TAXCUSTCATEGORIES ENGINE=InnoDB;
ALTER TABLE TAXES ENGINE=InnoDB;
ALTER TABLE TAXLINES ENGINE=InnoDB;
ALTER TABLE THIRDPARTIES ENGINE=InnoDB;
ALTER TABLE TICKETLINES ENGINE=InnoDB;
ALTER TABLE TICKETS ENGINE=InnoDB;
ALTER TABLE TICKETSNUM ENGINE=InnoDB;

ALTER TABLE APPLICATIONS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE CATEGORIES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE CLOSEDCASH CONVERT TO CHARACTER SET UTF8;
ALTER TABLE CUSTOMERS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE FLOORS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE LOCATIONS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PAYMENTS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PEOPLE CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PLACES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PRODUCTS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PRODUCTS_CAT CONVERT TO CHARACTER SET UTF8;
ALTER TABLE PRODUCTS_COM CONVERT TO CHARACTER SET UTF8;
ALTER TABLE RECEIPTS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE RESERVATIONS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE RESERVATION_CUSTOMERS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE RESOURCES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE ROLES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE SHAREDTICKETS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE STOCKCURRENT CONVERT TO CHARACTER SET UTF8;
ALTER TABLE STOCKDIARY CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TAXCATEGORIES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TAXCUSTCATEGORIES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TAXES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TAXLINES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE THIRDPARTIES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TICKETLINES CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TICKETS CONVERT TO CHARACTER SET UTF8;
ALTER TABLE TICKETSNUM CONVERT TO CHARACTER SET UTF8;

CREATE TABLE _PRODUCTS_COM (
    ID VARCHAR(255) NOT NULL,
    PRODUCT VARCHAR(255) NOT NULL,
    PRODUCT2 VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO _PRODUCTS_COM(ID, PRODUCT, PRODUCT2) SELECT CONCAT(PRODUCT, PRODUCT2), PRODUCT, PRODUCT2 FROM PRODUCTS_COM;

DROP TABLE PRODUCTS_COM;

CREATE TABLE PRODUCTS_COM (
    ID VARCHAR(255) NOT NULL,
    PRODUCT VARCHAR(255) NOT NULL,
    PRODUCT2 VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT PRODUCTS_COM_FK_1 FOREIGN KEY (PRODUCT) REFERENCES PRODUCTS(ID),
    CONSTRAINT PRODUCTS_COM_FK_2 FOREIGN KEY (PRODUCT2) REFERENCES PRODUCTS(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX PCOM_INX_PROD ON PRODUCTS_COM(PRODUCT, PRODUCT2);

INSERT INTO PRODUCTS_COM(ID, PRODUCT, PRODUCT2) SELECT ID, PRODUCT, PRODUCT2 FROM _PRODUCTS_COM;

DROP TABLE _PRODUCTS_COM;

ALTER TABLE TICKETS ADD COLUMN TICKETTYPE INTEGER DEFAULT 0 NOT NULL;
DROP INDEX TICKETS_TICKETID ON TICKETS;
CREATE INDEX TICKETS_TICKETID ON TICKETS(TICKETTYPE, TICKETID);
UPDATE TICKETS SET TICKETTYPE = 1 WHERE ID IN (SELECT RECEIPT FROM PAYMENTS WHERE TOTAL<0);

CREATE TABLE TICKETSNUM_REFUND (ID INTEGER NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO TICKETSNUM_REFUND VALUES(1);

CREATE TABLE TICKETSNUM_PAYMENT (ID INTEGER NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO TICKETSNUM_PAYMENT VALUES(1);

ALTER TABLE PAYMENTS ADD COLUMN TRANSID VARCHAR(255);
ALTER TABLE PAYMENTS ADD COLUMN RETURNMSG MEDIUMBLOB;

CREATE TABLE ATTRIBUTE (
    ID VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ATTRIBUTEVALUE (
    ID VARCHAR(255) NOT NULL,
    ATTRIBUTE_ID VARCHAR(255) NOT NULL,
    VALUE VARCHAR(255),
    PRIMARY KEY (ID),
    CONSTRAINT ATTVAL_ATT FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ATTRIBUTESET (
    ID VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ATTRIBUTEUSE (
    ID VARCHAR(255) NOT NULL,
    ATTRIBUTESET_ID VARCHAR(255) NOT NULL,
    ATTRIBUTE_ID VARCHAR(255) NOT NULL,
    LINENO INTEGER,
    PRIMARY KEY (ID),
    CONSTRAINT ATTUSE_SET FOREIGN KEY (ATTRIBUTESET_ID) REFERENCES ATTRIBUTESET(ID),
    CONSTRAINT ATTUSE_ATT FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX ATTUSE_LINE ON ATTRIBUTEUSE(ATTRIBUTESET_ID, LINENO);

CREATE TABLE ATTRIBUTESETINSTANCE (
    ID VARCHAR(255) NOT NULL,
    ATTRIBUTESET_ID VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255),
    PRIMARY KEY (ID),
    CONSTRAINT ATTSETINST_SET FOREIGN KEY (ATTRIBUTESET_ID) REFERENCES ATTRIBUTESET(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ATTRIBUTEINSTANCE (
    ID VARCHAR(255) NOT NULL,
    ATTRIBUTESETINSTANCE_ID VARCHAR(255) NOT NULL,
    ATTRIBUTE_ID VARCHAR(255) NOT NULL,
    VALUE VARCHAR(255),
    PRIMARY KEY (ID),
    CONSTRAINT ATTINST_SET FOREIGN KEY (ATTRIBUTESETINSTANCE_ID) REFERENCES ATTRIBUTESETINSTANCE(ID),
    CONSTRAINT ATTINST_ATT FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE PRODUCTS ADD COLUMN ATTRIBUTESET_ID VARCHAR(255);
ALTER TABLE PRODUCTS ADD CONSTRAINT PRODUCTS_ATTRSET_FK FOREIGN KEY (ATTRIBUTESET_ID) REFERENCES ATTRIBUTESET(ID);

ALTER TABLE STOCKDIARY ADD COLUMN ATTRIBUTESETINSTANCE_ID VARCHAR(255);
ALTER TABLE STOCKDIARY ADD CONSTRAINT STOCKDIARY_ATTSETINST FOREIGN KEY (ATTRIBUTESETINSTANCE_ID) REFERENCES ATTRIBUTESETINSTANCE(ID);

CREATE TABLE STOCKLEVEL (
    ID VARCHAR(255) NOT NULL,
    LOCATION VARCHAR(255) NOT NULL,
    PRODUCT VARCHAR(255) NOT NULL,
    STOCKSECURITY DOUBLE,
    STOCKMAXIMUM DOUBLE,
    PRIMARY KEY (ID),
    CONSTRAINT STOCKLEVEL_PRODUCT FOREIGN KEY (PRODUCT) REFERENCES PRODUCTS(ID),
    CONSTRAINT STOCKLEVEL_LOCATION FOREIGN KEY (LOCATION) REFERENCES LOCATIONS(ID)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO STOCKLEVEL(ID, LOCATION, PRODUCT, STOCKSECURITY, STOCKMAXIMUM) SELECT CONCAT(LOCATION, PRODUCT), LOCATION, PRODUCT, STOCKSECURITY, STOCKMAXIMUM FROM STOCKCURRENT;

CREATE TABLE _STOCKCURRENT (
    LOCATION VARCHAR(255) NOT NULL,
    PRODUCT VARCHAR(255) NOT NULL,
    ATTRIBUTESETINSTANCE_ID VARCHAR(255),
    UNITS DOUBLE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO _STOCKCURRENT(LOCATION, PRODUCT, UNITS) SELECT LOCATION, PRODUCT, UNITS FROM STOCKCURRENT;

DROP TABLE STOCKCURRENT;

CREATE TABLE STOCKCURRENT (
    LOCATION VARCHAR(255) NOT NULL,
    PRODUCT VARCHAR(255) NOT NULL,
    ATTRIBUTESETINSTANCE_ID VARCHAR(255),
    UNITS DOUBLE NOT NULL,
    CONSTRAINT STOCKCURRENT_FK_1 FOREIGN KEY (PRODUCT) REFERENCES PRODUCTS(ID),
    CONSTRAINT STOCKCURRENT_ATTSETINST FOREIGN KEY (ATTRIBUTESETINSTANCE_ID) REFERENCES ATTRIBUTESETINSTANCE(ID),
    CONSTRAINT STOCKCURRENT_FK_2 FOREIGN KEY (LOCATION) REFERENCES LOCATIONS(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX STOCKCURRENT_INX ON STOCKCURRENT(LOCATION, PRODUCT, ATTRIBUTESETINSTANCE_ID);

INSERT INTO STOCKCURRENT(LOCATION, PRODUCT, UNITS) SELECT LOCATION, PRODUCT, UNITS FROM _STOCKCURRENT;

DROP TABLE _STOCKCURRENT;

ALTER TABLE TICKETLINES ADD COLUMN ATTRIBUTESETINSTANCE_ID VARCHAR(255);
ALTER TABLE TICKETLINES ADD CONSTRAINT TICKETLINES_ATTSETINST FOREIGN KEY (ATTRIBUTESETINSTANCE_ID) REFERENCES ATTRIBUTESETINSTANCE(ID);

-- v2.30beta - v2.30

ALTER TABLE ATTRIBUTEVALUE DROP FOREIGN KEY ATTVAL_ATT;
ALTER TABLE ATTRIBUTEVALUE ADD CONSTRAINT ATTVAL_ATT FOREIGN KEY (ATTRIBUTE_ID) REFERENCES ATTRIBUTE(ID) ON DELETE CASCADE;

ALTER TABLE ATTRIBUTEUSE DROP FOREIGN KEY ATTUSE_SET;
ALTER TABLE ATTRIBUTEUSE ADD CONSTRAINT ATTUSE_SET FOREIGN KEY (ATTRIBUTESET_ID) REFERENCES ATTRIBUTESET(ID) ON DELETE CASCADE;

ALTER TABLE ATTRIBUTESETINSTANCE DROP FOREIGN KEY ATTSETINST_SET;
ALTER TABLE ATTRIBUTESETINSTANCE ADD CONSTRAINT ATTSETINST_SET FOREIGN KEY (ATTRIBUTESET_ID) REFERENCES ATTRIBUTESET(ID) ON DELETE CASCADE;

ALTER TABLE ATTRIBUTEINSTANCE DROP FOREIGN KEY ATTINST_SET;
ALTER TABLE ATTRIBUTEINSTANCE ADD CONSTRAINT ATTINST_SET FOREIGN KEY (ATTRIBUTESETINSTANCE_ID) REFERENCES ATTRIBUTESETINSTANCE(ID) ON DELETE CASCADE;

ALTER TABLE PRODUCTS MODIFY COLUMN ISCOM BIT DEFAULT b'0' NOT NULL;
ALTER TABLE PRODUCTS MODIFY COLUMN ISSCALE BIT DEFAULT b'0' NOT NULL;
ALTER TABLE TAXES MODIFY COLUMN RATECASCADE BIT DEFAULT b'0' NOT NULL;
ALTER TABLE CUSTOMERS MODIFY COLUMN VISIBLE BIT DEFAULT b'1' NOT NULL;

-- v2.30 - v2.30.1

ALTER TABLE TAXES ADD COLUMN VALIDFROM DATETIME DEFAULT '2001-01-01 00:00:00' NOT NULL;

-- v2.30.1 - v2.30.2

-- final script

DELETE FROM SHAREDTICKETS;

UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};
