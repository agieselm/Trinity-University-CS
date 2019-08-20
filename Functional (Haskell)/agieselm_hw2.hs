--Austin Gieselman HW 2
--worked with Chris Ma., Chris Mo., Ty, Gerardo

--Problem Uno

concatenate :: [String] -> String
concatenate [] = ""
concatenate (x:xs) = x ++  concatenate xs

--Problem Dos

myMax :: Ord a => [a] -> a
myMax [] = error "Can't take the maximum of an empty list"
myMax [x] = x
myMax (x:xs) =
   let recSol = myMax xs
   in if x > recSol
      then x
      else recSol

myMin :: Ord a => [a] -> a
myMin [] = error "Can't take the minimum of an empty list"
myMin [x] = x
myMin (x:xs) =
   let recSol = myMin xs
   in if x < recSol
      then x
      else recSol

range :: Ord a => [a] -> (a,a)
range [] = error "There is no min of max in an empty list"
range [x] = (x, x)
range (x:xs) = 
        let recSol = range xs
            top = max x (snd(recSol))
            bottom = min x (snd(recSol))
        in (top, bottom)

--Problem Tres

sorted :: Ord a => [a] -> Bool
sorted [] = True
sorted [x] = True
sorted (x:y:xs) = 
   x <= y && sorted (y:xs)

--Problem quatro

deleteAt :: Int -> [a] -> [a]
deleteAt 1 [] = error "can't delete from an empty list"
deleteAt 1 (x:xs) = xs 
deleteAt n (x:xs) | n >= 0 = x : (deleteAt (n-1) xs)


dropEveryNth :: Int -> [a] -> [a]
dropEveryNth _ [] = []
dropEveryNth n lst = aux 1 lst 
    where 
        aux acc [] = []
        aux acc (x:xs) 
                | n == acc =  aux 1 xs
                | otherwise = x : aux (acc + 1) xs

--Problem Cinco

powerset :: [a] -> [[a]]
powerset [] = [[]]
powerset (x:xs) = 
        let recSol = powerset xs
        in [x:ys | ys <- recSol] ++ recSol


--Problem Seis

mergeList :: Ord a => [a] -> [a] -> [a]
mergeList fh [] = fh
mergeList [] sh = sh
mergeList (x: fh) (y: sh) = 
        if x < y 
        then x:(mergeList fh) (y: sh)
        else y:(mergeList sh) (x: fh) 
               
splitList [] = ([], [])
splitList [x] = ([x], [])
splitList xs = splitAt (length xs `div` 2) xs

mergesort :: Ord a => [a] -> [a]
mergesort [] = []
mergesort [x] = [x]
mergesort lst = mergeList (mergesort fh) (mergesort sh)
        where (fh, sh) = splitList lst

--Problem Siete aka el finale

count :: Eq a => a -> [a] -> Integer
count y [] = 0
count y (x:xs) =
   let recSol = count y xs
   in recSol + (if y == x then 1 else 0)

--myMaximum2 :: [Integer] -> Integer
--myMaximum2 [] = error "Can't take the maximum of an empty list"
--myMaximum2 [x] = x
--myMaximum2 (x:xs) =
--   let recSol = myMaximum xs
--   in if x > recSol
--     then x
--      else recSol

--mostCommonElement :: Eq a => [a] -> a
--mostCommonElement [] = Error "There are no elements in an empty list"
--mostCommonElement [x] = x
--mostCommonElement (x:xs)  lst = aux 0 lst
--    where
--        aux acc [] = []
--        aux acc (x:xs)
--                | x == acc =  aux 1 xs
--                | otherwise = x : aux (acc + 1) xs

mostCommonElement :: Eq a => [a] -> a
mostCommonElement [] = error "There are no elements in an empty list"
mostCommonElement [x] = x
mostCommonElement (x:xs) = 
        let recSol = mostCommonElement xs
        in if count x(xs) >= count recSol(xs) then x else recSol

