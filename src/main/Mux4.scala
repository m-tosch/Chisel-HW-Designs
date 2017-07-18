import Chisel.Bool
import Chisel.Bundle
import Chisel.INPUT
import Chisel.Module
import Chisel.OUTPUT
import Chisel.UInt
import Chisel.defaultCompileOptions
import Chisel.when

/** Mux4, variable bit width
 * out signal is determined by selection-bits
 *   00 -> io.out = in3
 *   01 -> io.out = in2
 *   10 -> io.out = in1
 *   11 -> io.out = in0 */

class Mux4(w: Int) extends Module {
  val io = IO(new Bundle {
	  val sel = UInt(INPUT, 2)
    val in0 = UInt(INPUT, w)
 		val in1 = UInt(INPUT, w)
 		val in2 = UInt(INPUT, w)
 		val in3 = UInt(INPUT, w)
    val out = UInt(OUTPUT, w)
  })
  
  io.out := Mux2(io.sel(1),
    Mux2(io.sel(0), io.in0, io.in1),
      Mux2(io.sel(0), io.in2, io.in3))
}


/** Mux2, variable bit width
 * out = sel ? in0 : in1 */
class Mux2(w: Int) extends Module {
  val io = IO(new Bundle {
	  val sel = Bool(INPUT)
    val in0 = UInt(INPUT, w)
    val in1 = UInt(INPUT, w)
    val out = UInt(OUTPUT, w)
  })
  
  when(io.sel) {
    io.out := io.in0
  }.otherwise {
    io.out := io.in1
  }
}


/** object that uses the Mux2 class to decide on one of two possible outputs
 * defining the apply method with a return value allows us to
 * use this object as a value in an expression */
object Mux2 {
  def apply(sel: Bool, in0: UInt, in1: UInt) = {
    val m = Module(new Mux2(in0.getWidth)) 
    m.io.sel := sel
    m.io.in0 := in0
    m.io.in1 := in1
    m.io.out
  }
}