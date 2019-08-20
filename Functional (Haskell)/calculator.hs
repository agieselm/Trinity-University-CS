--Data types representing inputs
data Operation = Plus | Minus | Mult | Div deriving Show
data Input =  Number Float | Op Operation  deriving Show

--Data type representing the state of calculator after a series of inputs
data State = HasValue Float | HasOp Float Operation deriving Show

--showState (HasValue 7.0) = "7.0"
--showState (HasValueOp 7.0 Plus) = "7.0 +"
showState:: State -> String
showState (HasValue v) = (show v)
showState (HasOp v Plus) = (show v) ++ " +"
showState (HasOp v Minus) = (show v) ++ " -"
showState (HasOp v Div) = (show v) ++ " /"
showState (HasOp v Mult) = (show v) ++ " *"

funcOfOp :: Operation -> (Float -> Float -> Float)
funcOfOp Plus = (+)
funcOfOp Minus = (-)
funcOfOp Mult = (*)
funcOfOp Div = (/)

--take a current state, and an input, and return the updated state.
update:: State -> Input -> Maybe State
update (HasValue x) (Op o) = Just (HasOp x o)
update (HasOp x o) (Number y)  = Just (HasValue z)
   where z = (funcOfOp o) x y
update (HasOp x o) (Op op2) = Nothing

data MaybeFloat = JustF Float | NoF deriving Show

addToMF :: Float -> Float -> MaybeFloat
addToMF (Just x) y = Just (x + y)
addToMF (Nothing) y = Nothing

smartDivide :: Float -> Float -> MaybeFloat
smartDivide x y = if y == 0
                then Nothing
                else Just (x / y)

data List a = Cons a (List a) | Nill deriving Show

--take a sequence of inputs, and return the result of evaluating them in order
--evanuateSequence [Number 7, Op Add, Number 3] = HasValue 10.0
evaluateSequence :: [Input] -> State
evaluateSequence = undefined

-- "+" = Op Add
readInput :: String -> Input
readInput = undefined





