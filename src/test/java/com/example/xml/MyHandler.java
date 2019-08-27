package com.example.xml;

import com.example.xml.dto.Book;
import com.google.common.collect.Lists;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * @author zw
 * @date 2019-8-21
 */
public class MyHandler extends DefaultHandler {

    private List<Book> books;
    private Book book;
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        books = Lists.newArrayList();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * @param uri        名称空间 URI，如果元素没有名称空间 URI，或者未执行名称空间处理，则为空字符串
     * @param localName  本地名称（不带前缀），如果未执行名称空间处理，则为空字符串
     * @param qName      限定名（带有前缀），如果限定名不可用，则为空字符串
     * @param attributes 连接到元素上的属性。如果没有属性，则它将是空 Attributes 对象。在 startElement 返回后，此对象的值是未定义的
     * @Description: TODO
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        tag = qName;
        if ("book".equals(qName)) {
            book = new Book();
            book.setId(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        tag = "";
        if ("book".equals(qName)) {
            books.add(book);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String string = new String(ch, start, length);
        if ("name".equals(tag)) {//判断当前内容，属于哪一个元素。
            book.setName(string);
        } else if ("price".equals(tag)) {
            book.setPrice(string);
        } else if ("author".equals(tag)) {
            book.setAuthor(string);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
