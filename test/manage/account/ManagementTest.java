package manage.account; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Management Tester. 
* 
* @author <Authors name> 
* @since <pre>05/14/2019</pre> 
* @version 1.0 
*/ 
public class ManagementTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: logins(String usernumber, String password) 
* 
*/ 
@Test
public void testLogins() throws Exception { 
//TODO: Test goes here...
    Management management = new Management();
    String s = management.logins("1","00000000");
    System.out.println(s);
} 

/** 
* 
* Method: registered(String usernumber, String name, String sex, String password) 
* 
*/ 
@Test
public void testRegistered() throws Exception { 
//TODO: Test goes here... 
} 


} 
