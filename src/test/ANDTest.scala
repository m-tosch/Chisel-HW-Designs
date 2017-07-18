import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ANDTest(c: AND) extends PeekPokeTester(c) {
  
  def and(a: Boolean, b: Boolean) = a & b
  
	var (a, b) = (false, true)
	poke(c.io.a, a)
	poke(c.io.b, b)
	step(1)
	System.out.println("Peek at out: " + peek(c.io.out)) // print output value
	System.out.println("My Result: " + and(a,b))
	expect(c.io.out, and(a, b))
}


class ANDTester extends ChiselFlatSpec {
  behavior of "AND"
  backends foreach {backend =>
    it should s"do AND $backend" in {
      Driver(() => new AND, backend)(c => new ANDTest(c)) should be (true)
    }
  }
}
