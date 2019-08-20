#
#"hello, world" program (prints "hello, world" using SPIM system calls)
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
# print
#
        la      $a0, hello
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
hello:  .asciiz "hello, world!\n"
