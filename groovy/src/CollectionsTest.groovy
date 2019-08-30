import junit.framework.TestCase

/**
 * @author zw
 * @date 2019-8-27
 */
class CollectionsTest extends TestCase {

    void testCollections() {
        int[] arr = 0..100
        List<Integer> list = new ArrayList<>()
        list.addAll(arr)

        list.forEach { f -> println(f) }

    }
}
