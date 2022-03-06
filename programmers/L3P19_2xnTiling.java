// 2 x n 타일링
// https://programmers.co.kr/learn/courses/30/lessons/12900?language=java#

class Solution {
    public int solution(int n) {
        int answer = 1;
        
        // dynamic programming의 약자.
        // 타일을 붙이는 경우의 수는 피보나치 수열과 같으며 n=0일때와 n=1일때 가능한 경우의 수는 1가지입니다.
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2 ; i <= n ; i++) {
            // 결과값이 되는 dp[n]에 나머지 연산을 하는 것은 결국 (dp[n-1] + dp[n-2]) 에 나머지 연산을 하는 것입니다.
            // 나머지 연산은 분배법칙이 가능하므로 모든 계산에 나머지 연산을 하게되면 overflow가 발생하지 않으며 정답에 아무런 영향이 없습니다.
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        
        answer = dp[n];
        
        return answer;
    }
}


/*
n = 1일때
1가지 가능
n = 2일때
2가지 가능
n = 3일때
3가지 가능
n = 4일때
5가지 가능
피보나치 수열
*/