--"I don't care about any syntax, keep logic good"
--There will not be records for algebraic data types
--there will be a question where we define our own data type and then use it
--there will be a purely algorithmic question (we can use whatever method we want to decompose and solve)
--there are some specifics (aka use list comp., guards, recursion, etc) and some free lance ones
--make sure we understand all homework problems, starred homework probs, especially the less crazy starred hw problems
--Check in class activity problems, bst find/insert probs, and lecture questions
--
--
--
--LIST COMPREHENSIONS
--[Output | Generators, ..., Predicates, ...]
--
nums = [7, 3, 1, 5]


[x * 3 | x <- nums]

[if x < 5 then x * 3 else x | x <- nums] -- = [7, 9, 3, 5]

[x * 3 | x <- nums, x < 5] -- = [9, 3]

[(x, y) | x <- nums, y <- [-2, -3]]  -- = [(7, -2), (7, -3), (9, -2),....]


--RECURSION MWAAHAHAHA
--1) Base case
--2) Decomposition (usually pattern matching the head and the tail)
--3) Recursion Fairies
--4) Combine (3) and (2) into the answer ---compute

aux acc [] = 0
    acc (x:xs) = let recSol = aux acc xs
                 in if x == acc
                 then recSol + 1
                 else recSol

sorted(x:xs) = 
      let aux acc [] = True
          aux acc (x:xs) = if acc <= x
                           then sorted x xs
                           else false
      in aux x xs


--HIGHER ORDER FUNCS

--map f (x:xs) = (f x) : (map f xs) 
combinePairs :: (a -> a -> b) -> (a) -> [b]
combinePairs f [] = []
combinePairs f [x] = error
combinePairs f x:y:xs = (f x y) : (combinePairs xs)
