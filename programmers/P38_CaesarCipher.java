class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i = 0 ; i < s.length() ; i++) {
            // 대문자일 경우
            if(Character.isUpperCase(s.charAt(i))) {
                // n만큼 밀었을 때 Z를 넘어설 경우
                if(s.charAt(i)+n > 90) {
                    answer += Character.toString(s.charAt(i)+n-26);
                }
                else {
                    answer += Character.toString(s.charAt(i)+n);    
                }
                
            }
            // 소문자일 경우
            else if(Character.isLowerCase(s.charAt(i))) {
                // n만큼 밀었을 때 z를 넘어설 경우
                if(s.charAt(i)+n > 122) {
                    answer += Character.toString(s.charAt(i)+n-26);
                }
                else {
                    answer += Character.toString(s.charAt(i)+n);    
                }
            }
            // 그 외(ex. 공백)
            else {
               answer += s.charAt(i);
            }
        }
        
        return answer;
    }
}