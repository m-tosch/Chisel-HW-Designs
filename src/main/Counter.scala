
import Chisel._


class Counter() extends Module {
  val io = IO(new Bundle {
    val in = UInt(INPUT, 4)
    val out = UInt(OUTPUT, 4)
  })
  
  io.out := Counter.counter(io.in)
}


object Counter{
	// count up on clock cycle until max is reached
	// then wrap back to 0
	def counter(max: UInt) = {
		val x = Reg(init = UInt(0, max.getWidth))
			x := Mux(x === max, UInt(0), x + UInt(1))
			x
	}
}

