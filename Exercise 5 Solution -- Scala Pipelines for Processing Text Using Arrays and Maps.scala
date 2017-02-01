import scala.io._
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filter(_ != "")
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filter(_.length > 0)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filterNot(_.length == 0)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filterNot(_.length == 0).split("[^a-zA-Z]+")
// mapping gives use an Array of Arrays, which isn't what we want
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filterNot(_.length == 0).map(_.split("[^a-zA-Z]+"))
// We want to use flatMap, in order to return an array of Strings
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filterNot(_.length == 0).flatMap(_.split("[^a-zA-Z]+"))
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+"))
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.filterNot(_.length == 0).flatMap(_.split("[^a-zA-Z]+"))
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+").toLowerCase).filterNot(_.length == 0)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+"))
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase)
// group by the string itself, to collect all values that are associated with the same word (representing the instances of the word in the text)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str)
// map the key (itself a collection) to the size of that collection (the count of occurrences of the word)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str).mapValues(_.size)
// since we associate each of the values with a key and a value, create a sequence of pairs
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str).mapValues(_.size).toSeq
// ok, now sort by the count of occurrences
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str).mapValues(_.size).toSeq.sortWith((kv1,kv2) => kv1._2 > kv2._2)
// ok, now group by the length of the word, so that we can count the number of occurrences of that word length
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str.length).mapValues(_.size).toSeq.sortWith((kv1,kv2) => kv1._2 > kv2._2)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str.length)
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str.length).mapValues(_.size).toSeq.sortWith((kv1,kv2) => kv1._2 > kv2._2)
// now sort by decreasing length of the word!
Source.fromFile("document.txt")("ISO-8859-1").getLines.toArray.flatMap(_.split("[^a-zA-Z]+")).filterNot(_.length == 0).map(_.toLowerCase).groupBy((str:String) => str.length).mapValues(_.size).toSeq.sortWith((kv1,kv2) => kv1._1 < kv2._1)
