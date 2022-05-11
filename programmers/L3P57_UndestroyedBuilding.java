// 파괴되지 않은 건물
// https://programmers.co.kr/learn/courses/30/lessons/92344?language=java

class Solution {
    int n, m;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        n = board.length;
        m = board[0].length;
        // 건물이 파괴 또는 회복되는 정도를 저장할 공간입니다.
        int[][] degrees = new int[n+1][m+1];
        
        for(int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5]*(type == 1 ? -1 : 1);
            
            // 모든 스킬의 범위와 degree를 저장하여 한번에 누적합을 구합니다.
            // 자세한 설명은 getDegrees에서 합니다.
            degrees[r1][c1] += degree;
            degrees[r1][c2+1] -= degree;
            degrees[r2+1][c1] -= degree;
            degrees[r2+1][c2+1] += degree;
        }
        
        getDegrees(degrees);
        
        // 건물의 내구도에 파괴 또는 회복의 정도를 더합니다.
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < m ; c++) {
                board[r][c] += degrees[r][c];
                if(board[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    // 스킬의 공격과 회복의 정도(degree)의 누적합을 구하는 함수입니다.
    // degrees를 채울때 열부터 채운 후, 행을 채웁니다.
    // 현재 열의 값(degrees[r][c])은 이전 열의 값(degrees[r][c-1])을 더하는 것입니다.
    // 마지막 열까지 degree가 채워져야 하며, 마지막 열 이후로는 degree가 채워져서는 안됩니다.
    // 따라서 (마지막+1)열에 -degree를 채워 이후의 열에 degree가 채워지지 않도록 막습니다.
    // 위 과정을 행방향도 동일하게 적용합니다.
    public void getDegrees(int[][] degrees) {
        // 열방향 누적합을 저장합니다.
        for(int r = 0 ; r < n+1 ; r++) {
            for(int c = 1 ; c < m+1 ; c++) {
                degrees[r][c] += degrees[r][c-1];
            }
        }
        
        // 행방향 누적합을 저장합니다.
        for(int c = 0 ; c < m+1 ; c++) {
            for(int r = 1 ; r < n+1 ; r++) {
                degrees[r][c] += degrees[r-1][c];
            }
        }
    }
}


/*
모든 스킬들의 주어진 각 사각형의 범위안에 degree를 채우는것
스킬마다 주어지는 좌표는 4개
(r1,c1) ~ (r1,c2) 의 범위에 degree를 채워야함
(r1,c1)에 degree를 표기 (r1,c2)방향으로 이동하며 degree를 더하게된다.
c1~c2로 이동하면서 degree를 더하므로 현재 열에 이전 열의 값을 더하는것.
c=0 ~ c=board[0].length까지 이전열의 값을 더한다.
 , 스킬의 마지막 열 이후로 더하면 안되므로 스킬의 마지막열까지 degree가 더해질 수 있도록 (마지막+1)열에는 -degree값을 넣어준다.

여기까지 열방향으로 이동하면서 채우는 과정
이후 행방향 이동하면서 채워야한다.


*/