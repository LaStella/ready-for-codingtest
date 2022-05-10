// 파괴되지 않은 건물
// https://programmers.co.kr/learn/courses/30/lessons/92344?language=java

import java.util.*;

class Solution {
    int n, m;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        n = board.length;
        m = board[0].length;
        int[][] degrees = new int[n+1][m+1];
        
        for(int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5]*(type == 1 ? -1 : 1);
            System.out.println(degree);
            degrees[r1][c1] += degree;
            degrees[r1][c2+1] -= degree;
            degrees[r2][c1] -= degree;
            degrees[r2][c2+1] += degree;
        }
        }
        
        // for(int r = 0 ; r < n+1 ; r++) {
        //     for(int c = 0 ; c < m+1 ; c++) {
        //         System.out.printf("%3d", degrees[r][c]);
        //     }
        //     System.out.println();
        // }
        getDegrees(degrees);
        // for(int r = 0 ; r < n+1 ; r++) {
        //     for(int c = 0 ; c < m+1 ; c++) {
        //         System.out.printf("%3d", degrees[r][c]);
        //     }
        //     System.out.println();
        // }
        
        return answer;
    }
    
    public void getDegrees(int[][] degrees) {
        for(int r = 0 ; r < n+1 ; r++) {
            for(int c = 1 ; c < m+1 ; c++) {
                degrees[r][c] += degrees[r][c-1];
            }
        }
        
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