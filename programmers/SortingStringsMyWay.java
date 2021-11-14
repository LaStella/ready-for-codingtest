import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        String subStr1;
        String subStr2;
        
        
        // n번째 글자를 기준으로 앞, 뒤로 자른 후
        // (n번째 글자 + 앞 문자열 + 뒷 문자열)을 저장
        // 이를 정렬 후 다시 원상 복구
        for(int i = 0 ; i < strings.length ; i++) {
            subStr1 = strings[i].substring(0, n);
            subStr2 = strings[i].substring(n+1);
            strings[i] = strings[i].charAt(n)+subStr1+subStr2;
        }
        
        Arrays.sort(strings);
        
        for(int i = 0 ; i < strings.length ; i++) {
            subStr1 = strings[i].substring(1, n+1);
            subStr2 = strings[i].substring(n+1);
            strings[i] = subStr1+strings[i].charAt(0)+subStr2;
        }
        
        answer = strings;
        
        
        return answer;
    }
}