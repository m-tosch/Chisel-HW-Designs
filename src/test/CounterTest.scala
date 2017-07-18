
import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class CounterTest(c: Counter) extends PeekPokeTester(c) {
  
  // count from 0 to 5 on every clock cycle, then wrap back to 0
  var number = 5
  
  // only poke once! Not inside the loop
  poke(c.io.in, number)
  
  for(i <- 1 to 7) {
      System.out.println("--- Before step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      step(1)
      System.out.println("--- After step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      System.out.println()
  }
}

class CounterTester extends ChiselFlatSpec {
  behavior of "Counter"
  backends foreach {backend =>
    it should s"count something $backend" in {
      Driver(() => new Counter, backend)(c => new CounterTest(c)) should be (true)
    }
  }
}
