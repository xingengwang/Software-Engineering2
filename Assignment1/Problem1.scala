// This assignment is done by pair programming
// Xingneg Wang		|	Jared Manderscheid
// 11144515			|	11159557
// xiw031			|	jam943


import scala.math._
// (1)
val vec = Vector(-4.0,0.0,1.0,4.0,9.0)
vec.filter(_>=0).map(sqrt(_)).reduce(_+_)

// (2) a
val vec =Vector("a","aa","aaa","aaaa","aaaaa")
def functionTwoA(str:String)(vec:Vector[String])(threshold:Int): Vector[String] = {vec.filter(_.length > threshold ).map(str+_)
 ++ vec.filter(_.length <= threshold)}

// (2) b
val TwoB = functionTwoA ("qq")_
val TwoBB = TwoB(vec)
val TwoBC = TwoBB(3)

TwoB(vec)(3)

// (2) c
val TwoC = functionTwoA (_:String)(vec)_
val TwoC2 = TwoC("qqq")
val TwoC3 = TwoC2(3)
TwoC ("qqq") (3)

// (2) d
// One scenario is if you have a object being passed around, and all parameters are not provided in one place in the code.
// Function currying will allow you to maintain the required levels of abstraction while passing the function around.
// As well, function currying also allows for easier reuse of abstract and anonymous functions.
//

// (3)
val vec = Vector("xin","xingeng","wang","jared","manderscheid")
// first alternative 
def function3(str:String)(vec:Vector[String])(threshold:Int): Vector[String] = {vec.filter(_.length > threshold )
		.map(str+_).sortBy(_.length)}

function3("qq")(vec)(4)

// second alternative
def function3(str:String)(vec:Vector[String])(threshold:Int): Vector[String] = {vec.filterNot(_.length <= thresho
ld ).sortWith( (left,right) => left.length<right.length )}.map(str+_)
function3("qq")(vec)(4)

// (4)
// a
val sampleTimes = (1 to 1000).toVector
// b
def function4b(offset:Double, frequency:Double, amplitude:Double) = ((time:Double) => (offset+amplitude*sin(2*3.14159*frequency*time)))
val WaveformCreater = function4b(1.0,20.0,2.0)
WaveformCreater(3.3)

// C
val WaveformCreater = function4b(10.0,0.1,1.0)
val Waveform = sampleTimes.map(WaveformCreater(_))

// d 
def RMSFromMean(vec:Vector[Double]):Double = {
     val average = (vec.reduce(_+_))/(vec.length)
     val temp = vec.map(_-average)
     return sqrt(temp.map(pow(_,2)).reduce(_+_)/vec.length)}
	 
val result = RMSFromMean(Waveform)