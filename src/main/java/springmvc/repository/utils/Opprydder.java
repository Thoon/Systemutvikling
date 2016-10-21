/*
T.H - kommentar:
Klassen er en hjelpeklasse som brukes i 
springmvc.respository.PersonDatabaseRepositoryImpl for opprydning.

Klassen trengs ikke om man velger å bruke
springmvc.respository.PersonDatabaseJdbcTemplateRepositoryImpl som 
implementasjon for PersonRepository (interface).

*/

package springmvc.repository.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Opprydder {
    public static void closeResSet(ResultSet res) {
    try {
      if (res != null &&!res.isClosed()) res.close();
    } catch (SQLException e) {
      write(e, "closeResSet()");
    }
  }

  public static void closeSentence(Statement stm) {
    try {
      if (stm != null && !stm.isClosed()) stm.close();
    } catch (SQLException e) {
      write(e, "closeSentence()");
    }
  }

  public static void closeConnection(Connection con) {
    try {
      if (con != null && !con.isClosed()) con.close();
    } catch (SQLException e) {
      write(e, "closeConnection()");
    }
  }

  public static void rollback(Connection con) {
    try {
      if (con != null && !con.getAutoCommit()) con.rollback();
    } catch (SQLException e) {
      write(e, "rollback()");
    }
  }

  public static void setAutoCommit(Connection con) {
    try {
      if (con != null && !con.getAutoCommit()) con.setAutoCommit(true);
    } catch (SQLException e) {
      write(e, "setAutoCommit()");
    }
  }

  public static void write(Exception e, String msg) {
    System.err.println("*** Feil oppstått: " + msg + ". ***");
    e.printStackTrace(System.err);
  }
}
