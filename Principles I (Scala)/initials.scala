import io.StdIn._

def g(f1:String=>Int, f2:String=>Int):String=>String = {
  (s:String)=>(if(f1(s)==-1)"" else s.charAt(f1(s)).toString) + (if(f2(s)==-1)""else s.charAt(f2(s)).toString
}
