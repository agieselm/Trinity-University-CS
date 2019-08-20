myLength [] = 0
myLength (x:xs) = 
   let recSol = myLength xs
   in 1 + recSol

numberNonZero [] = 0
numberNonZero (x:xs) = 
   let recSol = numberNonZero xs
   in if x == 0
      then recSol
      else recSol + 1

myMaximum :: [Integer] -> Integer
myMaximum [] = error "Can't take the maximum of an empty list"
myMaximum[x] = x
myMaximum (x:xs) = 
   let recSol = myMaximum xs
   in if x > recSol
      then x
      else recSol

multList :: [Integer] -> Integer
multList [] = 1
multList (x:xs) = 
   let recSol = multList xs
   in x * recSol
      
multPos :: [Integer] -> Integer
multPos = multPos [] = 1
multPos (x:xs) = 
   let recSol = multPos xs
   in  if x > 0 then x * recSol
     else recSol

multPosLC :: [Integer] -> Integer
multPosLC = undefined

myElem:: Eq a => a -> [a] -> Bool
myElem y [] = False
myELem y (x:xs) = 
   let recSol = myElem y xs
   in if y == x true else recSol
   --( y== x) || recSol

count :: Eq a => a -> [a] -> Integer
count y [] = 0
count y (x:xs) = 
   let recSol = count y xs
   in recSol + (if y == x then 1 else 0)

lastElement [] = error "No last element of an empty list."
lastElement (x: []) = x
lastElement (x:xs) =
   let recSol = lastElement xs
   in recSol

--mostCommonElement :: Eq a => [a] -> a
--mostCommonElement [] = error "No elements in an empty list"
--mostCommonElement [x] = x
--mostCommonElement (x:xs) = 
--   let recSol = mostCommonElement xs
--   in

count :: Eq a => a -> [a] -> Integer
count y [] = 0
count y (x:xs) =
   let recSol = count y xs
   in recSol + (if y == x then 1 else 0)

occurancesOfHead :: Eq a => [a] -> Integer
occurancesOfHead [] = error "No head in an empty list"
occurancesOfHead (x) = 1
occurancesOfHead lst@(x:xs) = count x lst

--hoursToSchedule :: [(Integer, Integer)] -> Integer
--hoursToSchedule [] = 0
--hoursToSchedule ((h,m):xs) = 
--hoursToSchedule tasks = 
--   let recSol = hoursToSchedule xs
--     let aux []
--   in recSol + h + (if m > 0 then 1 else 0)
--sumTimes [] = (0, 0) 
--sumTimes ((h,m):xs) = 
--   let (h_sum, m_sum) = sumTimes xs
--      m_total = m_sum + m
--      m_remainder = m_total `mod` 60
--      h_total = h_sum +  h + (m_total `div` 60)
--   in (h_total, m_total)
--
--
countPositive:: [Integer] -> Integer
countPositive [] = 0
countPositive (x:xs)
   | x > 0 = 1 + countPositive xs
   | otherwise = countPositive xs

countPositive2 :: [Integer] -> Integer
countPositive2 lst = aux 0 lst
   where aux acc [] = acc
         aux acc (x:xs)
	    | x > 0 = aux (1+acc) xs
	    | otherwise = aux acc xs

myReplicate :: Integer -> a -> [a]
myReplicate 0 x = []
myReplicate n x = 
   let recSol = myReplicate (n-1) x
   in x:recSol
