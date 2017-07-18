import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class Mux4Test(c: Mux4) extends PeekPokeTester(c) {

  var sel = 2
  val (in0, in1, in2, in3) = (5, 7, 11, 15)

  for (i <- 0 to 3) {
    sel = i
    poke(c.io.sel, sel)
    poke(c.io.in0, in0)
    poke(c.io.in1, in1)
    poke(c.io.in2, in2)
    poke(c.io.in3, in3)
    step(1)
    System.out.println("Circuit: " + peek(c.io.out)
      + "  Expected: " + TestMux4.result(sel, in0, in1, in2, in3))
    System.out.println()
  }
}

// Scala Mux4 test
object TestMux4{
  def result(sel: Int, in0: Int, in1: Int, in2: Int, in3: Int): Int = {
    // scala switch statement
    val out = sel match{
      case 0 => in3
      case 1 => in2
      case 2 => in1
      case 3 => in0
    }
    out
  }
}

class Mux4Tester extends ChiselFlatSpec {
  behavior of "Mux4"
  backends foreach {backend =>
    it should s"do Mux4 $backend" in {
      Driver(() => new Mux4(4), backend)(c => new Mux4Test(c)) should be (true)
    }
  }
}