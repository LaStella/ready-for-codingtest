// 순위
// https://programmers.co.kr/learn/courses/30/lessons/49191?language=java

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] result = new boolean[n][n];
        
        for(int[] r : results) {
            result[r[0]-1][r[1]-1] = true;
        }
        
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                for(int k = 0 ; k < n ; k++) {
                    if(result[j][i] && result[i][k]) {
                        result[j][k] = true;
                    }
                }
            }
        }
        
        
        for(boolean[] r : result) {
            int count = 0;
            for(boolean b : r) {
                if(b) count++;
            }
            if(count == n-1) answer++;
        }
        
        return answer;
    }
}