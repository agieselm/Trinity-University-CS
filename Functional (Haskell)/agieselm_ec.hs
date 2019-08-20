--Problem Uno

type Name = String
data FSO = Text Name String | Binary Name [Int] | Directory Name [FSO] deriving Show

less = Binary "less" [72, 93, 197, 75]
lessh = Text "less.help" "For help, invoke man less"
root = Directory "" [usr]
    where usr = Directory "usr" [bin, etc]
          bin = Directory "bin" [less, lessh]
          etc = Directory "etc" []

getName :: FSO -> Name
getName (Text name _) = name
getName (Binary name _) = name
getName (Directory name _) = name

--isDirectory :: [Name] -> FSO -> Maybe FSO
--isDirectory (y:ys) (Directory n (x:xs)) = 
--                   let recSol = (isDirectory ys (Directory n (x:xs)))
--                   in if (y == (getName x)) then True else recSol

--findFSO :: [Name] -> FSO -> Maybe FSO
--findFSO path fileobject = if(isDirectory 



--Problem Dos

data IntervalOrd = Within | Smaller | Greater deriving (Eq, Show)
data Tree a = Leaf | Node a (Tree a) (Tree a) deriving Show

--a
compareInterval :: Ord a => a -> (a, a) -> IntervalOrd
compareInterval x (lower, upper)
                                |x < lower = Smaller
                                |x > upper = Greater
                                |x >= lower && x <= upper = Within
--b
mostLeft :: Tree a -> a
mostLeft (Node x Leaf _) = x
mostLeft (Node x left _) = mostLeft left

mostRight :: Tree a -> a
mostRight (Node x Leaf _) = x
mostRight (Node x _ right) = mostRight right

intervalOfTree :: Tree a -> (a, a)
intervalOfTree tree@(Node x left right)= (mostLeft tree, mostRight tree)

--c
treeInInterval :: Ord a => Tree a -> (a, a) -> Tree a
treeInInterval tree@(Node x l@(Node n _ _) r@(Node m _ _)) (a, b) = 
                                                if((compareInterval x (a, b)) == Within) then tree 
                                                else if ((compareInterval n (a, b)) == Within) then l 
                                                else if ((compareInterval m (a, b)) == Within) then r 
                                                else Leaf
