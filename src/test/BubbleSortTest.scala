// See LICENSE for license details.


import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class BubbleSortTest(c: BubbleSort) extends PeekPokeTester(c) {
  
  // multiple clock cycles are needed for the Chisel code to finish the sorting!
  // In this case: 3 cycles
  for(i <- 0 to 2) {
      System.out.println("--- Before step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      step(1)
      System.out.println("--- After step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      System.out.println()
  }
  
}


class BubbleSortTester extends ChiselFlatSpec {
  behavior of "BubbleSort"
  backends foreach {backend =>
    it should s"sort stuff $backend" in {
      Driver(() => new BubbleSort, backend)(c => new BubbleSortTest(c)) should be (true)
    }
  }
}
