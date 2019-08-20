import io.StdIn._
import io.Source

val src=Source.fromFile("/users/mlewis/CSCI1320-F15/SanAntonioTemps.csv").getLines

src.next
src.next


case class TempRecord(day:Int,jd:Int, month:Int,state_id:String, 
   year:Int, prcp:Double, tave:Int,tmax:Int,tmin:Int)

val a2d:Array[Array[String]]=src.map(_.trim.split(",") ).toArray

val tempRecords=for (a<- a2d)yield{  
   TempRecord(a(0).toInt,a(1).toInt,a(2).toInt,a(3).toString,a(4).toInt,a(5).toDouble,a(6).toInt,a(7).toInt,a(8).toInt)
}


def bubbleSort (a:Array[TempRecord], gt:(TempRecord,TempRecord) => Boolean):Unit = {
   for(i <- 0 until a.length-1) {
      for(j <- 0 until a.length-1 -i){
         if(gt(a(j),a(j+1))) {
            val tmp = a(j)
            a(j) = a(j+1)
            a(j+1) = tmp
            }
      }
   }
}

println("Coldest 5 Days")

bubbleSort(tempRecords, _.tave > _.tave)

tempRecords.take(5).foreach(i => {
   print(i.day + " ")
   print(i.month + " ")
   print(i.year + " ")
   println(i.tave)
})

println("Hottest 5 Days")

bubbleSort(tempRecords, _.tave < _.tave)

tempRecords.take(5).foreach(i => {
   print(i.day + " ")
   print(i.month + " ")
   print(i.year + " ")
   println(i.tave)
})

