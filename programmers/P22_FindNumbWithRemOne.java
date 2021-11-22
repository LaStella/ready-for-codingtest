class Solution {
    public int solution(int n) {
        int answer = 0;
        
        //자연수 n에 대하여 x로 나눈 나머지가 1이 되도록하는
        //가장 작은 자연수 x를 return하라.
        //제한사항 3 ≤ n ≤ 1,000,000

        //n이 3부터 시작하므로 제수(나누는 수, Divisor)는 2부터 시작한다.
        for(int i = 2 ; i < n ; i++) {
            if(n % i == 1) {
                answer = i;
                break;
            }
        }  
        
        return answer;
    }
}