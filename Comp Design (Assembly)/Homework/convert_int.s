#Pledged, Austin Gieselman
.text
        .globl convert
convert:
#
# procedure to convert string to integer
#
# $a0 has address of string
# $v0 set to integer (if valid)
# $v1 set to 0 if valid, -1 if invalid, -2 if overflow
#
# YOUR CODE GOES HERE
#
# Handles the different cases
#
        li      $t1, '0'            # ascii value of 0
        li      $t2, 0              # used to determine whether or not a number will be negated
        li      $t3, '-'            # ascii value of -
        li      $s7, -1             # register to hold -1 to multiply for negation
#
# Set up accumulator, multiplier, and register to hold char
#
        li      $s3, 0               # initialize accumulator register
        addi    $t5, $zero, 10       # used to multiply accumulator by 10
        addi    $s4, $zero, 0        # used to set $v0
        lb      $t6, ($a0)           # initialize register that holds ascii value of input char
#
# Branch cases
#
        beq     $t6, $zero, error2   # if input is empty
        beq     $t6, $t3, hyphen     # if char is a hyphen (negative)

convLoop:
        beq     $t6, $t4, endLoop1   # null character exception
        blt     $t6, 48, error2      # branch on if char is less than ascii value of int 0
        bgt     $t6, 57, error2      # branch on if char is greater than ascii value of int 9
        mul     $s5, $s3, $t5        # multiply accumulator by 10
        sub     $t7, $t6, $t1        # subtract ascii value of input by ascii value of 0
        add     $s3, $s5, $t7        # add accumulator to difference of mult
        addi    $a0, $a0, 1          # move to next char
        lb      $t6, ($a0)           # load-in next char
        j       convLoop             # jump back to loop

hyphen:
        addi    $t2, -1              # set $t2 to a number less than 0
        addi    $a0, $a0, 1          # move to next char
        lb      $t6, ($a0)           # load-in next char
        j       convLoop             # jump back to loop

error2:
        li      $s4, -1              # set error to -1
        j       endLoop1             # end program

endLoop1:
        beq     $t2, -1, endLoop2    # if hyphen was encountered, move to alternate endLoop
        add     $v0, $zero, $s3      # add up work for $v0 register values
        add     $v1, $zero, $s4      # add up work for $v1 register values
        jr      $ra

endLoop2:                            # does all the same work as endLoop1 but multiplies by -1
        add     $v0, $zero, $s3
        mul     $v0, $s3, -1
        add     $v1, $zero, $s4
        jr      $ra

# IF YOU NEED VARIABLES YOU CAN PUT THEM HERE
        .data
#        .word 0
#
# YOU SHOULD NOT NEED TO CHANGE ANYTHING BELOW
#
#  test/demo of procedure to convert a string to an integer
#
        .text
        .globl  main
main:
#
# opening linkage (save return address)
#
        addi    $sp, $sp, -4
        sw      $ra, 0($sp)
#
# prompt for and get user input (string)
#
        la      $a0, prompt
        li      $v0, 4              # print string
        syscall
        la      $a0, input
        la      $a1, 1000
        li      $v0, 8              # read string
        syscall
#
# remove trailing '\n'
# register usage:
# $s0 points to character in input
# $s1 has '\n'
        addi    $s0, $a0, 0
        li      $s1, '\n'
loop1:
        lb      $t0, 0($s0)
        beq     $t0, $s1, loop1end
        addi    $s0, $s0, 1
        j       loop1
loop1end:
        sb      $zero, 0($s0)
#
# call convert and print results
#
        la      $a0, input
        jal     convert
# register usage:
# $s1 saves $v0 (output result)
# $s2 saves $v1 (error result)
        addi    $s1, $v0, 0
        addi    $s2, $v1, 0
# print header info
        la      $a0, header_echo
        li      $v0, 4              # print string
        syscall
        la      $a0, input
        li      $v0, 4              # print string
        syscall
        la      $a0, nl
        li      $v0, 4              # print string
        syscall
# print result
        bne     $s2, $zero, error
        la      $a0, header_result
        li      $v0, 4              # print string
        syscall
        addi    $a0, $s1, 0
        li      $v0, 1              # print integer
        syscall
        la      $a0, nl
        li      $v0, 4              # print string
        syscall
        j       end
error:
        la      $a0, header_error
        li      $v0, 4              # print string
        syscall
        addi    $a0, $s2, 0
        li      $v0, 1              # print integer
        syscall
        la      $a0, nl
        li      $v0, 4              # print string
        syscall
end:
#
# closing linkage (get return address and restore stack pointer)
#
        lw      $ra, 0($sp)
        addi    $sp, $sp, 4
        jr      $ra
        .end    main
#
# area for variables and constants
#
        .data
prompt: .asciiz "Enter a line of text:\n"
header_echo: .asciiz "Input "
header_result: .asciiz "Result "
header_error: .asciiz "Error "
nl:     .asciiz "\n"

input:  .space  1000
