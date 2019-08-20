--Austin Gieselman

--1. Define prodList :: [Integer] -> Integer that finds the product of all elements.
prodList :: [Integer] -> Integer
prodList lst = foldr (*) 1 lst
--
----2. Define lengthsOfStrings :: [String] -> [Int] that finds the lengths of each string.
lengthsOfStrings :: [String] -> [Int]
lengthsOfStrings lst = map (length) lst
--
----3. Using a fold, rewrite myReverse :: [a] -> [a] 
myReverse :: [a] -> [a]
myReverse lst = foldl (reverse) lst
--
----4. Define sumPositive :: [Integer] -> Integer using:
----a: Filter and a fold 
sumPositive1 :: [Integer] -> Integer
sumPositive1 = undefined
----b: Just a fold.
sumPositive2 :: [Integer] -> Integer
sumPositive2 = undefined
----5. Using a fold, define positives :: [Integer] -> [Integer] that makes a list of only the positive elements of a list.
positives :: [Integer] -> [Integer]
positives = undefined
--
----6. Define foldyFilter :: (a -> Bool) -> [a] -> [a] , a version of filter that uses fold instead of recursion.
foldyFilter :: (a -> Bool) -> [a] -> [a]
foldyFilter = undefined
--
----7. Bonus: Define the function harmonic :: Integer -> Double that takes a number n and returns the nth harmonic number.
----Remember (fromIntegral x)
harmonic :: Integer -> Double
harmonic = undefined
--
