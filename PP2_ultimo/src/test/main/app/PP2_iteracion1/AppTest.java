package app.PP2_iteracion1;

import junit.framework.Test; 
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
    	
        super( testName );
        App saludo = new App();
    	saludo.getSaludo(); 
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	App saludo = new App();
    	saludo.getSaludo(); 
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */

	public void testApp()
    {
		App saludo = new App();
    	saludo.getSaludo(); 
        assertTrue( true );
    }
    
}
