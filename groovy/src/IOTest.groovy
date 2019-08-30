import junit.framework.TestCase

/**
 * @author zw
 * @date 2019-8-27
 */
class IOTest extends TestCase {

    private final File file = new File("src/HelloWorld.groovy")
    private final File targetFile = new File("src/DemoIO.txt")
    private final File rootPath = new File("src" + File.separator)

    /**
     java.io.File
     java.io.InputStream
     java.io.OutputStream
     java.io.Reader
     java.io.Writer
     */
    void testIO() {
        file.eachLine { line -> println("line: $line") }

        println(file.text)

        targetFile.withWriter("UTF-8") {
            line -> line.write("IOTest")
        }
        println(file.isAbsolute())
        println(file.isDirectory())
        println(file.isFile())
        println(file.isHidden())
        println(file.exists())
        println(file.file)
        println(file.length())
        println(file.lastModified())
        println(file.absolutePath)

        println()
        targetFile << file.text

        println("=================================")
        rootPath.eachFileRecurse {
            file -> println(file.absolutePath)
        }

        def random = Math.random()
        println(random)

        println("zw" * 3)

        // Example of an Integer using def
        def rint = 10..1
        println(rint)
        println(rint.isReverse())
    }
}
