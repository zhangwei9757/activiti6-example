package com.example.google;

import com.example.google.dto.ExampleCache;
import com.example.google.dto.InvalidInputException;
import com.example.google.dto.Student;
import com.google.common.base.*;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by jwt on 2019-8-13
 * <p>
 * https://www.yiibai.com/guava/guava_throwables_class.html
 */
public class GoogleGuavaTest extends TestCase {

    /**
     * Optional 类
     */

    public void testOptional() {
        Integer value1 = null;
        Integer value2 = new Integer(10);
        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        Integer sum = GoogelGuavaTestMethods.sum(a, b);
        System.out.println(sum);
    }

    /***
     * Preconditions 类
     */

    public void testPreconditions() {
        try {
            System.out.println(GoogelGuavaTestMethods.sqrt(-3.0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(GoogelGuavaTestMethods.sum(null, 3));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(GoogelGuavaTestMethods.getValue(6));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ordering 类
     */

    public void testOrdering() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        Ordering ordering = Ordering.natural();
        System.out.println("Input List: ");
        System.out.println(numbers);

        Collections.sort(numbers, ordering);
        System.out.println("Sorted List: ");
        System.out.println(numbers);

        System.out.println("======================");
        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
        System.out.println("Minimum: " + ordering.min(numbers));
        System.out.println("Maximum: " + ordering.max(numbers));

        Collections.sort(numbers, ordering.reverse());
        System.out.println("Reverse: " + numbers);

        numbers.add(null);
        System.out.println("Null added to Sorted List: ");
        System.out.println(numbers);

        Collections.sort(numbers, ordering.nullsFirst());
        System.out.println("Null first Sorted List: ");
        System.out.println(numbers);
        System.out.println("======================");

        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");

        System.out.println("Another List: ");
        System.out.println(names);

        Collections.sort(names, ordering.nullsFirst().reverse());
        System.out.println("Null first then reverse sorted list: ");
        System.out.println(names);
        DateTime dt = new DateTime();
        DateTime dateTime = dt.plusDays(5);
        System.out.println(dateTime.toLocalDateTime());

        boolean equals = java.util.Objects.equals(null, null);
        System.out.println(equals);
    }

    /**
     * Objects 类
     */

    public void testObjects() {
        Student s1 = new Student("Mahesh", "Parashar", 1, "VI");
        Student s2 = new Student("Suresh", null, 3, null);

        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(
                com.google.common.base.Objects.toStringHelper(s1)
                        .add("Name", s1.getFirstName() + " " + s1.getLastName())
                        .add("Class", s1.getClassName())
                        .add("Roll No", s1.getRollNo())
                        .toString());


    }

    /**
     * Range
     */

    public void testRange() {
        Range<Comparable<?>> all = Range.all();
        Range<Integer> atLeast = Range.atLeast(4);
        Range<Integer> closed = Range.closed(1, 5);
        Range<Integer> closedOpen = Range.closedOpen(1, 5);
        Range<Integer> greaterThan = Range.greaterThan(1);
        Range<Integer> atMost = Range.atMost(1);
        System.out.println(atLeast);
        System.out.println(greaterThan);

        System.out.println("======================================");
        //create a range [a,b] = { x | a <= x <= b}
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.print("[0,9] : ");
        GoogelGuavaTestMethods.printRange(range1);
        System.out.println("5 is present: " + range1.contains(5));
        System.out.println("(1,2,3) is present: " + range1.containsAll(Ints.asList(1, 2, 3)));
        System.out.println("Lower Bound: " + range1.lowerEndpoint());
        System.out.println("Upper Bound: " + range1.upperEndpoint());

        //create a range (a,b) = { x | a < x < b}
        Range<Integer> range2 = Range.open(0, 9);
        System.out.print("(0,9) : ");
        GoogelGuavaTestMethods.printRange(range2);

        //create a range (a,b] = { x | a < x <= b}
        Range<Integer> range3 = Range.openClosed(0, 9);
        System.out.print("(0,9] : ");
        GoogelGuavaTestMethods.printRange(range3);

        //create a range [a,b) = { x | a <= x < b}
        Range<Integer> range4 = Range.closedOpen(0, 9);
        System.out.print("[0,9) : ");
        GoogelGuavaTestMethods.printRange(range4);

        //create an open ended range (9, infinity
        Range<Integer> range5 = Range.greaterThan(9);
        System.out.println("(9,infinity) : ");
        System.out.println("Lower Bound: " + range5.lowerEndpoint());
        System.out.println("Upper Bound present: " + range5.hasUpperBound());

        Range<Integer> range6 = Range.closed(3, 5);
        GoogelGuavaTestMethods.printRange(range6);

        //check a subrange [3,5] in [0,9]
        System.out.println("[0,9] encloses [3,5]:" + range1.encloses(range6));

        Range<Integer> range7 = Range.closed(9, 20);
        GoogelGuavaTestMethods.printRange(range7);
        //check ranges to be connected
        System.out.println("[0,9] is connected [9,20]:" + range1.isConnected(range7));

        Range<Integer> range8 = Range.closed(5, 15);

        //intersection
        GoogelGuavaTestMethods.printRange(range1.intersection(range8));

        //span
        GoogelGuavaTestMethods.printRange(range1.span(range8));

    }

    /**
     * Throwables 类
     */

    public void testThrowables() {
        try {
            GoogelGuavaTestMethods.showcaseThrowables();
        } catch (InvalidInputException e) {
            //get the root cause
            System.out.println(Throwables.getRootCause(e));
        } catch (Exception e) {
            //get the stack trace in string format
            System.out.println(Throwables.getStackTraceAsString(e));
        }

        try {
            GoogelGuavaTestMethods.showcaseThrowables1();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }

    }

    /**
     * Google Guava 集合工具 Multiset Multimap Bimap Table
     */

    public void testMultiset() {
        //create a multiset collection
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        //print the occurrence of an element
        System.out.println("Occurrence of 'b' : " + multiset.count("b"));
        //print the total size of the multiset
        System.out.println("Total Size : " + multiset.size());
        //get the distinct elements of the multiset as set
        Set<String> set = multiset.elementSet();
        //display the elements of the set
        System.out.println("Set [");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("]");
        //display all the elements of the multiset using iterator
        Iterator<String> iterator = multiset.iterator();
        System.out.println("MultiSet [");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("]");
        //display the distinct elements of the multiset with their occurrence count
        System.out.println("MultiSet [");
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement() + ", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        //remove extra occurrences
        multiset.remove("b", 2);
        //print the occurrence of an element
        System.out.println("Occurence of 'b' : " + multiset.count("b"));
    }


    public void testMultimap() {
        Multimap<String, String> multimap = GoogelGuavaTestMethods.getMultimap();

        List<String> lowerList = (List<String>) multimap.get("lower");
        System.out.println("Initial lower case list");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println("Modified lower case list");
        System.out.println(lowerList.toString());

        List<String> upperList = (List<String>) multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());
        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());

        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");
        for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value = multimap.get("lower");
            System.out.println(key + ":" + value);
        }

        System.out.println("Keys of Multimap");
        Set<String> keys = multimap.keySet();
        for (String key : keys) {
            System.out.println(key);
        }

        System.out.println("Values of Multimap");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }


