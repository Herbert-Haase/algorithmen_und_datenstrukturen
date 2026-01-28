package hash

import scala.reflect.ClassTag

@main def main =
  val hashTable = DoubleHashingTable[Int](a => a)
  hashTable ++= Seq(81, 24, 63)
  hashTable ++= Vector(10, 50, 2, 1)
  hashTable ++= Array(22, 13, 74)
  println(hashTable search 11)
  println(hashTable -= 10)
  println(hashTable)
  val hashTable2 = DoubleHashingTable[Int](a => a)
  hashTable2 ++= Seq(1,21, 41)
  println(hashTable2)
  hashTable2 -= 1
  println(hashTable2)
  println(hashTable2.search(21))

trait HTable[T: ClassTag]:
  def += (value: T): Unit

  def ++=(values: Seq[T]): Unit =
    values.map(v => this += v)

  def search (value: Int): Option[T]

  def -= (value: T): Boolean