package manage.circulation;

public interface ISearchDAO {
    public boolean searchBook(String bookTitle);
    public boolean searchAuthor(String bookAuthor);
    public boolean searchPress(String bookPress);
    public boolean searchCategory(String bookCategory);
}
