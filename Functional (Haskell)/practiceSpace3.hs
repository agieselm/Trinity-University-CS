quicksort:: Ord a => [a] -> [a]
quicksort [] = []
quicksort (x:xs) = 
   let smaller = [y | y <- xs, y <= x]
       larger = [y | y <- xs, y > x]
   in (quicksort smaller) ++ [x] ++ (quicksort larger)

hanoi :: Integer -> Char -> Char -> Char -> [(Char, Char)]
hanoi 0 s p d = []
hanoi n s p d = 
   let step1 = hanoi (n-1) s d p
       step2 = (s, d)
       step3 = hanoi (n-1) p s d 
   in step1 ++ [step2] ++ step3 


isUpperCase :: Char -> Bool 
isUpperCase = (`elem` ['A'..'Z'])

applyTwice :: (a -> a) -> a -> a
applyTwice f x = f(f x)

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith f [] _  = []
myZipWith f  _ [] = []
myZipWith f (x:xs) (y:ys) = (f x y) : (myZipWith f xs ys)

applyAll :: [(a -> a)] -> a -> a
applyAll () _ = _
applyAll (f x y) = f(x) f(y)  


--applyAll [(+3), (*2)] 0 = 6
