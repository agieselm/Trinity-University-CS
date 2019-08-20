lambdaGCF :: Integer
lambdaGCF = head $ filter (\n -> (n `mod` 3829) == 0) [100000, 99999..]

myEven :: Integer -> Bool
myEven x = x `mod` 2 == 0

myLambdaEven = (\ x -> x `mod` 2 == 0)

fold :: (a -> b -> b) -> b -> [a] -> b
fold f b [] = b
fold f b (x:xs) = f x (fold f b xs) -- x `f` (fold f b xs)

concatenate lst = fold (++) [] lst

myElem1 x [] = False
myElem1 x (y:ys) = if x == y then True else myElem1 x ys

myElem2 x lst = fold (\y rs -> if x == y then True else rs) False lst 
