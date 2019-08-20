-- Austin Gieselman
-- worked with: Christian Morales, Christian Martinez

-- Problem 1

collatzEsque x = if(x < 0) then x*(-2) else(x-10)*(-1)

--Problem 2

absHead (xs) = (head xs, head xs *(-1))

--Problem 3

firstTwo xs = take 2 xs

--Problem 4

evenUpTo n = [x | x <- [0..n], x `mod` 2 == 0]

--Problem 5

divisiblePairs (xs) = [ (x, y) | x <- [head xs.. last  xs],  y <- [x + 1.. last xs], y `mod` x == 0]

--Problem 6

sOfl len strs = [(a) | a <- strs, length a == len ]
stringsOfLengths lens strs = [(a, sOfl a strs) | a <- lens]

--Problem 7

divisors x = [n | n <- [1..x], x `mod` n == 0]
primes = [n | n <- [2.. ], divisors n == [1, n]]  
 
