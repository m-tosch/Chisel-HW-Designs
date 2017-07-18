import Chisel._

class AND extends Module {
  val io = IO(new Bundle {
    val a = Bool(INPUT)
  	val b = Bool(INPUT)
    val out = Bool(OUTPUT)
  })
  io.out := io.a & io.b
}