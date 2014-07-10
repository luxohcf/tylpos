


//

//




//




//



package com.openbravo.pos.pda.dao;

import com.openbravo.pos.pda.util.PropertyUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaroslawwozniak
 * @version 1.0
 */
public abstract class BaseJdbcDAO {

    private PropertyUtils properties;

    public BaseJdbcDAO() {
        properties = new PropertyUtils();
    }

    protected Connection getConnection() throws Exception {
        try {
            Class.forName(properties.getDriverName());
            return DriverManager.getConnection(properties.getUrl(), properties.getDBUser(), properties.getDBPassword());
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    protected List transformSet(ResultSet rs) throws SQLException {
        List voList = new ArrayList();
        Object vo;
        while (rs.next()) {
            vo = map2VO(rs);
            voList.add(vo);
        }
        return voList;
    }

    protected boolean isPostgre() {
        return properties.getDriverName().contains("postgre");
    }

    protected abstract Object map2VO(ResultSet rs) throws SQLException;
}