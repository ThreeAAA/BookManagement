package dataBase.operation; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* UpdateUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>05/16/2019</pre> 
* @version 1.0 
*/ 
public class UpdateUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: updateBookInfo(String bookId, String title, String author, String press, String category, String time, String total, String surplus) 
* 
*/ 
@Test
public void testUpdateBookInfo() throws Exception { 
//TODO: Test goes here...
    new UpdateUtil().updateBookInfo("1", "1","22","23","56","2013-01-11","89","99");
} 


/** 
* 
* Method: update(String SQL, String... values) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = UpdateUtil.getClass().getMethod("update", String.class, String....class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
