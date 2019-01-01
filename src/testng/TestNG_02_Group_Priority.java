package testng;

import org.testng.annotations.Test;


public class TestNG_02_Group_Priority {
	
  @Test(groups="user")
  public void TC01() {
	  System.out.println("TC_01");
  }
  
  @Test(groups="user")
  public void TC02() {
	  System.out.println("TC_02");
  }
  
  @Test(groups="product")
  public void TC03() {
	  System.out.println("TC_03");
  }
  
  @Test(groups="product")
  public void TC04() {
	  System.out.println("TC_04");
  }
  
  @Test(groups="payment")
  public void TC05() {
	  System.out.println("TC_05");
  }
  
  @Test(groups="payment")
  public void TC06() {
	  System.out.println("TC_06");
  }

}
