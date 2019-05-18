package manage.account; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* UserOperation Tester. 
* 
* @author <Authors name> 
* @since <pre>05/17/2019</pre> 
* @version 1.0 
*/ 
public class UserOperationTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: borrowBook(String readerid, String bookId) 
* 
*/ 
@Test
public void testBorrowBook() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBorrowInfo(String readId) 
* 
*/ 
@Test
public void testGetBorrowInfo() throws Exception { 
//TODO: Test goes here...
    String s = new UserOperation().getBorrowInfo("10010");
    System.out.println(s);
} 


} 
