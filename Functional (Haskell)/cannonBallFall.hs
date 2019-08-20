Timetofall :: Double -> Integer
Timetofall h = aux h 0 
   where aux h v
       | h <= 0 = 0
       | otherwise = 
              let v' = v - 9.8
                  h' = h + v'
              in 1 + (aux h' v')
   
               
