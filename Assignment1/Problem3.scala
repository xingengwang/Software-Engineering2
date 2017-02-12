// This assignment is done by pair programming
// Xingneg Wang		|	Jared Manderscheid
// 11144515			|	11159557
// xiw031			|	jam943


import scala.math._

def factorial(a:Array[Long],n:Int):Stream[Long] = {
	val len = a.length
	if (n ==1) {
		return (1).toStream 
	} else {
		return (a[n-1])*factorial(a,n-1)
	}
}
