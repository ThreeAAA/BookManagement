package unit; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* JSONDate Tester. 
* 
* @author <Authors name> 
* @since <pre>05/17/2019</pre> 
* @version 1.0 
*/ 
public class JSONDateTest { 
JSONDate jsonDate1;
JSONDate jsonDate2;
JSONDate jsonDate3;
@Before
public void before() throws Exception {
    jsonDate2 = new JSONDate();
    jsonDate3 = new JSONDate();
    jsonDate1 = new JSONDate();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: put(String key, String value) 
* 
*/ 
@Test
public void testPutForKeyValue() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: putList(String key, JSONDate jsonDate) 
* 
*/ 
@Test
public void testPutList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getLave() 
* 
*/ 
@Test
public void testGetLave() throws Exception { 
//TODO: Test goes here...
    jsonDate1.put("id","11");
    jsonDate1.put("title","数学分析");
    jsonDate1.put("作者","老李");


    jsonDate3.put("id","12");
    jsonDate3.put("title","ddad");
    jsonDate3.put("zhuozhe","laoli");

    jsonDate2.put("数量","3");
    jsonDate2.putList("书籍",jsonDate1);
    jsonDate2.putList("书籍",jsonDate3);
    System.out.println(jsonDate2.toString());
/*
try { 
   Method method = JSONDate.getClass().getMethod("getLave"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: hasKey(String key) 
* 
*/ 
@Test
public void testHasKey() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = JSONDate.getClass().getMethod("hasKey", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: setJsonMap(String key, int start, int end) 
* 
*/ 
@Test
public void testSetJsonMap() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = JSONDate.getClass().getMethod("setJsonMap", String.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
