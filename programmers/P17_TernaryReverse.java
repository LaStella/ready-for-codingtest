class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String strAnswer = "";
        while(n > 0) {
            strAnswer += n%3;
            n /= 3;
        }
        
        return answer = Integer.parseInt(strAnswer, 3);
    }
}