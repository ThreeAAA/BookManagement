package unit; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* MD5 Tester. 
* 
* @author <Authors name> 
* @since <pre>05/15/2019</pre> 
* @version 1.0 
*/ 
public class MD5Test { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: md5(String plainText) 
* 
*/ 
@Test
public void testMd5() throws Exception { 
//TODO: Test goes here...
    String str = MD5.md5("00000000");
    System.out.println(str);
} 


} 
