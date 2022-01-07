//영어 끝말잇기
// https://programmers.co.kr/learn/courses/30/lessons/12981?language=java

import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        HashSet<String> set = new HashSet<>();
        
        String prev = words[0];
        set.add(words[0]);
        
        for(int i = 1 ; i < words.length ; i++) {
            // 영단어의 시작귀 끝이 일치하는지 확인합니다.
            if(prev.charAt(prev.length()-1) != words[i].charAt(0)) {
                answer[0] = i%n+1;
                answer[1] = i/n+1;
                break;
            }
            // 이미 말한 영단어인지 확인합니다.
            if(!set.add(words[i])) {
                answer[0] = i%n+1;
                answer[1] = i/n+1;
                break;
            }
            prev = words[i];
        }
        
        return answer;
    }
}