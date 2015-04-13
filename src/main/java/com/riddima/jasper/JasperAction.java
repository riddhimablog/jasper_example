package com.riddima.jasper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import net.sf.jasperreports.engine.JasperCompileManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author :Riddima blog
 * @version $Id: 
 */
public class JasperAction extends ActionSupport {
	
	private List<User> myList;
	private String format = "PDF";
	private String contentType = "application/pdf";
	private String fileName = "document.pdf";
	private String jasperfileName = "sample.jasper";
	private HashMap reportParams = new HashMap();
	
	public HashMap getReportParams() {
		return reportParams;
	}
	public void setReportParams(HashMap reportParams) {
		this.reportParams = reportParams;
	}
	public String getJasperfileName() {
		return jasperfileName;
	}
	public String getFormat() {
		return format;
	}
	public String execute() throws Exception {
		// dummy data create or feel free to change to data get in service class
		myList=new ArrayList<User>();
		User user1 =new User("Michael","Jackson","Joseph Jackson","Gary, Indiana, U.S.");
		User user2 =new User("Tom","Cruise","Thomas Cruise","Syracuse, New York, U.S.");
		myList.add(user1);
		myList.add(user2);
		/*//for service class
		 * 
		UserService service = new UserService();
		myList = service.getUsers();
		*/
		
		// Normally we would provide a pre-compiled .jrxml file
        // or check to make sure we don't compile on every request.
        try {
            JasperCompileManager.compileReportToFile(
            		getApplicationPath()+"/jasper/sample.jrxml",
            		getApplicationPath()+"/jasper/sample.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		
		
		
		reportParams.put("userName", "admin");
		return SUCCESS;
	}
	public List<User> getMyList() {
		return myList;
	}
	public void setFormat(String format) {
		this.format = format;
		if ("XLS".equals(format)) {
			fileName = "document.html";
		}
	}
	public String getContentType() {
		if ("XLS".equals(format)) {
			contentType = "application/xls";
		} else if ("HTML".endsWith(format)) {
			contentType = "text/html";
		}
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		if ("XLS".equals(format)) {
			fileName = "document.xls";
		} else if ("HTML".endsWith(format)) {
			fileName = "document.html";
		}
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setMyList(List<User> myList) {
		this.myList = myList;
	}
	
	public String getApplicationPath() {
		return ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
	}
}