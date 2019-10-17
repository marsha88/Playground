
class AsciiArt(image: String) {
  val base_image = image

  // creates a new ascii image by combining base_image with another image
  def +(that: AsciiArt) = {
    val lhs_lines = this.base_image.split("\n")
    val rhs_lines = that.base_image.split("\n")

    AsciiArt(
      lhs_lines
        .zipAll(rhs_lines, "", "")
        .map { case (l1, l2) => l1 + l2 }
        .mkString("\n")
    )
  }

  def plusY(rhs: AsciiArt) = AsciiArt(s"$base_image\n$rhs.base_image")
  
  // creates a new image that repeats the base_image n times (on x and y axis respectively)
  def *(n: Int) = List.fill(n)(AsciiArt(base_image)).fold(AsciiArt.empty)(_ + _)

  def reflectY = base_image

  def reflectX = base_image

  def rotate(angle: Int)(image: AsciiArt) = base_image

  override def toString = base_image
}

object AsciiArt {
  val empty = AsciiArt("")
  def apply(image: String) = new AsciiArt(image)
}

object Main {
   val car = """
              .---;-,                  
          __/_,{)|__;._                 
        ."` _     :  _  `.  .:::;.    .::'
        '--(_)------(_)--' `      '::'
   """
   val semi = """ 
                   _________________
           .--H--.|_________________|
         _//_||  ||                 |
        [    -|  |'--;--------------'
        '-()-()----()"()^^^^^^^()"()'
   """

   val fish = """
                 ,--,_
          __    _\.---'-.
          \ '.-"     // o\
          /_.'-._    \\  /
                `"--(/"`
                 _,--,
              .-'---./_   __
            /o \\     "-.' /
            \  //    _.-'._\
             `"\)--"`  
   """

  def main(args: Array[String]) {
    println(
      AsciiArt(fish) +Y AsciiArt(fish)
    )
  }
}
