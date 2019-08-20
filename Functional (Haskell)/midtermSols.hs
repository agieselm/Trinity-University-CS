{-
--problemo uno

adjacentPairs lstA lstB = [(x, x+1) | x <- lstA, x+1 `elem` lstB]
adjacentPairs2 lstA lstB = [(x, y) | x <- lstA, y <- lstB, x + 1 == y]

subLst [] lst = True
subLst (x:xs) lst = x `elem` lst && subLst xs lst
                {- if x `elem lst
 -                 then subLst xs lst
 -                 else False -}

subLst lst' lst = all (\x -> x `elem` lst) lst'
subLst lst' lst = foldr (\x acc -> x `elem` lst && acc) True lst'
subLst lst' lst = not(False `elem` (map (`elem` lst) lst'))

--Problemo dos

iter :: Integer -> (a -> a) -> a -> a
iter 0 f x = x
iter n f x = iter (n - 1) f (f x)
                {- f (iter (n-1) f x) -}

mult x n = iter x (\a -> a + n) 0

expo x n = iter n (\a -> mult a x) 1

tetrate x n = iter (n-1) (\a -> expo a x) x

--Problemo tres

seqMaybe :: [Maybe a] -> Maybe [a]
seqMaybe [] = []
seqMaybe (Nothing:_) = Nothing
seqMaybe ((Just x):xs) = 
    let recSol = seqMaybe xs
    in case recSol of
        Nothing -> Nothing
        Just lst -> Just (x:lst)
-}

--FINALLY

--1.1

lookupyo [] a = error "no keys"
lookupyo ((x, y):xs) a = if (x == a) then y else lookupyo xs a

--1.2

rev lst val = [fst x | x <- lst, snd x == val]

--1.3

dup lst = func[fst x | x <- lst]
          where func [] = False
                func(x:xs) = if x `elem` xs
                             then True
                             else func xs


--2

dasFold :: (b -> Maybe(a, b)) -> b -> [a]
dasFold f x = 
        case f x of 
             Just (a, y) -> a: dasFold f y
             Nothing -> []

--3

data BST a = Leaf | Node a (BST a) (BST a)
data Child = Izquierda | Derecha
type Path = [Child]

findPath :: Path -> BST a -> Maybe a
findPath [] tree =
         case tree of (Node a _ _) -> Just a
                      Leaf         -> Nothing
findPath paths Leaf = Nothing
findPath (x:xs) (Node a lchild rchild) =
          case x of Izquierda -> findPath xs lchild
                    Derecha   -> findPath xs rchild 

--4

TakeMeHome val [] = []
TakeMeHome val (x:xs) = if (x /= val) then []
               else x:(TakeMeHome val xs)

fSeq [] = []
fSeq (x:xs) =
     let a = TakeMeHome x xs
         b = drop (length a) xs
     in (x:a) fSeq b

getMax lst = aux (drop1 lst) (head lst) 
    where aux [] acc = acc
          aux (x:xs) acc = if(length x > length acc)
                   then aux xs x
                   else aux xs acc

getMaxSet lst = getMax (fSeq lst)


--5

headOfTuples :: [(a, a)] -> [a]
headOfTuples ((x, y):xs) = x: headOfTuples xs
headOfTuples _ = []

filterLst :: (a -> Bool) -> [a] -> [a]
filterLst _ [] = []
filterLst p (x:xs)
    |p x = x : filterLst p xs
    |otherwise = []

headVamp :: String -> [String]
headVamp "" = []
headVamp a = headOfTuples $ filterLst ((/=a).fst) listOfTuples


