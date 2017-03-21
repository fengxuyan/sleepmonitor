package com.jn.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * �����û�����Ϊ�û�������ر�</br>�磺��ע������ע�������
 * @author Administrator
 *
 */
public class CreateTable {

	public static boolean createRealTable(String tableName) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		CallableStatement proc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			proc = con.prepareCall("{ call CREATETABLE(?) }"); 
			proc.setString(1, tableName);
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}
	
	/**
	 * ɾ��real���report��
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean deleteTable(String realTable,String reportTable) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		CallableStatement proc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			proc = con.prepareCall("{ call DROPTABLE(?) }"); 
			proc.setString(1, realTable);
			proc.execute();
			proc.setString(1, reportTable);
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * ��oldName�����ΪnewName
	 * @param oldName
	 * @param newName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean renameTable(String oldName,String newName) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		CallableStatement proc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			proc = con.prepareCall("{ call DROPTABLE(?,?) }"); 
			proc.setString(1, oldName);
			proc.setString(2, newName);
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * ���������
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean createReportTable(String tableName) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		CallableStatement proc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			proc = con.prepareCall("{ call CREATETABLEREPORT(?) }"); 
			proc.setString(1, tableName);
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return true;
	}
	/**
	 * ������ע��</br>���Ը��ݱ�����������ע�ҵģ��ҵĹ�ע���Լ������ע��
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean createAttentionTable(String tableName) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = null;
		CallableStatement proc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			proc = con.prepareCall("{ call CREATEATTENTIONTABLE(?) }"); 
			proc.setString(1, tableName);
			proc.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				con.close();
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
