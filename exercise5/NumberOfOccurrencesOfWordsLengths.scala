import scala.io._
def word = args(0).toInt
def a = Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray
def b = a.map(_.toLowerCase)
def c = b.filter(_ != "")
def signleWord = c.map(_.split("[^a-zA-Z]+")).flatten
def signleWorddict = signleWord.filter(_ != "").groupBy(_.length)
val LengthwordCount = signleWorddict.get(word).get

print("there are "+LengthwordCount + " times occurrences of word with length " + word)