import Data.Char
import Data.List
import System.IO
import System.Environment
import System.Console.GetOpt

data Flag = NoLoop | Count String | Help deriving (Eq, Show)

--add a flag -i --index for which fortune to chose. 
--Only ask the user for an index if one isn't provided.

options :: [OptDescr Flag]
options = [
    Option ['n'] ["noloop"] (NoArg NoLoop) "Loop until the user wants to stop.",
    Option ['h'] ["help"] (NoArg Help) "Print a help file and exit.",
    Option ['c'] ["count"] (ReqArg Count "num") "Print <num> fortunes"
    Option ['i'] ["index"] (
    ]


numberOfFortunes :: [Flag] -> Int
numberOfFortunes [] = 1
numberOfFortunes ((Count x):xs) = read x
numberOfFortunes (_:xs) = numberOfFortunes xs

main :: IO ()
main = do
    args <- getArgs
    let (flags, others, errors) = getOpt Permute options args
    if Help `elem` flags
    then putStrLn (usageInfo "Usage Info: Fortune" options)
    else do
        handle <- openFile "fortunes.txt" ReadMode
        fortunes_text <- hGetContents handle
        let fortunes = lines fortunes_text
        putStrLn "Welcome to fortunes!"
        fortuneLoop fortunes flags

fortuneLoop fortunes flags = do
    putStr "Enter a number: "
    num_string <- getLine
    if all isDigit num_string then do
        let num = read num_string
        let index = num `mod` (length fortunes)
        let number = numberOfFortunes flags
        putStrLn "Your fortune(s): "
        putStr "\t"
        putStrLn (chooseFortunes fortunes index number)
    else putStrLn "Nice try, but you'll have to do better than that."
    if NoLoop `elem` flags
    then return ()
    else do
        putStr "Would you like another? "
        answer <- getLine
        if (map toLower answer) `elem` ["y", "yes"] then
           fortuneLoop fortunes flags
        else return ()

chooseFortunes fortunes start number =
    let indexes = map (*start) [1..number]
        safeIndexes = map (`mod` (length fortunes)) indexes
                      {-[i `mod` (length fortunes) | i <- indexes]-}
  chosenFortunes = map (fortunes!!) safeIndexes
    in intercalate "\n\t" chosenFortunes
