package jasper_example;

import junit.framework.TestCase;

import com.opensymphony.xwork2.ActionSupport;
import com.riddima.jasper.JasperAction;

public class JasperActionTest extends TestCase {
	public void testJasper() throws Exception {
		JasperAction index = new JasperAction();
		String result = index.execute();
		assertTrue("Expected a success result!",
				ActionSupport.SUCCESS.equals(result));
		

	}

}
