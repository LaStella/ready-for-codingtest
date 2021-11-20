import java.lang.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // n = p * q 이므로 제곱근n까지 p * q 형식의 수를 합한것이 결과가 된다.
        for(int i = 1 ; i < Math.sqrt(n) ; i++) {
            if(n % i == 0) {
                answer += i;
                answer += n/i;
            }
        }
        
        // 약수가 제곱근일 경우
        if(n % Math.sqrt(n) == 0) {
            answer += Math.sqrt(n);
        }
        
        return answer;
    }
}