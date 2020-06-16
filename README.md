# Chisel HW Designs

The Chisel Designs in this repository were created while learning and evaluating the Hardware description language Chisel,
more specifically Chisel3. They helped to build a general understanding of the circuit design
with Chisel.

## General Information
Designs can be found in /src/main

The corresponding Testbenches can be found in /src/test

The Testbenches are a mix of Scala and Chisel. Their purpose is the validation of a Chisel Design (module)
Therefore, the Testbench can set input signals (poke), control the clock (step & reset) and retrieve output
signals (peek)

<p align="center">
	<img src="doc/img/ScalaTestbench.png" width="500">
</p>

