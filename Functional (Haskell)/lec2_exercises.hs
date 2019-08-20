-- 1) Write a function "divisors" that takes a number x and returns all the divisors of x. Use the mod
-- function. (7 `mod` 2). You may choose not to include 1 in the list for simplicity.

let nats = [1, 2..]
[x | x <- nats ,  nats `mod` x  == 0]

-- 2a) Write a function isPrime that checks if a nubmer is prime.
-- 2b) Define "primes", the infinite lsit of all primes. 
--
-- 3) A number is “perfect” if it is equal to the sum of all its divisors (counting 1 but not
-- counting itself).  
-- a) Write a function isPerfect that tests if a number is perfect. 6 is prefect.
-- b) Generate a list "perfects" of all perfect numbers. Take care with infinity!

-- 4) If we can have lists of anything, why not lists of lists? 
-- Write a function "flatten" that takes a nested list and flattens it into a single list.
-- let llst = [[7,2,3,5], [], [3,8]]
-- flatten lst
-- [7,2,3,5,3,8]
