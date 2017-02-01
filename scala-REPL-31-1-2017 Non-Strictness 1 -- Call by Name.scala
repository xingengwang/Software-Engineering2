// the next function is strict (if we give it an argument that never returns (or causes an error), 
//the call with that argument also never returns (or causes an error)
def myIfElse0(pred:Boolean, consequent:Int, alternate:Int) : Int = if (pred) consequent else alternate
myIfElse0(1==1, 3,  3/0)
// we can create a "manual" version of call-by-name using 0-arity functions, which take nothing as an argument and perform the job
// the problem is that these are cumbersome
def myIfElse1(pred:Boolean, consequent:() => Int, alternate:() => Int) : Int = if (pred) consequent() else alternate()
myIfElse1(1==1,() => 3, () => 3/0)
// An easier and more succint way of accomplishing similar functionality
def myIfElse2(pred:Boolean, consequent: => Int, alternate: =>Int) : Int = if (pred) consequent else alternate
myIfElse2(1==1, 3, 3/0)
myIfElse2(1==0, 3, 3/0)

// import the nextDouble machine
import scala.util.Random._

// the below uses call-by-name
Array.fill(10)(nextDouble)
// if we were simply passing in a VALUE (instead of a "recipe" for a value), we would simply get a uniform array
// This suggests that the above is indeed passing in a recipe
Array.fill(10)(3.141592)
