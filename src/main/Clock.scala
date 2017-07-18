import Chisel._

class Clock extends Module {
  val io = IO(new Bundle {
    val a = UInt(INPUT, 2)
    val out = UInt(OUTPUT, 2)
  })
  
   // initialize fixed value to register
    val regTest = Reg(init = UInt(3, width = 2)) // value: 3, bit width: 2 
//  reg := UInt(3) // assign to register: this value on every clock
  
  
  /* next
   * (optional) update value on clock */
  // -- option1 --
  val reg = Reg(next = io.a)
  // -- option2 --
//  val reg = Reg(UInt())
//  reg := io.a
  
  /* init
   * (optional) initialization value on reset */
  // -- option1 --
//  val reg2 = Reg(init = io.a)
  // -- option2 --
//  val reg2 = Reg(UInt())
//  when(reset){ // using implicit global reset signal
//    reg := io.a
//  }
  
  io.out := reg
}
