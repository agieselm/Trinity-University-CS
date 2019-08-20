addN n [] = []
addN n (x:xs) = (x + n):(addN n xs)

multN n [] = []
multN n (x:xs) = (x * n):(multN n xs)

--The above two functions are not abstracted enough, as they are very similar and can be 
--further reduced to make a higher-level abstracted function

myMap f [] = []
myMap f (x:xs) = (f x):(myMap f xs)

noTwos [] = []
noTwos (x:xs) = 
   if x == 2 
   then noTwos xs 
   else x : noTwos xs

noCapitals [] = []
noCapitals (x:xs) = 
   if x `elem` ['A'..'Z']
   then noCapitals xs
   else x : noCapitals xs

--same with the above two functions, we must abstract and use a higher order function

myFilter f [] = []
myFilter f (x:xs) = 
   if f x 
   then myFilter f xs
   else x: myFilter f xs

myGCF f [] = []
myGCF f (x:xs) = 
   if f `mod` x /= 0
   then myGCF f xs
   else x : myGCF f xs

myGCFilter f [] = []
myGCFilter f (x:xs) = 
   if  
