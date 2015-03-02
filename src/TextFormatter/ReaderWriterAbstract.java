
package TextFormatter;

/**
 *
 * @author Scott Dykstra
 * @version 02/29/2013
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Scott
 */
public class ReaderWriterAbstract {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    protected String dbURL;
    protected String userName;
    protected String password;
    protected Connection conn = null;
    boolean ready = false;
    protected ArrayList<Object> tokens = null;

    public ReaderWriterAbstract() {
    }

    public Object getTokens(int num) {
        if (!tokens.isEmpty() || tokens != null) {
            if (tokens.size() > num) {
                return tokens.get(num);
            }
        }
        return null;
    } //end getTokens

    protected void tokenizer(String value, String delim) {
        StringTokenizer st = new StringTokenizer(value);
        if (delim == null) {
            delim = " ";
        }
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
    } //end tokenizer

    public void setUpDatabaseConnection(String dbURLIn, String userNameIn, String passwordIn) {
        dbURL = dbURLIn;
        userName = userNameIn;
        password = passwordIn;
        ready = true;
    } //end setUpDatabaseConnection

    protected boolean writeToDB(String sql) {
        Statement stmt = null;
        boolean finished = false;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(dbURL, userName, password);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            finished = false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            finished = false;
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
                finished = false;
            } //end finally try
            finished = true;
        } //end try
        return finished;
    } //end writeToDB

    protected ResultSet queryDB(String sql) {
        Statement stmt = null;
        ResultSet query = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(dbURL, userName, password);
            stmt = conn.createStatement();
            query = stmt.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException error) {
            error.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return query;
    } //end queryDB

    public boolean InsertDB(String tableName, ArrayList<String> updateColums, ArrayList<String> values) {
        String sql = null;
        if (ready) {
            if (tableName != null && updateColums != null || !updateColums.isEmpty() && values != null || !values.isEmpty()) {
                sql = "INSERT INTO " + tableName + "(";
                int counter = 0;
                for (String updateColum : updateColums) {
                    sql += updateColum;
                    counter++;
                    if (counter < updateColums.size() - 1) {
                        sql += ", ";
                    }
                }
                sql += " ) ";
                sql += "Select ";
                for (String value : values) {
                    sql += "'" + value + "'";
                    counter++;
                    if (counter < values.size() - 1) {
                        sql += ", ";
                    }
                }
            }
            return writeToDB(sql);
        }
        return false;
    }

    public boolean updateDB(String tableName, ArrayList<String> updateColums, ArrayList<String> values, String where) {
        String sql = null;
        int listSize = 0;
        if (ready) {
            if (tableName != null && updateColums != null || !updateColums.isEmpty() && values != null || !values.isEmpty()) {
                sql = "UPDATE " + tableName + " set ";
                if (updateColums.size() != values.size()) {
                    if (updateColums.size() > values.size()) {
                        listSize = values.size();
                    } else {
                        listSize = updateColums.size();
                    }
                } else {
                    listSize = updateColums.size();
                }
                System.out.println(listSize + " ListSize " + values.size() + " value " + updateColums.size() + "columns");
                for (int i = 0; i < listSize; i++) {
                    sql += updateColums.get(i) + "= '" + values.get(i) + "'";
                    if (i < listSize - 1) {
                        sql += ", ";
                    }
                }
                if (where != null) {
                    sql += " where " + where;
                }
            }
            System.out.println(sql);
            if (listSize != 0) {
                return writeToDB(sql);
            }
        }
        return false;
    } //end updateDB

    public ResultSet getQuery(String from, ArrayList<String> columns, String where) {
        if (ready) {
            ResultSet data = null;
            String sql = null;
            if (from != null && columns != null) {
                sql = "Select ";
                int i = 0;
                for (String column : columns) {
                    sql += column + " ";
                    i++;
                    if (i < columns.size() - 1) {
                        sql += ", ";
                    }
                }
                sql += from;
                if (where != null) {
                    sql += where;
                }
                data = queryDB(sql);
            }
            return data;
        }
        return null;
    } //end getQuery
    
}
