import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInspector {

	Inspector inspector;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		inspector = new Inspector();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPrintConstructors() {
		inspector.printConstructors(new SomeClass1(4).getClass(), "");
		StringBuffer sb = new StringBuffer();
		sb.append(" Constructor Name: SomeClass1\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Modifier: public\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void testPrintMethods() {
		inspector.printMethods(new SomeClass1(4).getClass(), "");
		StringBuffer sb = new StringBuffer();
		sb.append(" Method Name: work\n");
		sb.append("  Return Type: class java.lang.String\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Modifier: public\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void testPrintMethodsException() {
		inspector.printMethods(new SomeClass2(4).getClass(), "");
		StringBuffer sb = new StringBuffer();
		sb.append(" Method Name: work\n");
		sb.append("  Exception Type: java.lang.NullPointerException\n");
		sb.append("  Return Type: class java.lang.String\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Modifier: public\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void testRecInterface() {
		inspector.printMethods(new SomeClass3(4).getClass(), "");
		StringBuffer sb = new StringBuffer();
		sb.append(" Method Name: perform\n");
		sb.append("  Return Type: void\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Parameter Type: SomeClass1\n");
		sb.append("  Modifier: public\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void testRecSuperInterface() {
		inspector.printMethods(new SomeClass4(4).getClass(), "");
		StringBuffer sb = new StringBuffer();
		sb.append(" Method Name: perform\n");
		sb.append("  Return Type: void\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Parameter Type: int\n");
		sb.append("  Parameter Type: SomeClass1\n");
		sb.append("  Modifier: public\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void test1DArray() {
		int[] a = {1,2,3,4};
		inspector.recArray(a.getClass(), a, true, 0);
		StringBuffer sb = new StringBuffer();
		sb.append("[1,2,3,4]\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
	
	@Test
	void test2DArray() {
		int[][] a = {{1,2,3,4},{3,5,7,9}};
		inspector.recArray(a.getClass(), a, true, 0);
		StringBuffer sb = new StringBuffer();
		sb.append("[[1,2,3,4]\n");
		sb.append(",[3,5,7,9]\n");
		sb.append("]\n");
		assertEquals(sb.toString(),inspector.getStringBuffer());
	}
}
