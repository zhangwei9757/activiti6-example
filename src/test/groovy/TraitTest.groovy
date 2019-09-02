import junit.framework.TestCase

/**
 * @author zw
 * @date 2019-8-27
 */
trait fly {
    void showFly() {
        println("飞行功能...")
    }
}

class TraitTest extends TestCase implements fly {

    static void main(String[] args) {
        TraitTest traitTest = TraitTest.class.newInstance()
        traitTest.showFly()
    }

    void testClosure() {
        def list = 1..4
        def result = list.find { element -> element > 2 }
        println("find: $result")

        result = list.findAll { element -> element > 2 }
        println("findAll: $result")

        result = list.any { element -> element > 2 }
        println("any: $result")

        result = list.collect { element -> element > 2 }
        println("collect: $result")
    }
}
