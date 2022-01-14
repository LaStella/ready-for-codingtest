// 이진 변환 반복하기
// https://programmers.co.kr/learn/courses/30/lessons/70129?language=java

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        // s가 1이 될때까지 반복합니다.
        while(!s.equals("1")) {
            s = removeZero(s, answer);
            s = convertBinary(s);
        }
        // 함수를 만들지 않는 코드
        // while(!s.equals("1")) {
        //     int length = s.length();
        //     s = s.replace("0", "");
        //     answer[0]++;
        //     answer[1] += length-s.length();
        //     s = Integer.toBinaryString(s.length());
        // }
        return answer;
    }
    
    // 주어진 문자열 s에서 0을 제거하는 함수입니다.
    public String removeZero(String s, int[] answer) {
        String removedS = s.replace("0", "");
        // 제거된 0의 개수 = 제거되기 전 문자열의 길이 - 제거된 후 문자열의 길이
        int count = s.length()-removedS.length();
        answer[0]++;
        answer[1] += count;
        return removedS;
    }
    // 주어진 문자열 s를 s의 길이를 이진 변환한 문자열로 return하는 함수입니다.
    public String convertBinary(String s) {
        return Integer.toBinaryString(s.length());
    }
}