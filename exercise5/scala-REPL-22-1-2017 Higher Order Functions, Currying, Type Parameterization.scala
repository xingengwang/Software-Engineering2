// defining values in scala
val foo = 1
// defining methods in scala
def sum(x:Int, y:Int) = x+y
def foo(j:Int) = j*j
foo(3)
typeParameterizedFoo('A')
typeParameterizedFoo(2)
// defining a method
def myAdd10(n:Int) = n+10
myAdd10(5)
myAdd10(-5)
// defining a function
val myAdd10Fn = (n:Int) => n+10
myAdd10Fn(5)
myAdd10Fn(-5)
// we define a collection
val arr = Array(1,2,3,5,7,11,13)

arr.map(myAdd10Fn)

// Illustration of casting (type coercion)
10.toDouble
10.toString
10.toChar
65.toChar
(-1).toChar
// Map each element of the array to a corresponding element, as determined by applying the function (closure) passed to that original element
arr.map((n:Int) => n+100)
arr.map((n:Int) => n*n)
// The following function returns a closure takes a parameter b and add a to b
def adderGenerator(a:Int) = ((b:Int) => a+b)
// Create a function (closure) that adds 10 to numbers
adderGenerator(10)
adderGenerator(10)(3)
// Create a function (closure) that adds 1 to numbers
adderGenerator(1)(3)
arr.map(adderGenerator(1))
arr
arr.map(adderGenerator(10))
def curriedMult(a:Int)(b:Int) = a*b

// use a partially applied definition; the _ helps it to be treated as a functoin
curriedMult(2)_
curriedMult(2)(3)
curriedMult(2)(4)
curriedMult(2) _
curriedMult(10) _

// Specify a function 
def myMapAndAppend(fn:(Int => Int), seq1:Seq[Int], seq2:Seq[Int]) = seq1.map(fn) ++ seq2.map(fn)
myMapAndAppend((n:Int) => n*n, Array(1,2,3), Array(10,11,12))
myMapAndAppend((n:Int) => 2*n, Array(1,2,3), Array(10,11,12))
// Because the above takes sequences, we can perform the above with Arrays, or with Vectors -- both represent a Seq within Scala's subtyping system for collections
myMapAndAppend((n:Int) => 2*n, Vector(1,2,3), Vector(10,11,12))
// We can mix together the different collection types, as long as they are Seqs
myMapAndAppend((n:Int) => 2*n, Vector(1,2,3), Array(10,11,12))
myMapAndAppend((n:Int) => 2*n, Array(1,2,3), Vector(10,11,12))
// Because the type of the first function has a well-defined type, Scala can infer the type information for that parameter even if we don't given it
myMapAndAppend(n => 2*n, Array(1,2,3), Vector(10,11,12))
myMapAndAppend(n => 2*n, Vector(1,2,3), Vector(10,11,12))

// Now the above for double precision numbers
def myMapAndAppendDouble(fn:(Double => Double), seq1:Seq[Double], seq2:Seq[Double]) = seq1.map(fn) ++ seq2.map(fn)
myMapAndAppendDouble(n => 2*n, Vector(1.0, 2.0, 3.0), Vector(10.0, 11.0, 12.0))

// A type parameterized version of the above
def myMapAndAppendGeneral[T](fn:(T => T), seq1:Seq[T], seq2:Seq[T]) = seq1.map(fn) ++ seq2.map(fn)

// Performing mapping
myMapAndAppendGeneral((n:Int) => 2*n, Vector(1,2,3), Vector(10,11,12))

//**** The following causes problems: it can't infer the parameterized type (T) from the first argument, because the type of that closure is not given
myMapAndAppendGeneral(n => 2*n, Vector(1,2,3), Vector(10,11,12))

// For doubles
myMapAndAppendGeneral((n:Double) => 2*n, Vector(1.0,2.0,3.0), Vector(10.0,11.0,12.0))
// Inconsistent typing in the following precipitates an error message
myMapAndAppendGeneral((n:Double) => 2*n, Vector(1,2,3), Vector(10,11,12))

// another type parameterized function
def typeParameterizedFoo[T](j:T) = j.toString
