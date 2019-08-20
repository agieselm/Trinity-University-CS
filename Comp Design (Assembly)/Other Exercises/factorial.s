#
#  program to get an integer N from user and print N!, computed using
#  recursive function
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
        addi    $s0, $v0, 0     # save result in $s0
# 
# compute factorial
#
# $s0 has N
# set up to call factorial function
	addi	$a0, $s0, 0
	jal	fact
# $v0 has result to print
	addi	$a0, $v0, 0
        li      $v0, 1          # "print int" syscall
        syscall
        la      $a0, nl		# newline string
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
# factorial function
#
fact:
# opening linkage (save return address and registers)
# we save all registers even though we aren't using them -- "good practice"
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
	addi	$s0, $a0, 0
# do computation
# in C:
# int fact(int N) {
#   if (N <= 0) return 1;
#   else return N * fact(N-1)
# }
# with goto's and single return:
#    int return_val;
#    if (0 < N) goto recursive_case
#      return_val = 1;
#      goto return_label
#   recursive_case:
#      int temp = fact(N-1);
#      return_val = N * temp;
#   return_label:
#       return return_val;
# translated into MIPS assembly:
#    if (0 < N) goto recursive_case
	slt	$t0, $zero, $s0
	bne	$t0, $zero, Recursive
#      return_val = 1;
	addi	$v0, $zero, 1
#      goto return_label
	j	End
#   recursive_case:
Recursive:
#      int temp = fact(N-1);
	addi	$a0, $s0, -1	# get N-1 into $a0
	jal	fact		# result is in $v0
#      return_val = N * temp;
	mul	$v0, $s0, $v0
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
#   restore return address
        lw      $ra, 0($sp)
        addi    $sp, $sp, 4
# return to caller
        jr      $ra
        .end    fact
#
# area for variables and constants
#
        .data
prompt: .asciiz "Enter an integer:\n"
nl:     .asciiz "\n"
