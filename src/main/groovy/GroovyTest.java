import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author zw
 * @date 2019-8-30
 */
public class GroovyTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");
        Foo foo = context.getBean(Foo.class);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("输入任意键");
            in.next();
            foo.show();
        }
    }
}
