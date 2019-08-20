data Velocity = MPS Double | FPS Double deriving Show


velToMetDouble :: Velocity -> Double
velToMetDouble (MPS x) = x
velToMetDouble (FPS y) = y / 3.28084

instance Eq Velocity where
 --(==) :: Velocity -> Velocity -> Double 
   (==) v w = velToMetDouble v == velToMetDouble w


instance Ord Velocity where
--compare :: Velocity -> Velocity -> Ordering
compare v w =
        let vNum = velToMetDouble v
            wNum = velToMetDouble w
        in if vNum == wNum then EQ
           else if vNum < wNum then LT
           else GT


class Boolesque a where
    spook :: a -> Bool
    iffy :: a -> b -> b -> b
    iffy bs trueCase falseCase =
            if spook bs
            then trueCase
            else falseCase
    spook bs = iffy bs True False

instance Boolesque Bool where
  --spook :: Bool -> Bool
  spook b = b

instance Boolesque Integer where
   spook x = if x <= 0 then False else True

--make lists instances of the Boolesque typeclass.
--try to think of two distinct ways this could be done.
instance Boolesque a => Boolesque [a] where
    spook [] = False
    spook (x:xs) = spook x

