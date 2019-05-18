package manage.search;

import dataBase.operation.DateUtil;
import dataBase.operation.Delete;
import dataBase.operation.Insert;
import dataBase.operation.Query;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrowing {
    Insert insert = null;

    /**
     * 借出书籍
     * @param readId 读者证号，需要与读者信息表验证
     * @param bookId 图书编号，需要与图书信息表验证
     * @throws SQLException 连接数据库出现问题
     * @throws IOException 读取配置文件出现问题
     */
    public boolean addInto(String readId, String bookId) throws SQLException, IOException {
        String borrowDay = DateUtil.getNowDay(); //获取现在时间，表示借书时间
        String futureDate = DateUtil.getFutureDate(1); //获取一个月后的日期，表示应还书日期
        if (insert == null){
            this.insert = new Insert();  //建立连接
        }
        if(this.verifyBasicInfo(readId, bookId) && !this.verfyBorrowInfo(readId,bookId)){ //id存在且没有借书记录
            return insert.setBorrowingInfo(readId,bookId, borrowDay, futureDate);
        }else {
            return false;
        }

    }

    /**
     * 归还书籍
     * 成功归还需要有借出记录，还书完成后借出记录删除
     * @param readerId 读者证号，需要与读者信息表验证
     * @param bookId 图书编号，需要与图书信息表验证
     * @throws SQLException 连接数据库出现问题
     * @throws IOException 读取配置文件出现问题
     */
    public boolean removeInto(String readerId, String bookId) throws SQLException, IOException{
        String returnDay = DateUtil.getNowDay();
        Delete delete = new Delete();
        if (insert == null){
            this.insert = new Insert();  //建立连接
        }
        if(this.verfyBorrowInfo(readerId, bookId)){  //书已经借出
            return (delete.deleteBorrowInfo(readerId,bookId) && insert.setReturnInfo(readerId,bookId, returnDay)); //删除借出记录，且增加还书记录
        }else {
            return false;
        }
    }

    /**
     * 验证readId和bookId正确性
     * 看读者信息表和图书信息表里面存在不
     */
    private boolean verifyBasicInfo(String readId, String bookId) throws SQLException, IOException{
        Query query = new Query();
        ResultSet resultSet = query.queryReaderId(readId);
        if(!resultSet.next()){
            return false;
        }
        query.closeStatement();  //close
        resultSet = query.queryBookId(bookId);
        if(!resultSet.next()){
            return false;
        }
        query.closeStatement();
        return true;
    }

    private boolean verfyReturnInfo(String readId, String bookId) throws SQLException, IOException{
        Query query = new Query();
        ResultSet resultSet = query.queryReturnInfo(readId, bookId);
        if (resultSet.next()){
            query.closeStatement();
            return true;
        }else {
            query.closeStatement();
            return false;
        }

    }

    public boolean verfyBorrowInfo(String readId, String bookId) throws SQLException, IOException{
        Query query = new Query();
        ResultSet resultSet = query.queryBorrowInfo(readId, bookId);
        if (resultSet.next()){
            query.closeStatement();
            return true;
        }else {
            query.closeStatement();
            return false;
        }

    }
}
