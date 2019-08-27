package com.example.xml;

import com.example.xml.dto.Book;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.dom4j.ElementHandler;
import org.dom4j.io.SAXReader;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zw
 * @date 2019-8-21
 * <p>
 * DOM,SAX,JDOM,DOM4J 四种方式解析xml
 */
public class XmlTest extends TestCase {

    /**
     * SAX采用事件处理的方式解析XML文件，利用 SAX 解析 XML 文档，涉及两个部分：解析器和事件处理器
     * 解析器：     解析器可以使用JAXP（javax.xml.parsers）的API创建
     * 事件处理器： SAX API中主要有四种处理事件的接口，它们分别是
     * ContentHandler，
     * DTDHandler，
     * EntityResolver,
     * ErrorHandler
     * <p>
     * 特点：
     * 1. 边读边解析，应用于大型XML文档
     * 2. 只支持读
     * 3. 访问效率低
     * 4. 逐行扫描文件，顺序访问
     */
    public void testSAX() throws Exception {
        InputStream path = new FileInputStream(new File("E:\\b\\activiti6\\src\\test\\java\\com\\example\\xml\\book.xml"));

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();

        saxParser.parse(path, handler);

        handler.getBooks().forEach(System.out::println);
    }


    /**
     * DOM
     * 特点：
     * 1. 解析器读入整个文档，然后构建一个驻留内存的树结构
     * 2. 整个文档树在内存中，便于操作；支持删除、修改、重新排列等多种功能；访问效率高。
     */
    public void testDOM() throws Exception {
        InputStream path = new FileInputStream(new File("E:\\b\\activiti6\\src\\test\\java\\com\\example\\xml\\book.xml"));

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

        Document document = builder.parse(path);
        NodeList books = document.getElementsByTagName("book");

        List<Book> bookList = Lists.newArrayListWithExpectedSize(books.getLength());

        for (int i = 0; i < books.getLength(); i++) {
            Node node = books.item(i);
            Book book = new Book();

            // 解析当前节点的属性
            NamedNodeMap attributes = node.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node n = attributes.item(j);
                String nodeName = n.getNodeName();
                if (Objects.equals(nodeName, "id")) {
                    book.setId(n.getNodeValue());
                }
            }

            // 解析当前节点的子节点
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node n = childNodes.item(j);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = n.getNodeName();
                    String nodeValue = n.getFirstChild().getNodeValue();

                    if ("name".equals(nodeName)) {
                        book.setName(nodeValue);
                    } else if ("price".equals(nodeName)) {
                        book.setPrice(nodeValue);
                    } else if ("author".equals(nodeName)) {
                        book.setAuthor(nodeValue);
                    }
                }
            }

            // 生成节点对应元素
            bookList.add(book);
        }

        bookList.stream().forEach(System.out::println);
    }

    /**
     * JDOM
     * <p>
     * 特点：
     * JDOM
     * 优点:①是基于树的处理XML的Java API，把树加载在内存中
     * ②没有向下兼容的限制，因此比DOM简单
     * ③速度快，缺陷少
     * ④具有SAX的JAVA规则
     * <p>
     * 缺点:①不能处理大于内存的文档
     * ②JDOM表示XML文档逻辑模型。不能保证每个字节真正变换。
     * ③针对实例文档不提供DTD与模式的任何实际模型。
     * ④不支持与DOM中相应遍历包
     * 最适合于:JDOM具有树的便利，也有SAX的JAVA规则。在需要平衡时使用
     */
    public void testJDOM() throws Exception {
        InputStream path = new FileInputStream(new File("E:\\b\\activiti6\\src\\test\\java\\com\\example\\xml\\book.xml"));
        SAXBuilder builder = new SAXBuilder();

        org.jdom.Document document = builder.build(path);
        Element root = document.getRootElement();
        List<Element> children = root.getChildren("book");

        List<Book> bookList = new ArrayList<>();

        for (Element element : children) {
            Book book = new Book();
            book.setId(element.getAttribute("id").getValue());
            book.setAuthor(element.getChildText("author"));
            book.setName(element.getChildText("name"));
            book.setPrice(element.getChildText("price"));
            bookList.add(book);
        }

        bookList.stream().forEach(System.out::println);
    }

    /**
     * DOM4J
     * 特点：
     * DOM4J
     * 具有性能优异、灵活性好、功能强大和极端易用的特点是一个开放源代码的软件
     */
    public void testDOM4J() throws Exception {
        InputStream path = new FileInputStream(new File("E:\\b\\activiti6\\src\\test\\java\\com\\example\\xml\\book.xml"));
        SAXReader reader = new SAXReader();

        org.dom4j.Document document = reader.read(path);
        org.dom4j.Element root = document.getRootElement();

        List<Book> bookList = new ArrayList<>();

        Iterator iterator = root.elementIterator();
        while (iterator.hasNext()) {
            org.dom4j.Element next = (org.dom4j.Element) iterator.next();
            Book book = new Book();
            book.setId(next.attributeValue("id"));
            book.setPrice(next.elementText("price"));
            book.setAuthor(next.elementText("author"));
            book.setName(next.elementText("name"));
            bookList.add(book);
        }

        bookList.stream().forEach(System.out::println);

    }

}
