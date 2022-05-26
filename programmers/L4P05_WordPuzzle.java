// 단어 퍼즐
// https://programmers.co.kr/learn/courses/30/lessons/12983?language=java

import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int answer = 0;
        HashSet<String> set = new HashSet<>();
        
        for(String str : strs) {
            set.add(str);
        }
        
        answer = getMin(t, set);
        
        return answer;
    }
    
    // 주어진 단어t를 두 단어로 쪼개어 사용하는 단어 조각 개수의 최소값을 구하는 함수입니다.
    public int getMin(String t, HashSet<String> set) {
        // 빈 단어인 경우 단어 조각을 찾을 필요가 없으므로 0을 반환합니다.
        if(t.equals("")) {
            return 0;
        }
        // 초기값은 제한사항에 따라 결과로 나올 수 없는 101로 합니다. (단어 조각은 최대 100개이므로)
        int result = 101;
        
        // 주어진 단어 t를 최소 1개부터 t의 길이 만큼까지 두 단어로 분리합니다. (ex. t="abc", f_word = "abc", b_word = "")
        for(int i = 1 ; i <= t.length(); i++) {
            String f_word = t.substring(0, i);
            // 분리한 앞단어의 단어 조각이 존재하는 경우에만 뒷단어의 조각 개수를 구합니다.
            if(set.contains(f_word)) {
                String b_word = t.substring(i, t.length());
                int b_word_result = getMin(b_word, set);
                // 뒷단어의 단어 조각이 존재하지 않으면 넘어갑니다.
                if(b_word_result == -1) continue;
                
                // 분리한 두 단어의 총 조각 개수를 비교하여 최소값을 저장합니다.
                result = Math.min(result, 1+b_word_result);
            }
        }    
        
        // result가 초기값 그대로라면 주어진 단어 t를 두 단어로 분리하지 못하였으므로 -1을 반환합니다.
        return result == 101 ? -1 : result;
    }
}

/*
단어를 쪼개어 확인하는 방법
주어진 단어 t의 길이가 n이라 할때
front 0~mid
back mid+1~end
front 단어 조각에 들어있으면 분리 가능하며 없다면 분리 불가능
*/