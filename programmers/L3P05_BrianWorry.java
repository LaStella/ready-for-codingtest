// 브라이언의 고민
// https://programmers.co.kr/learn/courses/30/lessons/1830?language=java

import java.util.*;

class Solution {
    public String solution(String sentence) {
        String answer = "";
        LinkedHashMap<Character, ArrayList<Integer>> map = new LinkedHashMap<>();
        for(int i = 0 ; i < sentence.length() ; i++) {
            Character key = sentence.charAt(i);
            if(Character.isLowerCase(key)) {
                ArrayList<Integer> list = map.getOrDefault(key, new ArrayList<Integer>());
                list.add(i);
                map.put(key, list);
            }
        }
        
        System.out.println(map);
        return answer;
    }
}

/*

a1b1b1ac2d2d2c

111 222



*/