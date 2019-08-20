if you've already solved this, don't do it again!

--sorted: take a list and return true if it is in sorted order. You may or may not need an auxiliary
----function.
sorted :: Ord a => [a] -> Bool
sorted = undefined
--
----mostlySorted: take a list and reutrn true if at most two elements are "out-of-order". in the
----sequence 2 3 7 4 5, there is only one element out of order (7).
mostlySorted :: Ord a => [a] -> Bool
mostlySorted = undefined
--
----thirdLargest: take a list and return the second largest element.

myMaximum :: [Integer] -> Integer
myMaximum [] = error "Can't take the maximum of an empty list"
myMaximum[x] = x
myMaximum (x:xs) =
   let recSol = myMaximum xs
   in if x > recSol
      then x
      else recSol

secondLargest :: [Integer] -> Integer
secondLargest [] = error "no value in an empty list"
secondLargest [x] = error "only one value in this list"
secondLargest 
--
----fib: return the nth fibonnaci number. Write it the naive way first.
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
--
----return the most common element
mostCommonElement :: Eq a => [a] -> a
mostCommonElement = undefined
--
