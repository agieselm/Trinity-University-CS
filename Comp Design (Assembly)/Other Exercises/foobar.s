
#
# program to get an integer N from user and print 0..N
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
# in C
# for(int i = 0; i < N; i++) printf("%d\n", i);
#

# get input
#
        li      $v0, 5          # "read int" syscall
        syscall
        sw      $v0, ans        # store result
#
# $s0 for N, $s1 for i
#

	addi	$s0, $v0, 0
	addi	$s1, $zero, 0
Loop:	slt	$t0, $s1, $s0
	beq	$t0, $zero, End
	addi	$a0, $s1, 0	# Copy i to $a0
	li	$v0, 1		# "print int" syscall
	syscall			
	la	$a0, nl		# 'nl' means newLine
	li	$v0, 4		# "print string" syscall
	syscall
	addi	$s1, $s1, 1
	j	Loop
End:

#
# echo
#
        #la      $a0, echo
        #li      $v0, 4          # "print string" syscall
        #syscall
        #lw      $a0, ans
        #li      $v0, 1          # "print int" syscall
        #syscall
        #la      $a0, nl
        #li      $v0, 4          # "print string" syscall
        #syscall
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
