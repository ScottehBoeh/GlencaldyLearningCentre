package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumMediaType;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class ObjectStockBook extends ObjectStock {

    private int ISBN;
    private String bookAuthor;
    private String bookTitle;
    private String bookSubjectArea;
    private String bookPublisher;
    private int bookNumberOfPages;

    public ObjectStockBook() {
        setMediaType(EnumMediaType.BOOK);
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookSubjectArea() {
        return bookSubjectArea;
    }

    public void setBookSubjectArea(String bookSubjectArea) {
        this.bookSubjectArea = bookSubjectArea;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public int getBookNumberOfPages() {
        return bookNumberOfPages;
    }

    public void setBookNumberOfPages(int bookNumberOfPages) {
        this.bookNumberOfPages = bookNumberOfPages;
    }

    @Override
    public String getUnderstandableName() {
        return this.bookTitle;
    }

}
