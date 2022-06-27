// 블록 게임
// https://programmers.co.kr/learn/courses/30/lessons/42894?language=java

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        
        int n = board.length;
        HashMap<Integer, Integer[]> block_map = new HashMap<>();
        
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                if(board[r][c] != 0) {
                    Integer[] block = block_map.getOrDefault(board[r][c], new Integer[] {r, c, r, c});
                    if(block[1] > c) {
                        block[1] = c;
                    }
                    if(block[2] < r) {
                        block[2] = r;
                    }
                    if(block[3] < c) {
                        block[3] = c;
                    }
                    block_map.put(board[r][c], block);
                }
            }
        }
        
        return answer;
    }
}


/*
보드 전체를 탐색하며 map에 블록을 저장합니다.
map의 키는 블록에 쓰여진 숫자이며 값은 블록의 두 꼭지점이 됩니다. (r1, c1, r2, c2)
    >예시의 4번 블록의 경우 (4, 6) (5, 5) (5, 6) (6, 6)이며 두 꼭지점은 (4, 5) (6, 6) 이 됩니다.
맵에 저장한 블록을 차례로 꺼내 제거가능한 블록을 제거합니다.
    > 블록의 두 꼭지점을 범위로 board에서 값을 꺼냅니다.
    > key와 같은 값이면 넘어가며, 0이 존재한다면 해당 칸을 기준으로 위로 블럭이 존재하는지 확인합니다.
    > 0이아닌 다른 값이 존재하면 제거할 수 없는 블럭입니다.
*/