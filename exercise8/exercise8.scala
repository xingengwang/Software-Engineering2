/** Xingeng Wang
 *  xiw031
 *  11144515
 */
 
import scala.util._
import scala.io._
import scala.io.StdIn._
for {
	v1 <- Try(readLine().toDouble)
	v2 <- Try(readLine().toDouble)
	result = v1+v2
} yield result

 
