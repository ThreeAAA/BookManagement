package manage.circulation;

/**
 *
 */
public class Search implements ISearchDAO{
    @Override
    public boolean searchBook(String bookTitle) {
        return false;
    }

    @Override
    public boolean searchAuthor(String bookAuthor) {
        return false;
    }

    @Override
    public boolean searchPress(String bookPress) {
        return false;
    }

    @Override
    public boolean searchCategory(String bookCategory) {
        return false;
    }
}
