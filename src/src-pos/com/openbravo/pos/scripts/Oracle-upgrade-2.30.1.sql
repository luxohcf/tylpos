
--    Copyright (C) 2010 Openbravo, S.L.

--

--




--




--



-- Database upgrade script for ORACLE

-- v2.30.1 - v2.30.2

-- final script

DELETE FROM SHAREDTICKETS;

UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};