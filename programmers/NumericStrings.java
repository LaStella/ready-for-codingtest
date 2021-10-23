class Solution {
    public int solution(String s) {
        int answer = 0;
        
        
        String[] digit_arry = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i = 0; i < digit_arry.length; i++) {
            s = s.replaceAll(digit_arry[i], Integer.toString(i));
        }
        
        answer = Integer.parseInt(s);
        
        
        return answer;
    }
}