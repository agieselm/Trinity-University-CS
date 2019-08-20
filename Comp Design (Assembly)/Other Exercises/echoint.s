#
# program to get an integer from user and echo, using SPIM system calls
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
        li      $v0, 4          # "print string" syscall
        syscall
#
# get input
#
        li      $v0, 5          # "read int" syscall
        syscall
        sw      $v0, ans        # store result
#
# echo
#
        la      $a0, echo
        li      $v0, 4          # "print string" syscall
        syscall
        lw      $a0, ans
        li      $v0, 1          # "print int" syscall
        syscall
        la      $a0, nl
        li      $v0, 4          # "print string" syscall
        syscall
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
prompt: .asciiz "Enter an integer:\n"
echo:   .asciiz "You entered:\n"
nl:     .asciiz "\n"
ans:    .word   0
