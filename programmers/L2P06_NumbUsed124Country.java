class Solution {
    public String solution(int n) {
        String answer = "";
        
        while(n > 0) {
            if(n % 3 == 0) {
                answer = "4" + answer;
                n -= 3; // 자릿수가 늘어나는 것을 방지
            }
            else {
                answer = Integer.toString(n % 3) + answer;
            }
            n /= 3;
        }
        return answer;
    }
}