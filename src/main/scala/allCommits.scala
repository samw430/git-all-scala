import ammonite.ops._
import ammonite.ops.ImplicitWd._
import scala.collection.mutable.ListBuffer

object allCommits{
  def main( args: Array[String] ): Unit = {
    %('rm,"-rf","hello-world")(home)
    val sourcePath = home/"hello-world"

    %.git("clone","https://github.com/samw430/hello-world.git")(home)
    println("Clone success")

    val log = %%("git","log")(sourcePath)
    var logString = log.toString

    println( logString )

    val logListBuffer = new ListBuffer[String]()

    println("logString Complete")

    while( logString.contains("Author:")){
      var indexPosition = logString.indexOf("commit ")
      var hash = logString.substring( indexPosition + 7 , indexPosition + 47 )
      logListBuffer.append(hash)
      logString = logString.substring(indexPosition + 60 )

    }

    print( logListBuffer )

    %('rm,"-rf","Desktop/All-Commits")(home)
    mkdir! home/"Desktop"/"All-Commits"
    val destinationPath = home/"Desktop"/"All-Commits"

    val logIterator = logListBuffer.toIterator

    while( logIterator.hasNext ){
      var currentHash = logIterator.next

      mkdir! destinationPath/currentHash
      var currentPath = destinationPath/currentHash
      %.git('init)(currentPath)
      %%("git","remote","add","upstream",sourcePath)(currentPath)
      %%("git","fetch", "upstream")(currentPath)
      %%("git","checkout",currentHash)(currentPath)
    }
  }
}


