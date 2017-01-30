import scala.io._
def word = args(0)
def a = Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray
def b = a.map(_.toLowerCase)
def c = b.filter(_ != "")
def signleWord = c.map(_.split("[^a-zA-Z]+")).flatten
val singlewordCount = signleWord.filter(_ == word).length
print("there are "+singlewordCount + " times occurrences of word " + word)