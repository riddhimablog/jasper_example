package com.riddima.jasper;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRWrappingSvgRenderer;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.util.JRTypeSniffer;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ImageAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ApplicationAware, SessionAware, Preparable {
	private String contentType = "image/gif";
	private String imageName;

	private HttpServletRequest request;

	private InputStream fileInputStream;

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;

	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setApplication(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		byte[] imageData = null;
		String imageMimeType = null;
		if ("px".equals(imageName)) {
			try {
				JRRenderable pxRenderer = JRImageRenderer
						.getInstance("net/sf/jasperreports/engine/images/pixel.GIF");
				imageData = pxRenderer.getImageData();
			} catch (JRException e) {
				throw new ServletException(e);
			}
		} else {
			List jasperPrintList = BaseHttpServlet.getJasperPrintList(request);

			if (jasperPrintList == null) {
				throw new ServletException(
						"No JasperPrint documents found on the HTTP session.");
			}

			JRPrintImage image = JRHtmlExporter.getImage(jasperPrintList,
					imageName);

			JRRenderable renderer = image.getRenderer();
			if (renderer.getType() == JRRenderable.TYPE_SVG) {
				renderer = new JRWrappingSvgRenderer(renderer, new Dimension(
						image.getWidth(), image.getHeight()),
						image.getBackcolor());
			}

			imageMimeType = JRTypeSniffer.getImageMimeType(renderer
					.getImageType());

			try {
				imageData = renderer.getImageData();
			} catch (JRException e) {
				throw new ServletException(e);
			}
		}

		fileInputStream = new ByteArrayInputStream(imageData);

		System.out.println();
		return SUCCESS;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

}
