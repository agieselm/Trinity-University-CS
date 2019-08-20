# . . .

# $s0 a
# $s1 b
# $s2 c
# $s3 x

	addi $s0, $zero, 5 # a = 5
	addi $s1, $zero, 6 # b = 6
	addi $s2, $zero, 7 # c = 7

	add  $s0, $s0, $zero
	add  $a1, $s1, $zero
	add  $a2, $s2, $zero

	jal addproc

	add  $v0, $s3, $zero  #return value in x

# . . .
#save return address
addproc: 
	addi $sp, $sp, -4
	sw   $ra, 0($sp)
#save other registers 
	addi $sp, $sp, -4
	sw   $s0, 0($sp)
	addi $sp, $sp, -4
	sw   $s1, 0($sp)
# . . . s2 through s7

	add  $v0, $a0, $a1  # a + b
	add  $v0, $v0, $a2  # a + b + c (into register for return)
	
# "unwind the stack" (restore value)
# s2 through s7 . . .

	lw   $s1, 0($sp)
	addi $sp, $sp, 4
	lw   $ra, 0($sp)
	addi $sp, $sp, 4
	jr   $ra
