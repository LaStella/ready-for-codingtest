// 압축할 문자열 s가 매개변수로 주어질 때, 1개 이상 단위로 문자열을 잘라 압축하여
// 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
// 제한사항
// s의 길이는 1 이상 1,000 이하입니다.
// s는 알파벳 소문자로만 이루어져 있습니다.
// 압축예시
// "aabbaccc" -> "2a2ba3c" 1개 단위로 잘라 압축
// "ababcdcdababcdcd" -> "2ab2cd2ab2cd" 2개 단위로 잘라 압축

class Solution {
    public int solution(String s) {
        int answer = getCompressedLength(s, 1);
        
        for(int i = 2 ; i <= s.length()/2 ; i++) {
            if(answer > getCompressedLength(s, i)) {
                answer = getCompressedLength(s, i);
            }
        }  
        
        return answer;
    }
    
    public int getCompressedLength(String s, int n) {
        // n 간격마다 특수문자 '-'를 삽입
        String temp = s.replaceAll("\\w{"+n+"}", "$0-");
        // '-'를 기준으로 문자열을 쪼갬
        String[] strArray = temp.split("-");
        // 연속으로 중복된 문자열의 갯수 count
        int count = 1;
        
        for(int i = 1 ; i < strArray.length ; i++) {
            if(strArray[i].equals(strArray[i-count])) {
                count++;
                strArray[i] = "";
                // 만약 배열 마지막값을 확인할 경우
                if(i == strArray.length-1) {
                    strArray[i-count+1] = count + strArray[i-count+1];
                }
            }
            else {
                if(count != 1) {
                    strArray[i-count] = count + strArray[i-count];
                }
                count = 1;
            }
        }
        
        temp = String.join("", strArray);

        return temp.length();
    }
}