import junit.framework.TestCase

/**
 * @author zw
 * @date 2019-8-27
 */
class HelloWorld extends TestCase {

    static void main(String[] args) {
        def hello = 'helloworld!'
        println(hello)
    }

    /**
     byte -这是用来表示字节值。例如2。
     short -这是用来表示一个短整型。例如10。
     int -这是用来表示整数。例如1234。
     long -这是用来表示一个长整型。例如10000090。
     float -这是用来表示32位浮点数。例如12.34。
     double -这是用来表示64位浮点数，这些数字是有时可能需要的更长的十进制数表示。例如12.3456565。
     char -这定义了单个字符文字。例如“A”。
     Boolean -这表示一个布尔值，可以是true或false。
     String -这些是以字符串的形式表示的文本。例如，“Hello World”的。

     java.math.BigInteger	不可变的任意精度的有符号整数数字	30克
     java.math.BigDecimal	不可变的任意精度的有符号十进制数	3.5克
     */
    void testData() {
        //Example of a int datatype
        int x = 5;

        //Example of a long datatype
        long y = 100L;

        //Example of a floating point datatype
        float a = 10.56f;

        //Example of a double datatype
        double b = 10.5e40;

        //Example of a BigInteger datatype
        BigInteger bi = 30g;
        bi = 600
        //Example of a BigDecimal datatype
        BigDecimal bd = 3.5g;

        println(x);
        println(y);
        println(a);
        println(b);
        println(bi);
        println(bd);

        def rang = 0..10
        rang.forEach { f -> println(f) }

        println()
    }

    def static int sum(x, y = 10) {
        return x + y
    }

    /**
     * Groovy 方法, 形参默认值
     */
    def void testMethod() {
        def sum = sum(2)
        println(sum)
    }
}
