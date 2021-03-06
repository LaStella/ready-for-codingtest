// 짝수 행 세기
// https://school.programmers.co.kr/learn/courses/30/lessons/68647?language=java

class Solution {
    public int solution(int[][] a) {
        int answer = -1;
        
        int row = a.length;
        int col = a[0].length;
        double mod = 1e7+19;
        
        double[][] combis = new double[row+1][row+1];
        // System.out.println(combis[0][0]);
        combis[0][0] = 1;
        // nCr을 모두 구합니다.
        for(int n = 1 ; n <= row ; n++) {
            for(int r = 0 ; r <= n ; r++) {
                if(r == 0) combis[n][r] = 1;
                else if (r == n) combis[n][r] = 1;
                else combis[n][r] = (combis[n-1][r-1] + combis[n-1][r]) % mod;
            }
        }
        
        int[] numOfOne = new int[col];
        
        // for(int c = 0 ; c < col ; c++) {
        //     for(int r = 0 ; r < row ; r++) {
        //         if(a[r][c] == 1) numOfOne[c]++;
        //     }
        // }
        
        for(int r = 0 ; r < row ; r++) {
            for(int c = 0 ; c < col ; c++) {
                if(a[r][c] == 1) numOfOne[c]++;
            }
        }
        
        // System.out.println(1e7+19);
        
        // dp[i][j] : 첫 번째 열부터 i번째 열까지 j개의 짝수행을 가진 배열의 경우의 수
        double[][] dp = new double[col+1][row+1];
        
        dp[1][row-numOfOne[0]] = combis[row][row-numOfOne[0]];
        
        for(int c = 1 ; c <= col ; c++) {
            for(int r = 0 ; r <= row ; r++) {
                if(dp[c][r] == 0) continue;
                
                for(int k = 0 ; k <= numOfOne[c] ; k++) {
                    int next = (r - k) + (numOfOne[c] - k);
                    
                    if(next > row || r < k) continue;
                    
                    double cases = (combis[r][k] * combis[row-r][numOfOne[c] - k]) % mod;
                    
                    dp[c+1][next] += (dp[c][r] * cases) % mod;
                    // dp[c][r] = 0;
                    // dp[c+1][next] = 0;
                }
            }
        }
        
        System.out.println(dp[col][row]);
        return answer;
    }
}

/*
1. a의 한 열씩 0과 1의 개수를 셉니다.
2. 1에서 계산한 0과 1의 개수로 배치 가능한 b의 열의 경우의 수를 구합니다.
    2-1. (행의 갯수!) / (0의 개수!) * (1의 개수!)
문제발생 : 너무 많은 계산시간을 요구합니다.

dp로 접근
너무 어려워서 풀이글을 보았습니다.
풀이글을 이해를 못하여 현재 이해하려고 보는 중

이해가 안되서 일단 포기합니다.
*/