    public void testBimap() {
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");
        empIDNameMap.forcePut(new Integer(101), "testforcePut");

        BiMap<String, Integer> inverse = empIDNameMap.inverse();
        Integer mahesh = inverse.get("Mahesh");
        //Emp Id of Employee "Mahesh"
        System.out.println(mahesh);
    }


    public void testTable() {
        //Table<R,C,V> == Map<R,Map<C,V>>
        /*
         *  Company: IBM, Microsoft, TCS
         *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
         *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan }
         *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil }
         *
         * */
        //create a table
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");

        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "122", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");

        //get Map corresponding to IBM
        Map<String, String> ibmEmployees = employeeTable.row("IBM");

        System.out.println("List of IBM Employees");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        //get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println();

        //get a Map corresponding to 102
        Map<String, String> EmployerMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry : EmployerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }

    /**
     * Google Guava 缓存工具类
     */

    public void testLoadingCache() {
        //create a cache for employees based on their employee id
        LoadingCache employeeCache = CacheBuilder.newBuilder()
                .maximumSize(100) // maximum 100 records can be cached
                .expireAfterAccess(30, TimeUnit.MINUTES) // cache will expire after 30 minutes of access
                .build(new ExampleCache());

        try {
            //on first invocation, cache will be populated with corresponding
            //employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));
            //second invocation, data will be returned from cache
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Google Guava 字符串工具 Joiner, Spiltter, CharMatcher, CaseFormat
     */

    public void testJoiner() {
        String join = Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, 5, null, 6));
        String join2 = Joiner.on(",").useForNull("google").join(Arrays.asList(1, 2, 3, 4, 5, null, 6));
        System.out.println(join2);

        StringBuilder appendTo = Joiner.on(",").appendTo(new StringBuilder("google"), 1, 2);
        System.out.println(appendTo);

        Map<String, String> maps = Maps.newHashMap();
        maps.put("id", "1");
        maps.put("name", "2");
        String ss = Joiner.on("&").withKeyValueSeparator('=').join(maps);
        System.out.println(ss);
    }


