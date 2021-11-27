class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int length = phone_number.length(); // 너무 길어서 짧은 변수명으로 대체
        
        answer = "*".repeat(length-4) + phone_number.substring(length-4, length);
        
        return answer;
    }
}