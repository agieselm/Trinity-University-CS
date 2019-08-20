#
# "starter"  program -- might be useful in testing code fragments in (x)spim
#
        .text
        .globl  main
main:
#
# opening linkage (save return address)
#
        addi    $sp,$sp,-4 #sp is "stack pointer"
        sw      $ra,0($sp)
#
# put code to assign initial values to registers here
# examples:
        addi    $s0, $zero, 10         # sets 10 + 2 (hopefully)
        la      $s1, A                  # sets $s1 to address of A
	addi    $s2, $zero, 2
#
# put code to test here
# example:
	add     $s3, $s0, $s2
        sw      $s0, 0($s1)             # stores 10 in A[0]

#trying storing $s3 in A[i]
	addi $s4, $zero, 2              #use $s4 for i and set to 2
	add  $t0, $s4, $s4              #i * 2
	add  $t0, $t0, $t0              #i * 4
	add  $t0, $t0, $s1              #addr of A[i]
	sw   $s3, 0($t0)
#
# closing linkage (get return address and restore stack pointer)
#
        lw      $ra,0($sp)
        addi    $sp,$sp,4
        jr      $ra
        .end    main
#
# area for variables and constants
#
# example:
        .data
A:      .word   0, 0, 0, 0              # defines the equivalent of
                                        # "int A[4]"
