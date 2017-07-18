import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AccumulatorTest(c: Accumulator) extends PeekPokeTester(c) {
  
  // values that don't change
  val (iter, limit) = (5, 8)
  
  // variable that changes
  var sum = 0
  
  for(i <- 1 to iter) {
      var value = rnd.nextInt(limit) // 0 (inclusive) - limit (exclusive)
      sum += value
      poke(c.io.in, value)
      step(1)
      System.out.println("Value: "+value)
      System.out.println("Peek at out: "+peek(c.io.out))
      System.out.println("My Result: "+sum)
      System.out.println()
  }
  expect(c.io.out, sum)
}

// Worst case sum: (limit - 1) * iter              |               e.g. (8 - 1) * 5 = 35
// Worst case bit width: nextPowerOfTwo(sum)       |               e.g. nextPowerOfTwo(35) = 6
//
// A bit width of 1 allows numbers in the range of [0, ... , (2^1)-1 = 1]
// A bit width of 2 allows numbers in the range of [0, ... , (2^2)-1 = 3]
// A bit width of 3 allows numbers in the range of [0, ... , (2^3)-1 = 7]
// A bit width of n allows numbers in the range of [0, ... , (2^n)-1]
class AccumulatorTester extends ChiselFlatSpec {
  behavior of "Accumulator"
  backends foreach {backend =>
    it should s"accumulate $backend" in {
      // 3 = bit width needed for value (range [0,...,7] is possible)
      // 6 = bit width needed for sum (range [0,...,35] is possible)
      Driver(() => new Accumulator(3, 6), backend)(c => new AccumulatorTest(c)) should be (true)
    }
  }
}
