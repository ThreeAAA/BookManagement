package manage.circulation;

import dataBase.operation.SearchBao;

import java.sql.ResultSet;

/**
 * 搜索各种信息
 */
public class Search implements ISearchDAO{
    static SearchBao search = new SearchBao();

    @Override
    public ResultSet searchBook(String bookTitle) {
        return search.searchTitle(bookTitle);
    }

    @Override
    public ResultSet searchAuthor(String bookAuthor) {
        return search.searchAuthor(bookAuthor);
    }

    @Override
    public ResultSet searchPublisher(String bookPress) {
        return search.searchPublisher(bookPress);
    }

    @Override
    public ResultSet searchName(String bookCategory) {
        return search.searchName(bookCategory);
    }

    @Override
    public ResultSet searchReaderId(String bookCategory) {
        return search.searchReaderId(bookCategory);
    }
}
