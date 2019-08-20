def fact(n:Long):Long = if(n==1)1 else n*fact(n-1)

val a = Array.tabulate (20)(i=>fact(i+1))
println(a.mkString(", "))

def isOne(n:Long):Boolean = n.toString.charAt(0)=='1'

println(a.count(isOne))

println(a.filter(isOne).length)

println(a.map(i=>if(isOne(i))1 else 0).sum)


def rnd10(x:Long) = math.round(x/10.0)*10

println(a.map(rnd10).mkString(","))

Array.tabulate(20)(i=>rnd10(a(i)))
