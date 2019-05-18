package manage.account; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* AdminOperation Tester. 
* 
* @author <Authors name> 
* @since <pre>05/16/2019</pre> 
* @version 1.0 
*/ 
public class AdminOperationTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: search(String type, String keywords) 
* 
*/ 
@Test
public void testSearch() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addBook(String bookId, String bookTitle, String bookAuthor, String bookPublish, String bookCategoty, String bookTime, String bookAllAmount, String bookBrwAmount) 
* 
*/ 
@Test
public void testAddBook() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modfiyBook(String bookId, String bookTitle, String bookAuthor, String bookPublish, String bookCategoty, String bookTime, String bookAllAmount, String bookBrwAmount) 
* 
*/ 
@Test
public void testModfiyBook() throws Exception { 
//TODO: Test goes here...
    String t = new AdminOperation().modfiyBook("11","百科全书","姚翔铭","湖南文理出版社","理科","2019-05-15","2","1");
    System.out.println(t);
}

@Test
    public  void testGetAllBookInfo() throws Exception{
    String t = new AdminOperation().getAllBookInfo();
    System.out.println(t);
}


} 
