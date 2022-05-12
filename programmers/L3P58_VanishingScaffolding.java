// 사라지는 발판
// https://programmers.co.kr/learn/courses/30/lessons/92345?language=java

class Solution {
    int n, m;사라지는 발판
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        n = board.length;
        m = board[0].length;
        
        
        return answer;
    }
    
    
    // (r1, c1)에 해당하는 좌표가 움직이며 탐색하는 함수입니다.
    public void dfs(int r1, int c1, int r2, int c2, int[][] board) {
        if(!canMove(r1, c1, board)) {
            // (r1, c1)에 해당하는 사람의 패배입니다.
        }
        if(r1 == r2 && c1 == c2) {
            // (r1, c1)에 해당하는 사람의 승리입니다.
        }
        
        int turn = 0;
        
        for(int i = 0 ; i < 4 ; i++) {
            int nr = r1+dr[i];
            int nc = c1+dc[i];
            if(!inRange(nr, nc) || board[nr][nc] == 0) continue;
            
            board[nr][nc] = 0;
            dfs(r2, c2, nr, nc, board);
            board[nr][nc] = 1;
            
        }
    }
    
    // (r, c)에 해당하는 좌표의 사람이 움직일 수 있는지 확인하는 함수입니다.
    public boolean canMove(int r, int c, int[][] board) {
        for(int i = 0 ; i < 4 ; i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(inRange(nr, nc) && board[nr][nc] != 0) {
                return true;
            }
        }
        
        return false;
    }
    
    // (r, c)좌표가 보드 범위 안의 좌표인지 확인하는 함수입니다.
    public boolean inRange(int r, int c) {
        return 0<=r && r < n && 0<=c && c < m;
    }
    
    class 
}