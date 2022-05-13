// 사라지는 발판
// https://programmers.co.kr/learn/courses/30/lessons/92345?language=java

import java.util.*;

class Solution {
    int n, m;
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        n = board.length;
        m = board[0].length;
        
        answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1], board).turn;
        
        return answer;
    }
    
    
    // (r1, c1)에 해당하는 좌표의 사람이 움직이는 함수입니다.
    // Pair(win, turn), (r1, c1)좌표에 있는 사람의 승패여부와 턴수를 가진 객체를 리턴합니다.
    public Pair dfs(int r1, int c1, int r2, int c2, int[][] board) {
        // (r1, c1)에 해당하는 사람의 패배입니다.
        // 더 이상 움직일 수 있는 발판이 없습니다.
        if(!canMove(r1, c1, board)) {
            return new Pair(false, 0);
        }
        // (r1, c1)에 해당하는 사람의 승리입니다.
        // 다음 턴에 발판이 사라지게 됩니다. 다음 턴에 종료되므로 턴수를 하나 증가해야합니다.
        if(r1 == r2 && c1 == c2) {
            return new Pair(true, 1);
        }
        
        // 현재 턴의 사람이 승리할 수 있는지 나타내는 변수입니다.
        boolean isWin = false;
        // 현재 턴에서 게임 종료턴까지 최소, 최대 턴을 저장합니다.
        // 현재 턴의 사람이 승리한다면 최소 턴을 구해야 하며, 패배한다면 최대 턴을 구해야합니다.
        // 승리하기 위한 최적의 이동은 곧 최소 턴을 구하는 것이며, 패배를 할 경우 최대한 오래 버텨야 하므로 최대 턴을 구해야 합니다.
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;
        
        // 현재 턴의 사람이 상, 하, 좌, 우로 좌표를 옮깁니다.
        for(int i = 0 ; i < 4 ; i++) {
            int nr = r1+dr[i];
            int nc = c1+dc[i];
            if(!inRange(nr, nc) || board[nr][nc] == 0) continue;
            
            board[r1][c1] = 0;
            Pair result = dfs(r2, c2, nr, nc, board);
            board[r1][c1] = 1;
            
            // 다음 턴의 사람이 진다면, 현재 턴의 사람이 이기는 것을 의미합니다. (승패가 무조건 갈리므로)
            // 현재 턴의 사람이 4방향 이동 중 한번이라도 승리할 수 있다면 isWin을 true로 저장합니다.
            // 반대로, 4방향 이동 중 한번이라도 승리할 수 없다면 isWin은 false가 유지됩니다.
            if(!result.win) {
                isWin = true;
                minTurn = Math.min(minTurn, result.turn);
            }
            
            // 현재 턴의 사람이 승리한다면 패배할 경우에 쓰이는 최대턴은 계산할 필요가 없습니다.
            if(!isWin) {
                maxTurn = Math.max(maxTurn, result.turn);
            }
        }
        
        // 현재 턴의 사람의 승패 여부와 턴수를 반환합니다.
        // 턴수는 현재 턴의 사람이 승리할 수 있다면 최소턴을, 승리할 수 없다면 최대턴을 반환하며
        // 다음 턴으로 넘어가기 때문에 턴 수+1을 해줍니다.
        return new Pair(isWin, isWin ? minTurn+1 : maxTurn+1);
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
    
    // 승패여부와 턴 수를 저장하는 객체입니다.
    class Pair {
        boolean win;
        int turn;
        
        public Pair(boolean win, int turn) {
            this.win = win;
            this.turn = turn;
        }
    }
}