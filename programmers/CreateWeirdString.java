class Solution {
    public String solution(String s) {
        String answer = "";
        String[] strArry = s.split(" ");

        for(int i = 0 ; i < strArry.length ; i++) {
            String temp = "";
            for(int j = 0 ; j < strArry[i].length() ; j++) {
                if(j % 2 != 0) {
                    temp += Character.toLowerCase(strArry[i].charAt(j));
                }
                else {
                    temp += Character.toUpperCase(strArry[i].charAt(j));
                }
            }
            strArry[i] = temp;
        }
        
        answer = String.join(" ", strArry);
        
        if(answer.length() != s.length()) {
            answer += s.substring(answer.length());
        }
        
        return answer;
    }
}