// This assignment is done by pair programming
// Xingneg Wang		|	Jared Manderscheid
// 11144515			|	11159557
// xiw031			|	jam943


import scala.math._

// n is the start number of factorial we need
def factorialStream(n:Int):Stream[Long] = {
	var temp =Stream.from(1)
	return temp.take(n).reduce(_*_) #:: factorialStream(n+1)
}

scala> factorialStream(1).take(10).force
res36: scala.collection.immutable.Stream[Long] = Stream(1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800)

def isPrime(k :Int) : Boolean = {
	if (k < 1)
		false
	else if (k == 1)
		true
	else if (k == 2)
		true
	else
		!(2 to (k-1)).exists(x => k % x == 0)
}

def PrimeStream():Stream[Int] = {
	def temp = Stream.from(1)
	return temp.filter(isPrime(_))
}

isPrime: (k: Int)Boolean
PrimeStream: ()Stream[Int]

scala> PrimeStream().take(10).force
res47: scala.collection.immutable.Stream[Int] = Stream(1, 2, 3, 5, 7, 11, 13, 17, 19, 23)