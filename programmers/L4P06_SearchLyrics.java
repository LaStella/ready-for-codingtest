// 가사 검색
// https://programmers.co.kr/learn/courses/30/lessons/60060?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        Arrays.fill(answer, words.length);
        for(int i = 0 ; i < queries.length ; i++) {
            int length = queries[i].length();
            String removed_wildcard = queries[i].replaceAll("\\?", "");
            int count = length - removed_wildcard.length();
            boolean isPrefix = queries[i].indexOf('?') == 0 ? true : false;
            
            for(int j = 0 ; j < words.length ; j++) {
                if(words[j].length() == length) {
                    if(isPrefix) {
                        if(!removed_wildcard.equals(words[j].substring(count, length))) {
                            answer[i]--;
                        }
                    }
                    else {
                        if(!removed_wildcard.equals(words[j].substring(0, length-count))) {
                            answer[i]--;
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
시간초과
-----------------------------
2.
같은 위치에 같은 문자를 확인할 필요없이 와일드카드 문자를 제외한 문자열과 그에 맞는 위치의 문자열을 비교
와일드카드는 앞 또는 뒤에서부터 존재, 중간에 끼는 경우가 없으므로
- 쿼리와 '?'을 지운 쿼리의 길이를 비교하여 와일드카드의 개수를 셉니다.
- '?'가 처음으로 나오는 인덱스를 통해 접두인지 접미인지 구분합니다.
- words의 단어들을 와일드카드를 지운 문자열과 같은 위치의 문자열을 자르고 비교합니다.
시간초과
-----------------------------
3.
*/