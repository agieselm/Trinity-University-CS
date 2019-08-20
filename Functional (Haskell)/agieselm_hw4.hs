--Austin Gieselman
import CSCICourses
import Data.List

--1
data Group = Design | Applications | Systems deriving Show

--2
type CID = String
type Hours = Int
type Requirement = [CID]
data Course = GroupCourse CID Hours Group Requirement | StandardCourse CID Hours Requirement deriving Show

--3
readCourse :: String -> Course
readCourse strings = 
        let inputs = words strings
        in case inputs of
                cid:hours:"Design":reqs -> GroupCourse cid (read hours) Design reqs
                cid:hours:"Applications":reqs -> GroupCourse cid (read hours) Applications reqs
                cid:hours:"Systems":reqs -> GroupCourse cid (read hours) Systems reqs
                cid:hours:"None":reqs -> StandardCourse cid (read hours) reqs

--4
type HoursTaken = Int
data Curriculum = Curr [CID] [Group] HoursTaken deriving Show

--5
csciBach :: Curriculum
csciBach = Curr ["1120", "1320", "1321", "1323", "2320", "2321", "2322", "3320", "3321", "3322"] [Design, Applications, Systems] 49

csciC2M :: Curriculum
csciC2M = Curr ["1120", "1320", "1321", "1323", "2320"] [] 34

--6

type Transcript = [Course]
frances :: Transcript
frances = [(StandardCourse "1321" 3 ["1320"]), (StandardCourse "1120" 1 ["1320"]), (StandardCourse "1320" 3 [])]

sally :: Transcript
sally = [(StandardCourse "1120" 1 ["1320"]), (StandardCourse "1320" 3 []), (StandardCourse "1320" 3 []), (StandardCourse "1321" 3 ["1320"]), (StandardCourse "1323" 3 ["1320"]), (StandardCourse "2320" 3 ["1321", "1120"]), (GroupCourse "6969" 21 Design [])]

--7
findRequirements :: Course -> Requirement
findRequirements (StandardCourse cid hours reqs) = reqs
findRequirements (GroupCourse cid hours group reqs) = reqs

findCourse :: Transcript -> CID -> Bool
findCourse trans cid = cid `elem` (map seekCID trans)

canTake :: Transcript -> Course -> Bool 
canTake trans c = 
        let reqs = (findRequirements c)
        in all (findCourse trans) reqs

--8

hoursTaken :: Transcript -> Int
hoursTaken [] = 0
hoursTaken ((StandardCourse cid hrs reqs):xs) = hrs + (hoursTaken xs)
hoursTaken ((GroupCourse cid hrs group reqs):xs) = hrs + (hoursTaken xs)

--9
instance Eq Group where
        (==) Design Design = True
        (==) Systems Systems = True
        (==) Applications Applications = True
        (==) _ _ = False

groupsTaken :: Transcript -> [Group]
groupsTaken [] = []
groupsTaken ((StandardCourse x hours reqs) : xs) = (groupsTaken xs)
groupsTaken ((GroupCourse x hours group req) : xs) = nub (groupsTaken xs)

--10

getReqsFromTrans:: Transcript -> Requirement
getReqsFromTrans [] = []
getReqsFromTrans ((StandardCourse x hours req):xs) = x:(getReqsFromTrans xs)
getReqsFromTrans ((GroupCourse x hours group req):xs) = x:(getReqsFromTrans xs)

checkReqs :: Requirement -> Requirement -> Bool
checkReqs [] reqs = False
checkReqs (x:[]) reqs = x `elem` reqs
checkReqs (x:xs) reqs = x `elem` reqs && checkReqs xs reqs

canGraduate :: Curriculum -> Transcript -> Bool
canGraduate (Curr cid group hours) t = checkReqs cid (getReqsFromTrans t)

--11

findDiff :: Eq a => [a] -> [a] -> [a]
findDiff [] [] = []
findDiff xs ys = xs \\ ys

seekCID :: Course -> CID
seekCID (GroupCourse n _ _ _) = n
seekCID (StandardCourse n _ _) = n

missing :: Curriculum -> Transcript -> String
missing (Curr cid group hours) t = 
        let missingCourses = findDiff cid (map seekCID t)
            missingGroups = findDiff group (groupsTaken t)
            remainingHours = hours - hoursTaken t
        in "Missing hours" ++ " " ++ unwords(map show missingCourses) ++ ", " ++ "Missing groups" ++ unwords(map show missingGroups) ++ ", " ++ "hours needed to complete" ++ show remainingHours
urse strings =
        let inputs = words strings
        in case inputs of
                cid:hours:"Design":reqs -> GroupCourse cid (read hours) Design reqs
                cid:hours:"Applications":reqs -> GroupCourse cid (read hours) Applications reqs
                cid:hours:"Systems":reqs -> GroupCourse cid (read hours) Systems reqs
                cid:hours:"None":reqs -> StandardCourse cid (read hours) reqs