    public void testSplitter() {
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog.");

        Stream.of(split).forEach(System.out::println);

        Map<String, String> split1 = Splitter.on("&").withKeyValueSeparator("=").split("id=1&name=google");
        System.out.println(split1);
    }


    public void testCharMatcher() {
        String digits = CharMatcher.digit().retainFrom("mahesh123");
        System.out.println(digits); // only the digits

        String whitesapce = CharMatcher.whitespace().trimAndCollapseFrom("     Mahesh     Parashar ", ' ');
        System.out.println(whitesapce);
        // trim whitespace at ends, and replace/collapse whitespace into single spaces

        String javaDigit = CharMatcher.javaDigit().replaceFrom("mahesh123", "*");
        System.out.println(javaDigit); // star out all digits

        javaDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("mahesh123");
        System.out.println(javaDigit);
        // eliminate all characters that aren't digits or lowercase

        /*
        CharMatcher本身提供了很多CharMatcher实现类,如下:
        ANY: 匹配任何字符
        ASCII: 匹配是否是ASCII字符
        BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
        DIGIT: 匹配ASCII数字
        INVISIBLE: 匹配所有看不见的字符
        JAVA_DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
        JAVA_ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
        JAVA_LETTER: 匹配字母, 使用 Charater.isLetter() 实现
        JAVA_LETTER_OR_DIGET: 匹配数字或字母
        JAVA_LOWER_CASE: 匹配小写
        JAVA_UPPER_CASE: 匹配大写
        NONE: 不匹配所有字符
        SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
        WHITESPACE: 匹配所有空白字符

        CharMatcher is(char match): 返回匹配指定字符的Matcher
        CharMatcher isNot(char match): 返回不匹配指定字符的Matcher
        CharMatcher anyOf(CharSequence sequence): 返回匹配sequence中任意字符的Matcher
        CharMatcher noneOf(CharSequence sequence): 返回不匹配sequence中任何一个字符的Matcher
        CharMatcher inRange(char startInclusive, char endIncludesive): 返回匹配范围内任意字符的Matcher
        CharMatcher forPredicate(Predicate<? super Charater> predicate): 返回使用predicate的apply()判断匹配的Matcher
        CharMatcher negate(): 返回以当前Matcher判断规则相反的Matcher
        CharMatcher and(CharMatcher other): 返回与other匹配条件组合做与来判断的Matcher
        CharMatcher or(CharMatcher other): 返回与other匹配条件组合做或来判断的Matcher
        boolean matchesAnyOf(CharSequence sequence): 只要sequence中有任意字符能匹配Matcher,返回true
        boolean matchesAllOf(CharSequence sequence): sequence中所有字符都能匹配Matcher,返回true
        boolean matchesNoneOf(CharSequence sequence): sequence中所有字符都不能匹配Matcher,返回true
        int indexIn(CharSequence sequence): 返回sequence中匹配到的第一个字符的坐标
        int indexIn(CharSequence sequence, int start): 返回从start开始,在sequence中匹配到的第一个字符的坐标
        int lastIndexIn(CharSequence sequence): 返回sequence中最后一次匹配到的字符的坐标
        int countIn(CharSequence sequence): 返回sequence中匹配到的字符计数
        String removeFrom(CharSequence sequence): 删除sequence中匹配到到的字符并返回
        String retainFrom(CharSequence sequence): 保留sequence中匹配到的字符并返回
        String replaceFrom(CharSequence sequence, char replacement): 替换sequence中匹配到的字符并返回
        String trimFrom(CharSequence sequence): 删除首尾匹配到的字符并返回
        String trimLeadingFrom(CharSequence sequence): 删除首部匹配到的字符
        String trimTrailingFrom(CharSequence sequence): 删除尾部匹配到的字符
        String collapseFrom(CharSequence sequence, char replacement): 将匹配到的组(连续匹配的字符)替换成replacement
        String trimAndCollapseFrom(CharSequence sequence, char replacement): 先trim在replace
        */

        // 1.获取字符串中数字
        String str1 = "mahesh123ff87f";
        String result1 = CharMatcher.digit().retainFrom(str1);
        System.out.println("1.获取字符串中数字：" + result1);

        // 2.把多个空格替换,并去掉首位的空格
        String str2 = "     Mahesh     Parashar  ";
        String result2 = CharMatcher.whitespace().trimAndCollapseFrom(str2, ' ');
        System.out.println("2.把多个空格替换,并去掉首位的空格" + result2);

        // 3.去掉转义字符(\t,\n,\b...)
        String str3 = " ab\tcd\nef\bg";
        String result3 = CharMatcher.javaIsoControl().removeFrom(str3);
        System.out.println("3.去掉转义字符" + result3);

        // 4.把所有的数字用"*"代替
        String str4 = "124abc85dds";
        String result4 = CharMatcher.javaDigit().replaceFrom(str4, "*");
        System.out.println("4.替换数字：" + result4);

        // 5.获取所有的数字和小写字母
        String str5 = "124abc85ddsAF1HNsd";
        String result5 = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(str5);
        System.out.println("5.获取所有的数字和小写字母：" + result5);

        // 6.获取所有的大写字母
        String result6 = CharMatcher.javaUpperCase().retainFrom(str5);
        System.out.println("6.获取所有的大写字母：" + result6);

        // 7.获取所有单字节长度的符号
        String str7 = ",dg,123AH中国";
        String result7 = CharMatcher.singleWidth().retainFrom(str7);
        System.out.println("7.获取所有单字节长度的符号：" + result7);

        // 8.获取字母
        String str8 = "FirstName LastName +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
        String result8 = CharMatcher.javaLetter().retainFrom(str8);
        System.out.println("8.获取字母：" + result8);


        // 9.获取字母和数字
        String result9 = CharMatcher.javaLetterOrDigit().retainFrom(str8);
        System.out.println("9.获取字母和数字：" + result9);

        // 10.出现次数统计
        Integer count10 = CharMatcher.any().countIn(str8);
        System.out.println("10.出现次数：" + count10);

        // 11.数字出现次数
        Integer count11 = CharMatcher.digit().countIn(str8);

        System.out.println("11.数字出现次数：" + count11);

        // 12.获得除大写字母外其他所有字符
        String result12 = CharMatcher.javaLowerCase().negate().retainFrom(str8);
        System.out.println("12.获得除大写字母外其他所有字符：" + result12);
    }


