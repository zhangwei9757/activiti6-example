package com.example.google;

import com.example.google.dto.Employee;
import com.example.google.dto.InvalidInputException;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwt on 2019-8-13
 * <p>
 * https://www.yiibai.com/guava/guava_throwables_class.html
 */
public class GoogelGuavaTestMethods {
    public static double sqrt(double input) throws IllegalArgumentException {
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    public static int sum(Integer a, Integer b) {
        a = Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b,
                "Illegal Argument passed: Second parameter is Null.");
        return a + b;
    }

    public static int getValue(int input) {
        int[] data = {1, 2, 3, 4, 5};
        Preconditions.checkElementIndex(input, data.length,
                "Illegal Argument passed: Invalid index.");
        return 0;
    }


    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }

    /**
     * @param range
     */
    public static void printRange(Range<Integer> range) {
        System.out.print("[ ");
        for (int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade + " ");
        }
        System.out.println("]");
    }

    /**
     * @throws InvalidInputException
     */
    public static void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt2(-3.0);
        } catch (Throwable e) {
            //check the type of exception and throw it
            Throwables.propagateIfInstanceOf(e, InvalidInputException.class);
            Throwables.propagate(e);
        }
    }

    public static void showcaseThrowables1() {
        try {
            int[] data = {1, 2, 3};
            getValue(data, 4);
        } catch (Throwable e) {
            Throwables.propagateIfInstanceOf(e, IndexOutOfBoundsException.class);
            Throwables.propagate(e);
        }
    }

    public static double sqrt2(double input) throws InvalidInputException {
        if (input < 0) throw new InvalidInputException();
        return Math.sqrt(input);
    }

    public static double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public static void dummyIO() throws IOException {
        throw new IOException();
    }

    /**
     * Googel Guava 集合
     */
    public static Multimap<String, String> getMultimap() {
        //Map<String, List<String>>
        // lower -> a, b, c, d, e
        // upper -> A, B, C, D

        Multimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");
        return multimap;
    }

    /**
     * Google Guava 缓存
     */
    public static Employee getFromDatabase(String empId) {
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map database = new HashMap();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for" + empId);
        return (Employee) database.get(empId);
    }

}
