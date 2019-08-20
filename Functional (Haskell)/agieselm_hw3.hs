--Austin Gieselman


--Problem Uno
myfoldr :: (a -> b -> b) -> b -> [a] -> b
myfoldr f b [] = b
myfoldr f b (x:xs) = f x (myfoldr f b xs)

--Problem dos
myfoldl :: (b -> a -> b) -> b -> [a] -> b
myfoldl f b [] = b
myfoldl f b (x:xs) = myfoldl f (f b x) xs

--fold warmup 1
concatenate :: [String] -> String
concatenate lst = foldr (++) [] lst

--Problem tres
sorted :: Ord a => [a] -> Bool
-------sorted xs = all(\(x, y) -> x <= y) $ zip xs (tail xs) --can't use this b/c uses recursion & not fold
--sorted lst = foldl(\x ys -> if x <= ys then True else ys) False lst
sorted = undefined

--Problem quatro
range :: Ord a => [a] -> (a,a)
range [] = error "nothing in list"
range [x] = (x, x)
range (x:xs) = 
        let top = foldr(max) x xs
            bottom = foldr(min) x xs
        in (bottom, top)

--Problem cinco
flatmap :: (t -> [a]) -> [t] -> [a]
flatmap f (x:xs) = f x ++ flatmap f xs

--Problem Seis
exists :: (t -> Bool) -> [t] -> Bool
exists  _ [] = False
exists p (x:xs)
           |p(x) = True
           |otherwise = exists p xs

--Problem seite
forall :: (t -> Bool) -> [t] -> Bool
forall _ [] = False
forall p (x:xs)
          |p x = exists p xs  
          |otherwise = False

--Problem ocho

myPartition :: (a -> Bool) -> [a] -> ([a], [a])
myPartition p [] = ([], [])
myPartition p xs = 
           let trueList = filter p xs
               falseList = filter (not . p) xs
           in (trueList, falseList)
          
--Problem nueve
incByN :: (Num a) => [a] -> a -> [a]
incByN (xs)f = map (+f)xs

--Problem diez
sumPairs :: (Num a) => [a] -> [a] -> [a] 
sumPairs (xs) (ys) = zipWith (+) xs ys
