// 가사 검색
// https://programmers.co.kr/learn/courses/30/lessons/60060?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        Arrays.fill(answer, words.length);
        for(int i = 0 ; i < queries.length ; i++) {
            int length = queries[i].length();
            for(int j = 0 ; j < words.length ; j++) {
                if(words[j].length() == length) {
                    for(int k = 0 ; k < length ; k++) {
                        if(queries[i].charAt(k) == '?') {
                            continue;
                        }
                        if(queries[i].charAt(k) != words[j].charAt(k)) {
                            answer[i]--;
                            break;
                        }
                    }
                }
                else {
                    answer[i]--;
                }
            }
        }
        
        return answer;
    }
}

/*
1.
쿼리의 단어와 키워드를 비교
- 같은 길이인지 확인
- 같은 길이라면, 서로 같은 위치에 같은 문자인지 확인
*/