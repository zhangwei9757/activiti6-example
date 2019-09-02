import junit.framework.TestCase

/**
 * @author zw
 * @date 2019-8-27
 */
class DateTest extends TestCase {

    void testDate() {
        Date date = new Date()
        Date date1 = new Date(1)
        Date date2 = new Date(2)

        println(date.toString())
        println(date1.toString())
        println(date2.toString())

    }

    void testRegex() {
        def regex = "groovy"
        if (regex =~ "^groovy\$") {
            println("$regex")
        }

    }
}
