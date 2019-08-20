#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;


bool canWin(const vector<vector<bool>> &board) {
  for(int x = 0; x <= 30; x++) {
    board.push_back(vector<bool>()); //add 30 empty rows
  }
  for(int y = 0; y <= 30; y++) { 
    for(int x = 0; x < board.size(); x++) {
      board[x].push_back(x * y);     //add columns to all rows, makes square struct
  }
  
}
