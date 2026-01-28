package hash

import scala.reflect.ClassTag

class DoubleHashingTable[T: ClassTag](val hashFunc: T => Int) extends HTable:
  private val arr: Array[TableElement] = Array.fill(20)(TableElement(None, false))

  override def +=(value: T): Unit =
    for i <- 0 until arr.length / 2 do
      val hash = probe(hashFunc(value), i)
      if arr(hash).value.isEmpty then
        arr(hash) = TableElement(Some(value), false)
        return
      arr(hash).hasDouble = true
    throw OutOfMemoryError("Not enough memory for HashTable")

  private def probe(key: Int, n: Int) = (key + n) % (arr.length - 2 * n)

  override def search(value: Int): Option[T] =
    for i <- 0 until arr.length / 2 do
      val hash = arr(probe(value, i))
      if hash.value.isEmpty then
        if !hash.hasDouble then return None
      else if hashFunc(hash.value.get) == value then
        return hash.value
      else if !hash.hasDouble then return None
    None

  override def -=(value: T): Boolean =
    var lastHash: Option[TableElement] = None
    for i <- 0 until arr.length / 2 do
      val hash = arr(probe(hashFunc(value), i))
      if hash.value.isEmpty then
        if !hash.hasDouble then return false
      if hashFunc(hash.value.get) == value then
        hash.value = None
        //if lastHash.nonEmpty && !hash.hasDouble then lastHash.get.hasDouble = false
        return true
      else if !hash.hasDouble then return false
      lastHash = Some(hash)
    false

  override def toString: String =
    arr.mkString(", ")

  private class TableElement(var value: Option[T], var hasDouble: Boolean):
    override def toString: String =
      f"{$value, $hasDouble}"