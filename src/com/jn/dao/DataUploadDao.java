package com.jn.dao;

import java.sql.SQLException;
import java.util.Map;

import com.jn.Utils.ServiceResult;

public interface DataUploadDao {

	public ServiceResult DataUpload(Map map) throws SQLException;
}
