// JadenCase 문자열 만들기
// https://programmers.co.kr/learn/courses/30/lessons/12951?language=java#

class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] sArray = s.split(" ");

        for(int i = 0 ; i < sArray.length ; i++) {
            // 공백은 무시합니다.
            if(sArray[i].equals("")) {
                continue;
            }
            else {
                // 문자가 하나라면 그대로 대문자로 변환합니다.
                if(sArray[i].length() == 1) {
                    sArray[i] = sArray[i].toUpperCase();
                }
                // 첫 글자를 대문자로, 나머지는 소문자로 바꾸어 더합니다.
                else {
                    sArray[i] = sArray[i].substring(0, 1).toUpperCase() + sArray[i].substring(1, sArray[i].length()).toLowerCase();    
                }
            }
        }
        
        answer = String.join(" ", sArray);
        // 주어진 문자열 s에서 끝에서부터 잘린 공백을 붙여줍니다.
        answer += " ".repeat(s.length()-answer.length());
        
        return answer;
    }
}