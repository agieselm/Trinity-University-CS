import java.io.PrintWriter
import io.Source

def mapFile(inFile:String,outFile:String,trans:Char=>Char) {
  val pw=new PrintWriter(outFile)
  val in=Source.fromFile(inFile)
  for(c <- in) {
    pw.print(if(c>='a' && c<='z' || c>='A' && c<='Z') trans(c) else c)
  }
  in.close
  pw.close
}
val offset=args(2).toInt
mapFile(args(0),args(1),c=> {
  if(c.isLower) ('a'+(c-'a'+offset+26)%26).toChar
  else ('A'+(c-'A'+offset+26)%26).toChar
})

