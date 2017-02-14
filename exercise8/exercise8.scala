/** Xingeng Wang
 *  xiw031
 *  11144515
 */
 
import scala.util._
import scala.io._
import scala.io.StdIn._
// (1)
scala> for {
     | v1 <- Try(readLine("Please input a Double: ").toDouble)
     | v2 <- Try(readLine("Please input another Double: \n").toDouble)
     | result = v1+v2
     | } yield result
Please input a Double: res6: scala.util.Try[Double] = Failure(java.lang.NumberFormatException: For input string: "qqq")

 
scala> for {
     | v1 <- Try(readLine("Please input a Double: ").toDouble)
     | v2 <- Try(readLine("Please input another Double: \n").toDouble)
     | result = v1+v2
     | } yield result
Please input a Double: Please input another Double:
res7: scala.util.Try[Double] = Success(4.3)

// (2)
scala> for {
     | v1 <- Try(readLine("Please input an interge: \n").toInt)
     | v2 <- Try(readLine("Please input another interge: \n").toInt)
     | result = v1/v2
     | } yield result
Please input an interge:
Please input another interge:
res8: scala.util.Try[Int] = Success(5)

scala> for {
     | v1 <- Try(readLine("Please input an interge: \n").toInt)
     | v2 <- Try(readLine("Please input another interge: \n").toInt)
     | result = v1/v2
     | } yield result
Please input an interge:
Please input another interge:
res9: scala.util.Try[Int] = Failure(java.lang.NumberFormatException: For input string: "d")

scala> for {
     | v1 <- Try(readLine("Please input an interge: \n").toInt)
     | v2 <- Try(readLine("Please input another interge: \n").toInt)
     | result = v1/v2
     | } yield result
Please input an interge:
res10: scala.util.Try[Int] = Failure(java.lang.NumberFormatException: For input string: "ww")

scala> for {
     | v1 <- Try(readLine("Please input an interge: \n").toInt)
     | v2 <- Try(readLine("Please input another interge: \n").toInt)
     | result = v1/v2
     | } yield result
Please input an interge:
Please input another interge:
res11: scala.util.Try[Int] = Failure(java.lang.ArithmeticException: / by zero)


// (3)
scala> for {
     | v1 <- Try(readLine("Please input an Double: \n").toDouble)
     | v2 <- Try(readLine("Please input second Double: \n").toDouble)
     | v3 <- Try(readLine("Please input third Double: \n").toDouble)
     | result = v1+v2+v3
     | } yield result
Please input an Double:
Please input second Double:
Please input third Double:
res12: scala.util.Try[Double] = Success(6.6)

scala> for {
     | v1 <- Try(readLine("Please input an Double: \n").toDouble)
     | v2 <- Try(readLine("Please input second Double: \n").toDouble)
     | v3 <- Try(readLine("Please input third Double: \n").toDouble)
     | result = v1+v2+v3
     | } yield result
Please input an Double:
Please input second Double:
res13: scala.util.Try[Double] = Failure(java.lang.NumberFormatException: For input string: "ww")

// no much different will be happen when we change to 4 or 5 input
// each time it take one input it will check the input right away