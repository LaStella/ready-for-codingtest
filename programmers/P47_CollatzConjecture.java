class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = num;   // int형으로 사용할 경우 오버플로우 발생위험이 있음
        
        while(n != 1) {
            if(answer == 500) {
                answer = -1;
                break;
            }
            // 삼항연산자
            // n = (n % 2 == 0) ? n / 2 : n * 3 + 1;
            if(n % 2 == 0) {
                n /= 2;
            }
            else {
                n = n * 3 + 1;
            }
            answer++;
        }
        
        return answer;
    }
}