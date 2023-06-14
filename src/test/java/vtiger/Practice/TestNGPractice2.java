package vtiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPractice2 {

	public class TestNGPractice {

		 
	     @Test
	     public void create()
	     {
	     Assert.fail();
	     System.out.println("create");
	     }
			
	     @Test(dependsOnMethods = "create")
	     public void modify()
	     {
	    	 System.out.println("modify");
	     }
	     
	     @Test
	     public void delete()
	     {
	    	 System.out.println("delete");
	     }
	     
	     @Test(dependsOnMethods = {"create","delete"})
	     public void debug()
	     {
	    	 System.out.println("Debug");
	     }
}
}