    public void testCaseFormat() {
        String data = "test_data";
        String to1 = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data");
        String to2 = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data");
        String to3 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data");
        System.out.println(to1);
        System.out.println(to2);
        System.out.println(to3);
    }

    /**
     * Google Guava 原语工具 Bytes, Shorts, Ints, Longs, Floats, Doubles, Chars, Booleans
     */

    public void testBasicTypeTools() {
        /*
         *  Bytes
         */
        byte[] byteArray = {1, 2, 3, 4, 5, 5, 7, 9, 9};

        //convert array of primitives to array of objects
        List<Byte> objectArray = Bytes.asList(byteArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        byteArray = Bytes.toArray(objectArray);
        System.out.print("[ ");
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("]");
        byte data = 5;
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Bytes.contains(byteArray, data));

        //Returns the index
        System.out.println("Index of 5: " + Bytes.indexOf(byteArray, data));

        //Returns the last index maximum
        System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray, data));

        System.out.println("\n===================================================");
        /*
         * Shorts
         */
        short[] shortArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //convert array of primitives to array of objects
        List<Short> objectArrayShort = Shorts.asList(shortArray);
        System.out.println(objectArrayShort.toString());

        //convert array of objects to array of primitives
        shortArray = Shorts.toArray(objectArrayShort);
        System.out.print("[ ");
        for (int i = 0; i < shortArray.length; i++) {
            System.out.print(shortArray[i] + " ");
        }
        System.out.println("]");
        short dataShort = 5;
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Shorts.contains(shortArray, dataShort));

        //Returns the minimum
        System.out.println("Min: " + Shorts.min(shortArray));

        //Returns the maximum
        System.out.println("Max: " + Shorts.max(shortArray));
        dataShort = 2400;
        //get the byte array from an integer
        byte[] byteArrayShort = Shorts.toByteArray(dataShort);
        for (int i = 0; i < byteArrayShort.length; i++) {
            System.out.print(byteArrayShort[i] + " ");
        }
        System.out.println("\n===================================================");
        /*
         * Ints
         */
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //convert array of primitives to array of objects
        List<Integer> objectArrayInt = Ints.asList(intArray);
        System.out.println(objectArrayInt.toString());

        //convert array of objects to array of primitives
        intArray = Ints.toArray(objectArrayInt);
        System.out.print("[ ");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println("]");
        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Ints.contains(intArray, 5));

        //Returns the minimum
        System.out.println("Min: " + Ints.min(intArray));

        //Returns the maximum
        System.out.println("Max: " + Ints.max(intArray));

        //get the byte array from an integer
        byte[] byteArrayInt = Ints.toByteArray(20000);
        for (int i = 0; i < byteArrayInt.length; i++) {
            System.out.print(byteArrayInt[i] + " ");
        }
        System.out.println("\n===================================================");

    }

    public void testNonatime() {

    }
}
