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
        
        for(int c = 0 ; c < col ; c++) {
            for(int r = 0 ; r < row ; r++) {
                if(a[r][c] == 1) numOfOne[c]++;
            }
        }
        
        // System.out.println(1e7+19);
        
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


*/