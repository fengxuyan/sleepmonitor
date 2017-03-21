package com.jn.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.Utils.RequestUtils;
import com.jn.Utils.ServiceErrorCode;
import com.jn.dao.ProductDao;


@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public JSONObject addProduct(HttpServletRequest request){
		JSONObject data = new JSONObject();
		String Email = request.getParameter("Email");
		String SN = request.getParameter("SN");
		String existEmail = null;
		String snRight = null;
		String existSn =null;
		String SN3 = null;
		String type = null;
		String ip =null;
		if(SN==null||"".equals(SN)){
			data.put("Code", ServiceErrorCode.SN_NULL);
			data.put("Message", "The SN is Empty!");
		}else if(SN.length()!=15){
			data.put("Code", ServiceErrorCode.SN_LENGTH_ERROR);
			data.put("Message", "The SN Must be 15 Digit!");
		}else{
			snRight = productDao.existOrNot(SN);
			if("0".equals(snRight)){
				data.put("Code", ServiceErrorCode.SN_ERROR);
				data.put("Message", "Error in SN.Please Check Carefully!");
			}else if("2".equals(snRight)){
				data.put("Code", ServiceErrorCode.STATS_ERROR);
				data.put("Message", "Error Server.Please Try Again!");
			}else{
				SN3 = SN.substring(0,3);
				type = SN.substring(3,4);
				ip = productDao.getIp(SN3, type);
				if("error".equals(ip)){
					data.put("Code", ServiceErrorCode.STATS_ERROR);
					data.put("Message", "Error in Server!");
				}else if("noip".equals(ip)){
					data.put("Code", ServiceErrorCode.SN_IP_ERROR);
					data.put("Message", "The SN Not record.Please contact the company or check your SN!");
				}else{
					existEmail = productDao.getEmail(SN);
					if(existEmail==null||"".equals(existEmail)){
						existSn = productDao.getSn(Email, type);
						if(existSn==null||"".equals(existSn)){
							String flog = "fail";
							int i = 0;
							while("fail".equals(flog)){
								flog = productDao.updateInfo(Email, SN);
								i++;
								if(i==5)
									break;
							}
							if(i==5){
								data.put("Code", ServiceErrorCode.DELETE_ERROR);
								data.put("Message", "Failed to Add your Product.Please Try Again!");
							}else{
								data.put("Code", ServiceErrorCode.ADD_SUCCESS);
								data.put("Message", "Successfully Add Product!");
								data.put("IP", ip);
							}
						}else if(SN.equals(existSn)){
							data.put("Code", ServiceErrorCode.ADD_SUCCESS);
							data.put("Message", "Successfully Add Product!");
							data.put("IP", ip);
						}else{
							String existSn3 = existSn.substring(0,3);
							String existType = existSn.substring(3,4);
							if(existSn3.equals(SN3)){
								try {
									//更新对应的sn
									productDao.updateSn(Email, existSn, SN);
									//String url = "http://"+ip+"/SleepMonitor/product/saveSn";
									String url = "http://localhost:8080/SleepMonitor/product/transData";
									String params = "newSN="+SN+"&oldSN="+existSn;
									int i = 0;
									String httpResult = "\"fail\"";
									while("\"fail\"".equals(httpResult)){
										httpResult = RequestUtils.sendPost(url, params);
										i++;
										if(i==5)
											break;
									}
									if(i==5){
										data.put("Code", ServiceErrorCode.ADD_ERROR);
										data.put("Message", "Failed to Add your Product.Please Try Again!");
									}else{
										data.put("Code", ServiceErrorCode.ADD_SUCCESS);
										data.put("Message", "Successfully Add your Product!");
									}
								} catch (Exception e) {
									e.printStackTrace();
									data.put("Code", ServiceErrorCode.ADD_ERROR);
									data.put("Message", "Failed to Add your Product.Please Try Again!");
								}
							}else{
								//新服务器:String url = "http://"+ip+"/SleepMonitor/product/saveSn";
								String url1 = "http://localhost:8080/SleepMonitor/product/getDataFromOld";
								//旧产品的IP
								String oldIp = productDao.getIp(existSn3, existType);
								//旧服务器:String url = "http://"+oldIp+"/SleepMonitor/product/saveSn";
								String url2 = "http://localhost:8080/SleepMonitor/product/deleteOldData";
								String newParams = "newSN="+SN+"&oldSN="+existSn;
								String oldParams = "&oldSN="+existSn;
								String httpRet1 = "\"fail\"";
								String httpRet2 = "\"fail\"";
								try {
									int i = 0;
									while ("\"fail\"".equals(httpRet1)) {
										httpRet1 = RequestUtils.sendPost(url1,newParams);
										i++;
										if (i == 5)
											break;
									}
									if(i==5){
										data.put("Code", ServiceErrorCode.ADD_ERROR);
										data.put("Message", "Failed to Add your Product.Please Try Again!");
									}else{
										while ("\"fail\"".equals(httpRet2)) {
											httpRet2 = RequestUtils.sendPost(url2,oldParams);
											i++;
											if (i == 5)
												break;
										}
										if(i==5){
											data.put("Code", ServiceErrorCode.ADD_ERROR);
											data.put("Message", "Failed to Add your Product.Please Try Again!");
										}else{
											data.put("Code", ServiceErrorCode.ADD_SUCCESS);
											data.put("Message", "Successfully Add your Product!");
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
									data.put("Code", ServiceErrorCode.ADD_ERROR);
									data.put("Message", "Failed to Add your Product.Please Try Again!");
								}
							}
						}
					}else{
						if(existEmail.equals(Email)){
							data.put("Code", ServiceErrorCode.ADD_SUCCESS);
							data.put("Message", "Successfully Add Product!");
							data.put("IP", ip);
						}else{
							data.put("Code", ServiceErrorCode.SN_ATTACH);
							data.put("Message", "The SN has Attached An User.Please Check your SN!");
						}
						
					}
				}
			}
		}
		return data;
	}
	
	@Override
	public JSONObject deleteProduct(HttpServletRequest request){
		JSONObject data = new JSONObject();
		String Email = request.getParameter("Email");
		String sn = request.getParameter("SN");
		String snRight = null;
		String ip = null;
		String sn3 = null;
		String type = null;
		String existEmail = null;
		if(sn==null||"".equals(sn)){
			data.put("Code", ServiceErrorCode.SN_NULL);
			data.put("Message", "The SN is Empty!");
		}else if(sn.length()!=15){
			data.put("Code", ServiceErrorCode.SN_LENGTH_ERROR);
			data.put("Message", "Invalid SN.Unable to delete the Product!");
		}else{
			snRight = productDao.existOrNot(sn);
			if("0".equals(snRight)){
				data.put("Code", ServiceErrorCode.SN_ERROR);
				data.put("Message", "Invalid SN.Unable to delete the Product!");
			}else if("2".equals(snRight)){
				data.put("Code", ServiceErrorCode.STATS_ERROR);
				data.put("Message", "Error Server.Please Try Again!");
			}else{
				sn3 = sn.substring(0,3);
				type = sn.substring(3,4);
				ip = productDao.getIp(sn3, type);
				if("error".equals(ip)){
					data.put("Code", ServiceErrorCode.STATS_ERROR);
					data.put("Message", "Error in Server!");
				}else if("noip".equals(ip)){
					data.put("Code", ServiceErrorCode.SN_IP_ERROR);
					data.put("Message", "Invalid SN.Unable to delete the Product!");
				}else{
					existEmail = productDao.getEmail(sn);
					if(existEmail==null||"".equals(existEmail)){
						data.put("Code", ServiceErrorCode.DELETE_SUCCESS);
						data.put("Message", "Successfully Delete the Product!");
					}else{
						if(!Email.equals(existEmail)){
							data.put("Code", ServiceErrorCode.MATCH_ERROR);
							data.put("Message", "You Do not Have Permission to Delete the Product!");
						}else{
							//String url = "http://"+ip+":8080/SleepMonitor/product/delete";
							String url = "http://localhost:8080/SleepMonitor/product/deleteData";
							String param = "SN="+sn;
							try {
								String httpResult = "\"fail\"";
								int i = 0;
								while("\"fail\"".equals(httpResult)){
									httpResult = RequestUtils.sendPost(url, param);
									i++;
									if(i==5)
										break;
								}
								if(i==5){
									data.put("Code", ServiceErrorCode.DELETE_ERROR);
									data.put("Message", "Failed to Delete your Product.Please Try Again!");
								}else{
									String flog = "fail";
									int j = 0;
									while("fail".equals(flog)){
										flog = productDao.updateInfo(Email, sn,"");
										j++;
										if(j==5)
											break;
									}
									if(j==5){
										data.put("Code", ServiceErrorCode.DELETE_ERROR);
										data.put("Message", "Failed to Delete your Product.Please Try Again!");
									}else{
										data.put("Code", ServiceErrorCode.DELETE_SUCCESS);
										data.put("Message", "Successfully Delete your Product!");
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								data.put("Code", ServiceErrorCode.STATS_ERROR);
								data.put("Message", "Error in Server.Please Try Again!");
							}
						}
					}
				}
			}
		}
		return data;
	}
	
	
	@Override
	public String transData(HttpServletRequest request){
		String newSN = request.getParameter("newSN");
		String oldSN = request.getParameter("oldSN");
		String newRealTable = "real"+newSN;
		String newReportTable = "report"+newSN;
		String oldRealTable = "real"+oldSN;
		String oldReportTable = "report"+oldSN;
		return productDao.transData(newSN, oldSN, newRealTable, newReportTable, oldRealTable, oldReportTable);
	}
	
	@Override
	public String deleteData(HttpServletRequest request){
		String sn = request.getParameter("SN");
		return productDao.deleteData(sn);
		
	}
	
	@Override
	public String getDataFromOld(HttpServletRequest request){
		String newSN = request.getParameter("newSN");
		String oldSN = request.getParameter("oldSN");
		String newRealTable = "real"+newSN;
		String newReportTable = "report"+newSN;
		String oldRealTable = "real"+oldSN;
		String oldReportTable = "report"+oldSN;
		return productDao.getDataFromOld(newSN, oldSN, newRealTable, newReportTable, oldRealTable, oldReportTable);
	}
	
	@Override
	public String deleteOldData(HttpServletRequest request){
		String oldSN = request.getParameter("oldSN");
		String oldRealTable = "real"+oldSN;
		String oldReportTable = "report"+oldSN;
		return productDao.deleteOldData(oldSN, oldRealTable, oldReportTable);
	}
	
}
