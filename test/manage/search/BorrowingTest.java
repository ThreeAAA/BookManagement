package manage.search; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Borrowing Tester. 
* 
* @author <Authors name> 
* @since <pre>04/30/2019</pre> 
* @version 1.0 
*/ 
public class BorrowingTest { 
Borrowing b = null;
@Before
public void before() throws Exception {
    b = new Borrowing();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addInto(String readId, String bookId) 
* 
*/ 
@Test
public void testAddInto() throws Exception { 
//TODO: Test goes here...
    if(b.addInto("1","12")){
        System.out.println("OK");
    }else {
        System.out.println("NO");
    }
} 

/** 
* 
* Method: removeInto() 
* 
*/ 
@Test
public void testRemoveInto() throws Exception { 
//TODO: Test goes here...
    testAddInto();
    if(b.removeInto("10010","11")){
        System.out.println("OK");
    }else {
        System.out.println("NO");
    }
} 


/** 
* 
* Method: verify(String readId, String bookId) 
* 
*/ 
@Test
public void testVerify() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Borrowing.getClass().getMethod("verify", String.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
}
@Test
public void testVerifyBorrow() throws Exception{
    System.out.println(b.verfyBorrowInfo("1","11"));

}
} 
