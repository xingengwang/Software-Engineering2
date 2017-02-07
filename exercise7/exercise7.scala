/** Xingeng Wang
 *  xiw031
 *  11144515
 */
 
def StreamFn(a:Stream[Any], b:Stream[Any]):Stream[Any] = a.head #:: b.head #:: StreamFn(a.tail,b.tail)
val output = StreamFn(A,B).take(20).force
print(output)