--CLASS NOTES
--
--lecture 1/23

Calculating Program execution time (CPU only)

  CPU execution time for program x -----> CPU cycles x clock cycle time
  This can be expanded to get      -----> instruction count x cycles per instruction x clock cycle

  e.g. time = (#instructions X CPI)/ clock rate

Sidebar: Dimensional analysis

  approach "word problems" in terms of units, treating them almost like factors in mult & division.
  If the formula you propose to use produces the right units there's a good chance its the right one

Calculating execution time, cont. 

  One factor in the basic formula is cycles per instruction. What if that isn't the same for all instructions?

Parallelism (Hardware)
  
  parallelism might be "doing more than one thing at a time"... It's been used in processors for a very long time via pipelining, and in high-performance processors via vector   processing
  Parallelism as the key to go past the limit of single-processor performance - executing multiple instruction streams at the same time.
  So.. use those transistors to put multiple cores on a chip
  
Parallelism (Hard & Software)
 
  multicore computers offer on eking of potential parallelism --"Multithreading"
  networks of computers offer another -- "Message passing".
  sufficiently advanced graphics processors offer yet another limited form of multithreading
  exploiting any of these traditionally requires significant programmer effort... Hiding the details in the libraries

Parallelism Performance

  multithreading can be used to make the code simpler for the programmer
  it can also be used to imporove performance. 
  here, "speedup" is defined as a function of the number of processing elements, where the speedup for P processing elements is the ration of execution time 1 PE to execution    time using P PEs.
       |                        execution time(seq)
       - - - - > "speedup(P)" = -------------------
                                o. time on P processors

Performance --Amdahl's Law

  --IF y is the "serial fraction", speedup on P PEs is (at best, i.e., ignoring overhead)
  --
  --                1
  --  S(P) = ________________
  --          y + ((1 - y)/P)
  --
  --and as P increase, this approaches 1/y - upper bound on speedup.

Preview --> "Architecture as Interface Definition"

  from a software perspective, "architecture" defines lowest-level building blocks 
  |
  ---> what operations are possible, what kinds of operands, binary data formats, etc.
  
  from a hardware perspective, "architecture" is a specification
  |
  ---> designers must build something that behaves the way the specification says

Architecture ---> Key Abstractions

  1) Memory: long list of binary numbers, encoding all data, each with address and contents. 
  2) instructions: primitive operations processor can perform.
  3) fetch/execute cycle: what the processor does to execute a program --> repeatedly get next instruction(from memory), increment program counter, execute instruction
  4) Registers: fast access work space for processor, typically divided into "special purpose"(e.g. program counter) "general purpose" (e.g. int and floating point)




1/30

--registers:

you can only do arithmetic on values in registers.

--arithmetic instructions review

add and sub take three operands, all register nums.
addi also takes three: two register numbers and a constant immediate value.

--load/store instructions review

load and store take two operands, one a register to load into/store from and one specifying address in terms of register containing base address and displacement(const).

-- I Format

meant for instructions such as lw.
fields:
    --op --op code, 6 bits
    --rs --first source operand, 5 bits
    --rt --destination operand, 5 bits
    --disp --displacement, 16 bits
 example: find binary representation of...

 lw  $t0, 12($t1)
 

-- R Format

means for instructions such as add.
fields:
    -op --op code, 6 bits
    -rs --first source operand, 5 bits
    -rt --second source operand, 5 bits
    -rd --destination operand, 5 bits
    -shamt --"Shift amount" (not used for add), 5 bits
    -funct --"function field", 6 bits


    
