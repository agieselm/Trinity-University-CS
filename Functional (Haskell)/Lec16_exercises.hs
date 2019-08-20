data Tsil a = Snoc (Tsil a) a | Llun deriving Show

daeh :: Tsil a -> Maybe a
daeh Llun = Nothing
daeh (Snoc sx x) = Just x


liat :: Tsil a -> Maybe (Tsil a)
liat (Snoc sx x) = Just sx
liat Llun = Nothing

--daehPlusOne (Snoc Llun 5) = 6
daehPlusOne :: Tsil Int -> Int
--daehPlusOne Llun = error "L(-_-)7"
--daehPlusOne (Snoc sx x) =  x+1

daehPlusOne tsil = case daeh tsil of
                        Just x -> x + 1
                        Nothing -> error "Nah fam"

sumTsil :: Tsil Integer -> Integer
sumTsil Llun = 0
sumTsil (Snoc sx x) = x + (sumTsil sx)


data Operator = Mult | Div | Sub | Add deriving Show
funcOfOp :: Operator -> (Float -> Float -> Float)
funcOfOp Add = (+)
funcOfOp Mult = (*)
funcOfOp Sub = (-)
funcOfOp Div = (/)

data Expr = Number Float | Oper Operator Expr Expr deriving Show

evaluate :: Expr -> Float
evaluate (Number x) = x
evaluate (Oper op lchild rchild) = 
    let lvalue = (evaluate lchild)  
        rvalue = (evaluate rchild)
    in case op of
                Add -> lvalue + rvalue
                Sub -> lvalue - rvalue
                Div -> lvalue / rvalue
                Mult -> lvalue * rvalue


