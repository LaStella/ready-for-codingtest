// 짝수 행 세기
// https://school.programmers.co.kr/learn/courses/30/lessons/68647?language=java

class Solution {
    public int solution(int[][] a) {
        int answer = -1;
        
        int n = a.length;
        int m = a[0].length;
        
        for(int c = 0 ; c < m ; c++) {
            int count = 0;
            for(int r = 0 ; r < n ; r++) {
                if(a[r][c] == 1) count++;
            }
            // n! / ( (n-count)! * count! )
        }
        
        return answer;
    }
}

/*
1. a의 한 열씩 0과 1의 개수를 셉니다.
2. 1에서 계산한 0과 1의 개수로 배치 가능한 b의 열의 경우의 수를 구합니다.
    2-1. (행의 갯수!) / (0의 개수!) * (1의 개수!)
문제발생 : 너무 많은 계산시간을 요구합니다.

*/