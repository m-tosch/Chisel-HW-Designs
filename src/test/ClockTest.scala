import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ClockTest(c: Clock) extends PeekPokeTester(c) {
  
  var number = 0
  
  for(i <- 1 to 3) {
      number = i
      poke(c.io.a, number)
      System.out.println("Number: "+number)
      
      System.out.println("--- Before step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      System.out.println("COMPARE: "+expect(c.io.out, number))
      
      step(1) // triggers Reg(next = ..)
//    reset(1) // triggers Reg(init = ..)
      
      System.out.println("--- After step ---")
      System.out.println("peek at out: "+peek(c.io.out))
      System.out.println("COMPARE: "+expect(c.io.out, number))
      System.out.println()
  }
}

class ClockTester extends ChiselFlatSpec {
  behavior of "Clock"
  backends foreach {backend =>
    it should s"test the clock $backend" in {
      Driver(() => new Clock, backend)(c => new ClockTest(c)) should be (true)
    }
  }
}
