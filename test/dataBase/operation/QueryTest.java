package dataBase.operation; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.sql.ResultSet;

/** 
* Query Tester. 
* 
* @author <Authors name> 
* @since <pre>04/30/2019</pre> 
* @version 1.0 
*/ 
public class QueryTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: queryBookId(String id) 
* 
*/ 
@Test
public void testQueryBookId() throws Exception { 
//TODO: Test goes here...
    Query query = new Query();
    ResultSet resultSet = query.queryBookId("11");
    System.out.println(resultSet.next());
} 

/** 
* 
* Method: queryReaderId(String id) 
* 
*/ 
@Test
public void testQueryReaderId() throws Exception { 
//TODO: Test goes here...
    Query query = new Query();
    ResultSet resultSet = query.queryReaderId("3");
    if (resultSet.next()){
        System.out.println(resultSet.getString(1));
    }
}


/** 
* 
* Method: queryAllBook() 
* 
*/ 
@Test
public void testQueryAllBook() throws Exception { 
//TODO: Test goes here... 
}

@Test
public void testQueryUsePassInfo() throws Exception{
    Query query = new Query();
    query.querUsePassInfo("1","00000000");
    }

/** 
* 
* Method: setStatement(Statement statement) 
* 
*/ 
@Test
public void testSetStatement() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setResultSet(ResultSet resultSet) 
* 
*/ 
@Test
public void testSetResultSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: closeStatement() 
* 
*/ 
@Test
public void testCloseStatement() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: query(String SQL, String... values) 
* 
*/ 
@Test
public void testQuery() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Query.getClass().getMethod("query", String.class, String....class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
