import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/**
 * @author zw
 * @date 2019-8-27
 *
 *
 JsonSlurper	 JsonSlurper是一个将JSON文本或阅读器内容解析为Groovy数据的类
 结构，例如地图，列表和原始类型，如整数，双精度，布尔和字符串。
 JsonOutput	    此方法负责将Groovy对象序列化为JSON字符串。
 */
class JsonTest extends GroovyTestCase {

    private final String jsonTxt = '{ "name": "John", "ID" : "1"}'

    /**
     * json -> object
     */
    void testJsonParser() {
        JsonSlurper slurper = new JsonSlurper()
        def rtn = slurper.parseText jsonTxt
        println(rtn.name)
        println(rtn.ID)
        rtn.each { println(it) }
    }

    /**
     * object -> json
     */
    void testJsonOutPut() {
        def json = JsonOutput.toJson([name: 'zzww', id: 1])
        println(json)
    }
}
