data Player = PlayerOne | PlayerTwo deriving (Show, Eq)

data Square = Mark Player | Empty deriving (Show, Eq)

type Board = [[Square]]

data Game = Game Board Player

type Move = (Int, Int)

data Result = Tie | Won Player | Ongoing deriving (Show, eq)

makeMove :: Game -> Move -> Maybe Game
makeMove = undefined

validMove :: Game -> Move -> Bool
validMove (Game Board x) (y, z) 
                | y < 0 = False
                | y > 2 = False
                | z < 0 = False
                | z > 2 = False 
                | Otherwise = True



whoWins :: Game -> Maybe Result --returns Nothing if the game isn't over
whoWins = undefined

tripleWinner :: Square -> Square -> Square -> Maybe Player
tripleWinner (Mark x) (Mark y) (Mark z) = 
   if (x == y == z)
   then Just x
   else Nothing
tripleWinner _ _ _ = Nothing

combineWinner :: Maybe Player -> Maybe Player -> MaybePlayer
combineWinner Nothing (Just x) = Just x
combineWinner (Just x) Nothing = Just x
combineWinner Nothing Nothing = Nothing
combineWinner (Just x) (Just y)
    if x == y
    then Just x
    else error "This should never happen"

gameResult :: Game -> Maybe Result 
gameResult (board, player) = 
    let winner = (horizontalWinner board) `combineWinner`
                 (verticalWinner board) `combineWinner`
                 (diagonalWinner board)
    in case winner of
        Just player -> Just (Won Player)
        Nothing -> if tieGame Board
                      then Just Tie
                      else Nothing

horizontalWinner :: Board -> Maybe Player
horizontalWinner [row1, row2, row3] = 
    let checkRow [space1, space2, space3] = tripleWinner space1 space2 space3
        checkRow _ = error "incorrect tic tac toe row"
    in (checkRow row1) `combineWinner` (checkRow row2) `combineWinner` (checkRow row3)
horizontalWinner _ = error "Incorrect tic tac toe board"

verticalWinner [s1:r1, s2:r2, s3:r3] = 
    (tripleWinner s1 s2 s3) `combineWinner` (verticalWinner r1 r2 r3)
verticalWinner [[], [], []] = Nothing

diagonalWinner [row1, row2, row3] = 
   (verticalWinner [drop 0 row1, drop 1 row2, drop 2 row3])
   `combineWinner`
   (verticalWinner [drop 2 row1, drp 1 row2, drop 0 row3])

tieGame board = not (Empty `elem` (concat board))
