# Pledged: Austin Gieselman
#   program to get an integer n from user and print the Nth element of the
#   fibonacci sequence using recursive function
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
# prompt
#
        la      $a0, prompt
        li      $v0, 4              # "Print String" syscall
        syscall
#
# get input
#
        li      $v0, 5              # "Read Int" syscall
        syscall
        addi    $s0, $v0, 0         # Save result in $s0
#
# compute fibonacci
# setup to call fibonacci function
#

        la      $a0, prompt1        # "Print String" syscall
        li      $v0, 4
        syscall

        addi    $a0, $s0, 0         # Jump to fib label
        jal     fib

        addi    $a0, $v0, 0
        li      $v0, 1              # "Print Int" syscall
        syscall

        la      $a0, nl             # Newline syscall
        li      $v0, 4
        syscall
#
# closing linkage (get return address and restore stack pointer)
#
        lw      $ra, 0($sp)
        addi    $sp, $sp, 4
        jr      $ra
        .end main
#
# fibonacci function
#
fib:
# opening linkage
#   return address
        addi    $sp, $sp, -4
        sw      $ra, 0($sp)
#   $sN registers
        addi    $sp, $sp, -32
        sw      $s0, 0($sp)
        sw      $s1, 4($sp)
        sw      $s2, 8($sp)
        sw      $s3, 12($sp)
        sw      $s4, 16($sp)
        sw      $s5, 20($sp)
        sw      $s6, 24($sp)
        sw      $s7, 28($sp)
# get argument N into $s0
        addi     $s0, $a0, 0
# base cases go here
        addi    $t1, $zero, 1
        beq     $s0, $zero, return0 	#If argument is 0, branch to return0
        beq     $s0, $t1, return1	    #If argument is 1, branch to return1
# fib(N-1)
        addi    $a0, $s0, -1

        jal     fib                     # Recurse

        add     $s1, $zero, $v0
# fib(N-2)
        addi    $a0, $s0, -2

        jal     fib                     # Recurse

# fib(N-1) + fib(N-2)
        add     $v0, $s1, $v0
End:
# closing linkage
# "unwind the stack"
#   restore $sN registers
        lw      $s7, 28($sp)
        lw      $s6, 24($sp)
        lw      $s5, 20($sp)
        lw      $s4, 16($sp)
        lw      $s3, 12($sp)
        lw      $s2, 8($sp)
        lw      $s1, 4($sp)
        lw      $s0, 0($sp)
        addi    $sp, $sp, 32

#    restore return address
        lw      $ra, 0($sp)
        addi    $sp, $sp, 4

#    return to caller
        jr      $ra
        .end    fib

return0:
        li      $v0, 0
        j       End
return1:
        li      $v0, 1
        j       End
#
# area for variables and constants
#
        .data
prompt: .asciiz "Enter an integer value for N:\n"
prompt1:.asciiz "The Nth fibonacci number is:\n"
nl:     .asciiz "\n"
