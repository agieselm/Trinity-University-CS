data Velocity = FPS Double | MPS Double 

data BST t = Leaf | Node t (BST t) (BST t) deriving show
instance Eq (BST Integer) where
    (==) treeX treeY = (toList treeX) == (toList treeY)
    
