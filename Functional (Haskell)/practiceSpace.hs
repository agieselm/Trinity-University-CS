isZero :: Integer -> Bool
isZero 0 = True
isZero _ = False

lucky :: Integer -> String
lucky 3 = "Lucky."
lucky 7 = "So very lucky."
lucky 21 = "The luckiest."
lucky x = "The number " ++ (show x) ++ " is not very lucky at all."

factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n-1)

x = 5
y = 5
z = (fromIntegral x) / (fromIntegral y)

addVectors :: (Double, Double) -> (Double, Double) -> (Double, Double)
addVectors a b = (fst a + fst b, snd a + snd b)
addVectors (x1, y1) (x2, y2) = (x1+x2, y1+y2)

len :: [a] -> Integer
len [] = 0
len (x:[]) = 1
len (x:y:[]) = 2
len (x:xs) = 1 + len xs

myEven x | (x `mod` 2 == 0) = True
	 | otherwise 	    = False

mileage miles gallons
   | mpg <= 10.0 = "Get a New Car"
   | mpg <= 20.0 = "You're doing okay."
   | mpg <= 40.0 = "You must drive a prius."
   | otherwise = "You're a hipster, aren't you?"
   where mpg = miles/gallons


lexCmp (x:xs) (y:ys)
   | x < y = "The First!" --can't use aString, it isn't in scope!
   | y < x = "The Second!"
   | otherwise = lexCmp xs ys
lexCmp (_:_) [] = "The Second!"
lexCmp [] (_:_) = "The First!"
lexCmp [] [] = "Equal!"
   where aString = "The First"

cylinder r h = 
    let sideArea =  2 * pi * r * h 
       topArea =  pi * r^2 
    in sideArea + 2 * topArea


tripleListStartsSeveny :: [(Integer, Integer, Integer)] -> Bool 
