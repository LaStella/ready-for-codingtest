// 압축
// https://programmers.co.kr/learn/courses/30/lessons/17684?language=java

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, Integer> dictionary = new HashMap<>();
        int index = 1;
        
        // 대문자 A 부터 Z까지 사전에 추가해줍니다.
        for(char c = 'A' ; c <= 'Z' ; c++) {
            dictionary.put(Character.toString(c), index++);
        }
        
        String key = "";
        for(int i = 0 ; i < msg.length() ; i++) {
            // 사전에 검색할 단어 key를 한글자씩 늘려줍니다.
            key += Character.toString(msg.charAt(i));
            // 주어진 문자열 msg의 끝에 도달하면 중단합니다.
            if(i == msg.length()-1) {
                list.add(dictionary.get(key));
                break;
            }
            // 검색할 단어 key에 msg의 다음 글자를 붙인 단어가 사전에 없으면 사전에 추가합니다.
            // key에 해당하는 Index를 list에 추가하고, key는 공백으로 초기화해줍니다.
            if(dictionary.containsKey(key+msg.charAt(i+1))) continue;
            else {
                list.add(dictionary.get(key));
                dictionary.put(key+msg.charAt(i+1), index++);
                key = "";
            }
        }
        
        // 저장된 Index list를 배열로 바꿔줍니다.
        answer = list.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}