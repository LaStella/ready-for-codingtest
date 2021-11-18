class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1 ; i <= n ; i++) {
            //1은 소수가 아니므로 넘어감
            if(i == 1) {
                continue;
            }
            
            answer++;
            
            for(int j = 2 ; j <= Math.sqrt(i) ; j++) {
                if(i % j == 0) {
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
}