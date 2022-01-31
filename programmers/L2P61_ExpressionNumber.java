// 숫자의 표현
// https://programmers.co.kr/learn/courses/30/lessons/12924?language=java

class Solution {
    public int solution(int n) {
        int answer = 0;
        // a + (a+1) + (a+2) ... (a+b) 와 같이 연속된 자연수의 합은 (2a+b)(a+1)/2 입니다.
        for(int a = 1 ; a <= n ; a++) {
            // a+b 가 n보다 크게되면 연속된 자연수의 합은 n보다 커지므로 b의 범위는 a+b까지입니다.
            for(int b = 0 ; a+b <= n ; b++) {
                int total = (2*a+b)*(b+1)/2;
                if(total == n) {
                    answer++;
                    break;
                }
                // 연속된 자연수의 합이 n을 넘어서면 그 이후로도 n을 넘으므로 확인할 필요가 없습니다.
                else if(total > n) {
                    break;
                }
            }
        }
        
        return answer;
    }
}