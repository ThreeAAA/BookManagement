package manage.circulation;

import java.sql.ResultSet;

public interface ISearchDAO {
    public ResultSet searchBook(String bookTitle);
    public ResultSet searchAuthor(String bookAuthor);
    public ResultSet searchPublisher(String bookPress);
    public ResultSet searchName(String bookCategory);
    public ResultSet searchReaderId(String bookCategory);
}
