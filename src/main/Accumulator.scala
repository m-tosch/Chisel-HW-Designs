import Chisel._

// inWidth = bit width of the input
// outWidth = bit width of the output
class Accumulator(val inWidth: Int, val outWidth: Int) extends Module {
  val io = IO(new Bundle {
  	val in = UInt(INPUT, inWidth)
    val out = UInt(OUTPUT, outWidth)
  })
  
  // Register. Retain state until updated
  val acc = Reg(init = UInt(0, outWidth))
  // accumulate
  acc := acc + io.in
  // assign output
  io.out := acc
}
