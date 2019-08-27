package com.example.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

/**
 * @author zw
 * @date 2019-8-27
 */
public class TestGroovy extends TestCase {

    private final File groovyPath = new File("src/test/groovy/TestGroovy.groovy");

    public void testGroovyClassLoader() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();

        GroovyClassLoader groovyLoader = new GroovyClassLoader(classLoader);

        Class groovyClass = groovyLoader.parseClass(groovyPath);
        GroovyObject o = (GroovyObject) groovyClass.newInstance();
        Object rtn = o.invokeMethod("test", "zzzwww");
        System.out.println(rtn);
    }

    public void testGroovyShell() throws Exception {
        GroovyShell shell = new GroovyShell();
        Script script = shell.parse(groovyPath);

        Object[] args = {};
        Object o = script.invokeMethod("test", args);
        System.out.println(o);
    }
}
