package Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

import Pentaho.PentahoLogic;

public class PentahoLogicTest {

	@Test
	public void test() throws ReportProcessingException, IOException {
		PentahoLogic pentaho;
		pentaho = new PentahoLogic();
		pentaho.createDefaultReport();
	}

}
