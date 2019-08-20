#	beg $s3, $s4, L1;
#	add $s0, $s1, $s2;
# L1:	sub $s0, $s0, $s3;

#	if(i != j) goto Else;
#	f = g + h
#	goto After;
#Else: f = g - h
#After;

#	bne $s3, $s4, Else
#	add ...
#	j After;
#Else:   add ...
#After:

Loop: 	add $t0, $s3, $s3	# $t0 <- i*2
	add $t0, $t0, $t0       # $t0 <- i*4
	add $t1, #s5, $t0	# $t1 <- addr of A[i]
	lw  $t1, 0($t1)         # $t1 <- A[i]
	add $s1, $s1, $t1       # g = g + A[i]
	add $s3, $s3, $s4	# i = i + j
	bne $s3, $s2, Loop      # if(i != h)
