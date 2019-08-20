--collectPos :: take a list and return only the positive elements
--Write this using direct recursion
collectPos :: [Integer] -> [Integer]
collectPos [] = []
collectPos [-1] = []
collectPos (x:xs) 
   | x > 0 = x: (collectPos xs)
   | otherwise = collectPos xs
--
----this time, use an auxilliary function with an accumulator. 
collectPos2 :: [Integer] -> [Integer]
collectPos2 lst = aux [] lst 
   where aux acc [] = acc
         aux acc (x:xs) 
            | x > 0 = aux (x:acc) xs
            | otherwise = aux acc xs
--
----sorted: take a list and return true if it is in sorted order. You may or may not need an auxiliary
----function with an accumulator.
sorted :: Ord a => [a] -> Bool
sorted [] = True
sorted (x:xs) = aux x xs 
   where aux prev [] = True
         aux prev (x:xs)
            | x > prev = aux x xs
            | otherwise = False
--
----mostlySorted: take a list and reutrn true if at most two elements are "out-of-order". in the
----sequence 2 3 7 4 5, there is only one element out of order (7).
mostlySorted :: Ord a => [a] -> Bool
mostlySorted = undefined
--
----secondLargest: take a list and return the second largest element.
secondLargest :: [Integer] -> Integer
secondLargest = undefined

--fib: return the nth fibonnaci number. Write it the simplest way you can first.
fib :: Integer -> Integer
fib = undefined
--
----now try fib 30. That's really slow! See if you can make it faster.
--
myZip :: [a] -> [b] -> [(a,b)]
myZip = undefined
----myZip [1,3,2] ['a', 'b', 'c'] = [(1,'a'), (3,'b'), (2,'c')]
--
----eliminate the duplicates from a sorted list
elimDups :: Eq a => [a] -> [a]
elimDups = undefined
--
----eliminate the duplicates from an unsorted list
elimDupsUnsorted :: Eq a => [a] -> [a]
elimDupsUnsorted = undefined

--run length encoding: turn "aabbbccd" into [('a',2), ('b',3), ('c', 2), ('d',1)]
rlEncode :: String -> [(Char, Integer)]
rlEncode = undefined

