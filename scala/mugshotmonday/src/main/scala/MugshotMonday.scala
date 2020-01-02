
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.jdk.CollectionConverters._
import java.io.File
import java.io.PrintWriter

import org.jsoup.select.Elements



object MugshotMonday {

  val BASE_URL = "https://salemleader.com/police/mugshotmonday"

  case class Mugshot(name: String, age: String, location: String, img: String, offenses: Array[String]) {
    override def toString: String = s"$name, $age, $location, $offenses"
  }

  def createMugshot(text: String, img: String) = {
    val attrs = text.split(", ")
    if(attrs.length >= 3) {
      val name = attrs(0)
      val age = attrs(1)
      val location = attrs(2)
      val offenses = attrs.slice(3, attrs.length - 1)
      Mugshot(name, age, location, img, offenses)
    } else {

    }
  }

  def parseMugshotsHTML(html: Elements) = {
    if(html != null) {
      html.asScala.map {
        mugshot => {
          val imageTag = mugshot.select("img").first()
          val image = if(imageTag == null) "" else  imageTag.attr("src")
          val descriptionElement = mugshot.select("blockquote > p").first()
          val details = if(descriptionElement == null) "" else descriptionElement.text
          //createMugshot(details, image)
          details
        }
      }
    } else {
      Seq("<invalid format>")
    }
  }

  def main(array: Array[String]) = {

    // Creating a file
    val file_Object = new File("abc.txt" )

    // Passing reference of file to the printwriter
    val print_Writer = new PrintWriter(file_Object)

    for(edition <- 0 to 103) {
      val editionUrl = if(edition == 0) BASE_URL else s"${BASE_URL}-${edition}"
      val document: Document = Jsoup.connect(editionUrl).get
      val mugshotsHTML = document.select(".field-item")
      // val mugshots = parseMugshotsHTML(mugshotsHTML)
      val mugshots = mugshotsHTML.asScala.map {
        mugshotHTML => {
          val mugshotDescriptionElement = mugshotHTML.select("blockquote > p").first()
          if(mugshotDescriptionElement == null)
            s"Failed on edition: $edition"
          else
            s"${mugshotDescriptionElement.text}, $edition"
        }
      }
      mugshots.foreach(mugshot => print_Writer.write(s"$mugshot\n"))
    }

    // Closing printwriter
    print_Writer.close()
  }
}
