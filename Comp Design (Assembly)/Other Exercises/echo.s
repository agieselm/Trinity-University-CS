#
# program to get a line of text from user and echo, using SPIM system calls
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
        la      $a0, ans
        li      $a1, 100
        li      $v0, 8          # "read string" syscall
        syscall
#
# echo
#
        la      $a0, echo
        li      $v0, 4          # "print string" syscall
        syscall
        la      $a0, ans
        li      $v0, 4
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
prompt: .asciiz "Enter a line of text:\n"
echo:   .asciiz "You entered:\n"
ans:    .space  100	
