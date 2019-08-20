# Pledged, Austin Gieselman
# register usage:
#
# $s2 holds &D
# $s3 holds i
# $s4 holds j
# $s5 holds a from User
# $s6 holds b from User
#
        .text
        .globl main
main:
        la      $s2, arr
#
# opening linkage (save return address)
#
        addi    $sp, $sp, -4
        sw      $ra, 0($sp)

#
# prompt1: "Enter number for variable A"
#
        la      $a0, prompt1
        li      $v0, 4
        syscall
#
# get input1
#
        li      $v0, 5
        syscall
        addi    $s5, $v0, 0
#
# echo1
#
        la      $a0, echo1
        li      $v0, 4          # "print string" syscall
        syscall

        addi    $a0, $s5, 0
        li      $v0, 1          # "print int" syscall
        syscall

        la      $a0, nl
        li      $v0, 4          # "print string" syscall
        syscall
#
# prompt2: "Enter number for variable B"
#
        la      $a0, prompt2
        li      $v0, 4
        syscall
#
# get input2
#
        li      $v0, 5
        syscall
        addi    $s6, $v0, 0

#
# echo2
#
        la      $a0, echo2
        li      $v0, 4          # "print string" syscall
        syscall
        addi    $a0, $s6, 0
        li      $v0, 1          # "print int" syscall
        syscall
        la      $a0, nl
        li      $v0, 4          # "print string" syscall
        syscall
#
# multiplication: multiplies B by 4
#
        la      $a0, prompt3
        li      $v0, 4
        syscall                 # "print prompt 3" syscall

        sll     $s7, $s6, 2
        li      $v0, 1
        add     $a0, $zero, $s7
        syscall                 # "print multiplied int" syscall

        la      $a0, nl
        li      $v0, 4
        syscall                 # "Make it pretty" syscall


#
# Problem 2.27
#
        addi    $s3, $zero, 0         # i <- 0
loop1:  slt     $t2, $s3, $s5         # $t2 <- (i < a)
        beq     $t2, $zero, end1      # if !(i < a) goto end1
        addi    $s4, $zero, 0         # j <- 0
loop2:  slt     $t2, $s4, $s6         # $t2 <- (j < b)
        beq     $t2, $zero, end2      # if !(j < b) goto end2
        sll     $t2, $s4, 2           # $t2 <- offset into D for D[4*j]
        add     $t2, $t2, $s2         # $t2 <- &D[4*j]
        add     $t1, $s3, $s4         # $s4 <- i + j
        sw      $t1, 0($t2)           # D[4*j] = i + j
        add     $s4, $s4, 1           # j++
        j       loop2
end2:   add     $s3, $s3, 1           # i++
        j       loop1
end1:
#
# print out values in array D
#
        addi    $s0, $zero, 0   # k <- 0
loop3:  slt     $t0, $s0, $s7   # $t1 <- (k < 4 * b)
        beq     $t0, $zero,end3 # if !(k < 4 * b) goto end3

        sll     $t0, $s0, 2
        add     $t0, $t0, $s2

        lw      $a0, ($t0)
        li      $v0, 1
        syscall

        addi     $s0, $s0, 1     # k++
        j       loop3
end3:
        la      $a0, nl
        li      $v0, 4
        syscall                 # "Make it pretty" syscall

#
# closing linkage (get return address and restore stack pointer)
#
        lw      $ra, 0($sp)
        addi    $sp, $sp, 4
        jr      $ra
         .end   main
#
# area for variables and constants
#
         .data
prompt1: .asciiz "Enter an integer for variable A:\n"
prompt2: .asciiz "Enter a second integer for variable B:\n"
prompt3: .asciiz "The product of B and four is:\n"
echo1:   .asciiz "For A, you entered:\n"
echo2:   .asciiz "For B, you entered:\n"
nl:      .asciiz "\n"
arr:     .word 0
