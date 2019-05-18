package dataBase.operation; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* Insert Tester. 
* 
* @author <Authors name> 
* @since <pre>05/15/2019</pre> 
* @version 1.0 
*/ 
public class InsertTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setBookInfo(String bookId, String title, String athor, String press, String category, String total, String surplus) 
* 
*/ 
@Test
public void testSetBookInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setBorrowingInfo(String readId, String bookId, String returnTime, String lendTime) 
* 
*/ 
@Test
public void testSetBorrowingInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setUserPsd(String userId, String password) 
* 
*/ 
@Test
public void testSetUserPsd() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setUserInfo(String userId, String name, String sex) 
* 
*/ 
@Test
public void testSetUserInfo() throws Exception { 
//TODO: Test goes here...
    Insert insert = new Insert();
    boolean temp = insert.setUserInfo("123456","姚明","男");
    System.out.println(temp);
} 

/** 
* 
* Method: setReturnInfo(String readerId, String bookId, String date) 
* 
*/ 
@Test
public void testSetReturnInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insert(String SQL, String... value) 
* 
*/ 
@Test
public void testInsert() throws Exception { 
//TODO: Test goes here... 
} 


} 
