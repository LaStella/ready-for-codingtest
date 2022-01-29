// 땅따먹기
// https://programmers.co.kr/learn/courses/30/lessons/12913?language=java

import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        for(int r = 1 ; r < land.length ; r++) {
            // 행의 각 열에 이전 행의 가능한 최대값(같은 열의 값은 더하지 못함)을 더합니다.
            for(int c = 0 ; c < land[0].length ; c++) {
                land[r][c] += getMax(r-1, c, land);
            }
        }
        
        Arrays.sort(land[land.length-1]);
        
        answer = land[land.length-1][3];
        
        return answer;
    }
    
    // 주어진 행의 최대값을 return하는 함수입니다. 단, 열이 같을 수 없습니다.
    public int getMax(int row, int column, int[][] land) {
        int max = 0;
        
        for(int c = 0 ; c < land[0].length ; c++) {
            if(c == column) continue;
            max = Math.max(max, land[row][c]);
        }

        return max;
    }
}