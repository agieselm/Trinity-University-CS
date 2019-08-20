data BST t = Leaf | Node t (BST t) (BST t)

size :: BST a -> Integer
size Leaf = 0
size (Node x left right) = 1 + size left + size right

element :: Ord a => a -> BST a -> Bool
element y Leaf = False
element y (Node x left right) = (y == x) || (element y left) || (element y right)

singleton x = Node x Leaf Leaf
treeA = Node 18 (Node 9 (Node 7 (singleton 4) 
                                Leaf) 
                                (singleton 15))
                        (Node 20 Leaf
                                (singleton 21))

insert :: Ord a => a -> BST a -> BST a
insert y Leaf = Node y Leaf Leaf
insert y tree@(Node root left right) =
    case compare y root of
      EQ -> tree
      LT -> let newLeft = insert y left
            in Node root newLeft right
      GT -> let newRight = insert y right
            in Node root left newRight
                
--find element that doesnt search the whole tree

element' :: Ord a => a -> BST a -> Bool
element' y Leaf = False
element' y (Node x left right)
    | y == x = True
    | y < x = element' y left 
    | y > x = element' y right
element' y (Node x left right) = 
   case compare y x of
      EQ -> True
      LT -> element' y left
      GT -> element' y right


--insertInList :: Ord a -> a -> a -> [a] -> [a]
--insertInList y [] = [y]
--insertInList y (x:xs) = 
--     case compare y x of
--         EQ -> x:xs 
--         LT -> y:x:xs
--         GT -> x:(insertInList y:xs)

isEmpty :: BST a -> Bool
isEmpty Leaf = True
isEmpty (Node x left right) = False

toList :: BST a -> [a]
toList Leaf = []
toList (Node root left right) = (toList left) ++ (root:(toList right))

toTree :: Ord a => [a] -> BST a
toTree [] = Leaf
--toTree (x:xs) = let sapling = toTree xs
--                in insert x sapling
toTree lst = foldr insert Leaf lst




--TYPE CLASSES - Classses (categories) of types
               --that implement a common interface
               --two parts to type classes::::
                     --Declare the class
                     --instance class to a type



