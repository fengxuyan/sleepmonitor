package com.jn.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jn.domain.CustomerData;

/**
 * 将数据插入对应表中
 * @author Administrator
 *
 */
public class SaveData {
	public boolean saveData(String Email,CustomerData cusData,Connection con,PreparedStatement proc) throws ClassNotFoundException, ServiceException {
		String tableName = new EmailChange().emailChange(Email);
		String sql = "insert into "+tableName+" values(?,?,?,?,?,?,?,?  )";
		boolean flog = true;
		try {
			proc = con.prepareStatement(sql);
			proc.setString(1, cusData.getHupDate());
			proc.setString(2, cusData.getUpTime());
			proc.setInt(3, cusData.getHeartbeat());
			proc.setInt(4, cusData.getHmovement());
			proc.setInt(5, cusData.getRespiration());
			proc.setDouble(6, cusData.getTemperature());
			proc.setInt(7, cusData.getExtend1());
			proc.setInt(8, cusData.getExtend2());
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			flog = false;
			if(e.getMessage().contains("ORA-00942"));
				throw new ServiceException("邮箱不存在");
		} catch (Exception e) {
			e.printStackTrace();
			flog = false;
		} finally{
			try {
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flog;
	}
}
