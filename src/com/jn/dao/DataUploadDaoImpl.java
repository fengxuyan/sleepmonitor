package com.jn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jn.Utils.SaveData;
import com.jn.Utils.ServiceErrorCode;
import com.jn.Utils.ServiceException;
import com.jn.Utils.ServiceResult;
import com.jn.domain.CustomerData;
@Repository("DataUploadDao")  
public class DataUploadDaoImpl implements DataUploadDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ServiceResult ret;
	@Autowired
	private CustomerData customerData;
	@SuppressWarnings("null")
	@Override
	public ServiceResult DataUpload(Map map) throws SQLException {
		ret.setResult("Code", ServiceErrorCode.STATS_OK);
		ret.setResult("Message", map.get("Email"));
		String Email = (String)map.get("Email");
		CustomerData[] cus = (CustomerData[]) map.get("DataArray");
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Connection con = null;
		PreparedStatement proc = null;
		boolean saveFlog = true;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott_1023");
			for(int i = 0;i<(Integer)map.get("Count");i++){
				saveFlog = new SaveData().saveData(((String)map.get("Email")),(CustomerData)cus[i],con,proc);
				if(!saveFlog)
					throw new ServiceException("Error in Save Data");
			}
		}catch(ClassCastException e){
			e.printStackTrace();
			ret.setResult("Code",ServiceErrorCode.DATA_SAVE_ERROR);
			ret.setResult("Message","Error in Analytical Data");
		}catch(NumberFormatException e){
			e.printStackTrace();
			ret.setResult("Code",ServiceErrorCode.DATA_SAVE_ERROR);
			ret.setResult("Message","Error in Analytical Data");
		}catch(ServiceException e){
			e.printStackTrace();
			ret.setResult("Code",ServiceErrorCode.DATA_SAVE_ERROR);
			ret.setResult("Message","Error in Analytical Data");
		}catch(Exception e){
			e.printStackTrace();
			ret.setResult("Code",ServiceErrorCode.STATS_ERROR);
			ret.setResult("Message","Unknown Error");
		} finally{
			con.close();
			ts.commit();
			session.close();
		}
		return ret;
	}

}
