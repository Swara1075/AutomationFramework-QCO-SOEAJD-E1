package vtiger.Practice;

import org.testng.annotations.Test;

public class TestNGPractice {

	 
     @Test(invocationCount = 3,priority  = 2)
     public void create()
     {
    	 System.out.println("create");
     }
		
     @Test(priority = -2)
     public void modify()
     {
    	 System.out.println("modify");
     }
     
     @Test(enabled = false)
     public void delete()
     {
    	 System.out.println("delete");
     }
     
     @Test(invocationCount = -6)
     public void debug()
     {
    	 System.out.println("Debug");
     }
}
	 