import ammonite.ops._
import ammonite.ops.ImplicitWd._
import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine

object allCommits{
  def main( args: Array[String] ): Unit = {
    val repoURL = readLine("URL of Repo to Clone: ")
    val sourceFolder = readLine("Name of Repo to be cloned (Must be accurate): ")
    val destinationFolder = readLine("Desired Name for Destination Folder: ")

    val sourcePath = home/"Desktop"/sourceFolder
    val destinationPath = home/"Desktop"/destinationFolder

    if( exists! sourcePath ) {
      println("Source folder already exists cannot execute program")
      System.exit(0)
    }
    if ( exists! destinationPath ){
      println("Destination folder already exists cannot execute program")
      System.exit(0)
    }

    %.git("clone",repoURL)(home/"Desktop")

    val logForIterator = hashCodes(sourceFolder)
    val logIterator = logForIterator.toIterator

    mkdir! home/"Desktop"/destinationFolder

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
  def hashCodes( args: String ): ListBuffer[String] = {
    val source = home/args
    val log = %%("git","log")(source)
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
    return logListBuffer
  }
}


