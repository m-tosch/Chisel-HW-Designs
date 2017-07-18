import Chisel.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class MemoryTest(c: Memory) extends PeekPokeTester(c) {
  
  // read
  def read(addr: Int, data: Int) = {
    poke(c.io.ren, true) // enable read
    poke(c.io.rdAddr, addr) // set address
    step(1) // advance one clock cycle to produce readable output
    val rdData = peek(c.io.rdData) // retrieve read data
    expect(c.io.rdData, rdData)
    System.out.println("Circuit: "+rdData+" Expected: "+data)
  }
  
  // write
  def write(addr: Int, data: Int)  = {
    poke(c.io.wen, 1) // enable write
    poke(c.io.wrAddr, addr) // set address
    poke(c.io.wrData, data) // set data to write
    // all input ports are set, advance one clock cycle
    step(1)
  }
  
  write(0, 1)
  read(0, 1)
  write(9, 11)
  read(9, 11)
}

class MemoryTester extends ChiselFlatSpec {
  behavior of "Memory"
  backends foreach {backend =>
    it should s"do memory test $backend" in {
      Driver(() => new Memory, backend)(c => new MemoryTest(c)) should be (true)
    }
  }
}
