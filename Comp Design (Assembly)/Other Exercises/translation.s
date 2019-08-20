# use $s0 for a, $s1 for b
# if(a < b)
	slt	$t0, $s0, $s1 	  # at 0x8000
	beq	$t0, $zero, Else  # at 0x8004
#	a = a + t;
	addi 	$s0, $s0, 1 	  # at 0x8008
	j	End		  # at 0x800c
# else
Else:
# 	b = b - 1;
	addi 	$s1, $s1, -1      # at 0x8010
End:				  # at 0x8014
