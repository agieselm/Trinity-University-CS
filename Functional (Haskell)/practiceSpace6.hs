--disjoint types (e.g. lists, arr, tuples, etc)

--data Name = Const[type...] [| const[two...]...] 

--keyword (Name), all type names are capitalized (name you are defining) Const(constructor) also capitalized.
--abstract syntax for any type declaration you could make (above)


data Contest = Rock | Scissors | Paper deriving Show

stringOfContest :: Contest -> String
stringOfContest Rock = "Rock"
stringOfContest Scissors = "Scissors"
stringOfContest Paper = "Paper"

whoWins :: Contest -> Contest -> String
whoWins Scissors Paper = "First Player"
whoWins Paper Rock = "First Player"
whoWins Rock Scissors = "First Player"
whoWins Paper Scissors = "Second Player"
whoWins Rock Paper = "Second Player"
whoWins Scissors Rock = "Second Player"
whoWins x y = "Tie between " ++ (stringOfContest x) ++ " and " ++ (stringOfContest y)



data Velocity = MetersPerSecond Float | FeetPerSecond Float --constructor carries a float data

--data Shape = Circle Float Float Float | Rectangle Float Float Float Float deriving Show

--area :: Shape -> Float 
--area (Circle x y r) = pi * r^2
--area (Rectangle x1 y1 x2 y2) = abs $ (x2 - x1) * (y2 - y1)

--constructors that don't take values are called base constructors
--area (Circle p r) = pi * r^2


type Point = (Float, Float)
data Shape = Circle Point Float | Rectangle Point Point | Triangle Point Point Point deriving Show

area :: Shape -> Float 
area (Circle center r) = pi * r^2
area (Rectangle (x1, y1) (x2, y2)) = abs $ (x2 - x1) * (y2 - y1)
area (Triangle (x y z) = 


