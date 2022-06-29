// 블록 게임
// https://programmers.co.kr/learn/courses/30/lessons/42894?language=java

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        
        int n = board.length;
        HashMap<Integer, Integer[]> block_map = new HashMap<>();
        
        // 보드 전체를 탐색하며 블록을 맵에 저장합니다.
        // (key : 블록의 번호, value : 직사각형의 두 꼭지점) 형태로 저장합니다.
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                // 해당 칸이 빈칸이 아니라면 블록이 존재합니다.
                if(board[r][c] != 0) {
                    // 칸에 위치한 블록의 번호를 키로 사각형의 두 꼭지점(r1, c1, r2, c2)을 가져옵니다. (r1 < r2, c1 < c2)
                    // 없는 블록이라면 기본값으로 (r, c, r, c)를 저장합니다.
                    Integer[] block = block_map.getOrDefault(board[r][c], new Integer[] {r, c, r, c});
                    // 블록의 두 꼭지점을 (최소row, 최소col, 최대row, 최대col) 형태로 비교하여 저장합니다.
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
        
        while(true) {
            ArrayList<Integer> removable_block_list = new ArrayList<>();
            // 블록맵에서 제거 가능한 블록의 번호를 저장합니다.
            for(int block_num : block_map.keySet()) {
                if(canRemove(block_num, board, block_map)) {
                    removable_block_list.add(block_num);
                }
            }
            
            // 제거 가능한 블록이 더이상 없다면 중단합니다.
            if(removable_block_list.isEmpty()) break;
            
            // 블록을 제거합니다.
            for(int block_num : removable_block_list) {
                answer++;
                removeBlock(block_num, board, block_map);
            }
        }
        
        
        
        return answer;
    }
    
    // 블록을 제거할 수 있는지 확인하는 함수입니다.
    public boolean canRemove(int block_num, int[][] board, HashMap<Integer, Integer[]> block_map) {
        Integer[] block = block_map.get(block_num);
        for(int r = block[0] ; r <= block[2] ; r++) {
            for(int c = block[1] ; c <= block[3] ; c++) {
                // 블록이 빈칸이라면 검은 블록을 떨어뜨려 채울 수 있는지 확인합니다.
                if(board[r][c] == 0) {
                    if(!canFill(r, c, board)) {
                        return false;    
                    }
                }
                else {
                    // 다른 블록이 위치한 경우 직사각형으로 만들 수 없습니다.
                    if(board[r][c] != block_num) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    // 검은 블록을 채울 수 있는지 확인하는 함수입니다.
    public boolean canFill(int row, int col, int[][] board) {
        // 주어진 row, col 을 기준으로 위에 블록이 존재한다면 채울 수 없습니다.
        for(int r = row-1 ; r >= 0 ; r--) {
            if(board[r][col] != 0) return false;
        }
        
        return true;
    }
    
    // 블록을 제거하는 함수입니다.
    public void removeBlock(int block_num, int[][] board, HashMap<Integer, Integer[]> block_map) {
        Integer[] block = block_map.get(block_num);
        for(int r = block[0] ; r <= block[2] ; r++) {
            for(int c = block[1] ; c <= block[3] ; c++) {
                board[r][c] = 0;
            }
        }
        block_map.remove(block_num);
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