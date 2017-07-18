import Chisel._

class Memory extends Module {
   val io = IO(new Bundle {
    val wen     = Bool(INPUT) // write enabled
    val wrAddr  = UInt(INPUT, width = 8) // write to this address
    val wrData  = UInt(INPUT, width = 8) // data to write
    val ren     = Bool(INPUT) // read enabled
    val rdAddr  = UInt(INPUT, width = 8) // read from this address
    val rdData  = UInt(OUTPUT, width = 8) // read data is stored in this
  })

  // Random Access Memories via Mem()-construct
  // Space for 256, 8-bit UInt values
  val mem = Mem(256, UInt(width = 8))

  /* When wen is asserted, write wrData to mem at wrAddr 
   * When ren is asserted, rdData holds the output out of
   * reading the mem at rdAddr
   * do NOT read and write on the same clock edge! */

  // write 
  when(io.wen) {
    mem(io.wrAddr) := io.wrData
  }

  // read
  io.rdData := 0.U
  when(io.ren) {
    io.rdData := mem(io.rdAddr)
  }

}