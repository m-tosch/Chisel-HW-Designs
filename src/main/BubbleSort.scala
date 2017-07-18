import Chisel._


object TestArray {
    val array = Array(27, 13, 2, 32, 8)
}


// sort a vector of UInt values
class BubbleSort extends Module {
  val io = IO(new Bundle {
 		val out = Vec(5, UInt(OUTPUT, 6))
  })
  // Vector with 4 UInt values of bit width 2
//  val vec1 = Vec(UInt(0), UInt(1), 2.U, 3.U){UInt(width = 2)}
//  val vec2 = Vec(Array(UInt(0), UInt(1), 2.U, 3.U)){UInt(width = 2)}
  
  // Vector with 5 UInt elements of bit width 6. 
  // All initialized with 0
//  val values = Vec(Seq.fill(5) { UInt(value = 0, width = 6) } )
  
  // Vector filled with test data from an Array
  val values = Vec(TestArray.array.map(UInt(_, width = 6)))
  
  // the vector has to be put into a register in order to retain it's updated state
  // with every clock cycle. This way, the vector can be sorted over multiple clock cycles
  val valuesReg = Reg(init = values)

  // sorting algorithm
  for (i <- 0 until valuesReg.length by 1) {
    for (j <- 0 until valuesReg.length - i - 1 by 1) {
      when(valuesReg(j) < valuesReg(j + 1)) {
        var tmp = valuesReg(j)
        valuesReg(j) := valuesReg(j + 1)
        valuesReg(j + 1) := tmp
      }
    }
  }
  
	io.out := valuesReg
}


