
--    Copyright (C) 2010 Openbravo, S.L.

--

--




--




--



-- Database upgrade script for MYSQL

-- v2.30 - v2.30.1

ALTER TABLE TAXES ADD COLUMN VALIDFROM DATETIME DEFAULT '2001-01-01 00:00:00' NOT NULL;

-- v2.30.1 - v2.30.2

-- final script

DELETE FROM SHAREDTICKETS;

UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};