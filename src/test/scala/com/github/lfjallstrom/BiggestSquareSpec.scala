package com.github.lfjallstrom

import org.scalatest.{FlatSpec, Matchers}

class BiggestSquareSpec extends FlatSpec with Matchers {

  "Biggest Square" should "be found from empty city" in {
    BiggestSquare.resolve(Nil) should be (0)
  }

  it should "be found from flat city" in {
    BiggestSquare.resolve(0 :: Nil) should be (0)
  }

  it should "be found from a city that has one tower" in {
    BiggestSquare.resolve(1 :: Nil) should be (1)
    BiggestSquare.resolve(2 :: Nil) should be (1)
  }

  it should "be found from square city" in {
    BiggestSquare.resolve(3 :: 3 :: 3 :: Nil) should be (3)
  }

  it should "be found from rectangular city" in {
    BiggestSquare.resolve(4 :: 4 :: 4 :: Nil) should be (3)
  }

  it should "be found from example city 1" in {
    BiggestSquare.resolve(1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: Nil) should be (5)
  }

  it should "be found from example city 2" in {
    BiggestSquare.resolve(2 :: 8 :: 3 :: 7 :: 4 :: 5 :: 8 :: 2 :: 9 :: Nil) should be (4)
  }

  it should "be found from example city 1 in reversed order" in {
    BiggestSquare.resolve(9 :: 8 :: 7 :: 6 :: 5 :: 4 :: 3 :: 2 :: 1 :: Nil) should be (5)
  }

  it should "be found from example city 2 in reversed order" in {
    BiggestSquare.resolve(9 :: 2 :: 8 :: 5 :: 4 :: 7 :: 3 :: 8 :: 2 :: Nil) should be (4)
  }

  it should "be found from the data file" in {
    BiggestSquare.resolve("input.txt") should be (201)
  }
}
