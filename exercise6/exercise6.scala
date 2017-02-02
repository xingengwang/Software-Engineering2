/** Xingeng Wang
 *  xiw031
 *  11144515
 */
 
import scala.math._
val e = 0.001
def derivative(e:Double,fnUnary:(Double) => Double) = ((x0:Double) => ((fnUnary(x0+e)-fnUnary(x0))/e))

val t1 = derivative(e,(x:Double) => 1.0)
val t2 = derivative(e,(x:Double) => x)
val t3 = derivative(e,(x:Double) => 2*x)
val t4 = derivative(e,(x:Double) => x*x)
val t5 = derivative(e,(x:Double) => exp(x))

val vet = (-1.0 to 1.0 by 0.25)

vet.map(t1(_))
vet.map(t2(_))
vet.map(t3(_))
vet.map(t4(_))
vet.map(t5(_))
