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
        
        int prevSIndex = -1;
        int prevEIndex = -1;
        String prevWord = "";
        
        for(char key : map.keySet()) {
            ArrayList<Integer> list = map.get(key);
            int count = list.size();
            int sIndex = list.get(0);
            int eIndex = list.get(count-1);
            String word = "";
            
            if(count == 2) {
                int interval = eIndex-sIndex;
                // 간격이 2일 경우 규칙1과 규칙2 둘 다 가능
                if(interval == 2) {
                    if(prevSIndex < sIndex && prevEIndex > eIndex) {
                        System.out.println(getWord(1, sIndex, eIndex, sentence));
                        // rule 1
                        // 이전 규칙안에 존재할 수 있는 규칙은 1번 규칙만 가능 (2번규칙 안에 1번규칙 존재가능, 1번규칙안에 2번규칙 존재불가능)      
                    }
                    else if(prevEIndex < sIndex) {
                        System.out.println(getWord(2, sIndex, eIndex, sentence));
                        // rule 2
                        // 이전 규칙과 겹치지않는 범위라면 규칙2를 우선배정
                    }
                    else {
                        // invalid
                    }
                }
                // 간격이 2보다 크다면 규칙2만 가능
                else if(interval > 2) {
                    if(prevSIndex < sIndex && prevEIndex > eIndex) {
                        // invalid
                        // 이전 규칙안에 존재할 수 있는 규칙은 1번 규칙만 가능 (2번규칙 안에 1번규칙 존재가능, 1번규칙안에 2번규칙 존재불가능)
                    }
                    else if(prevEIndex < eIndex) {
                        System.out.println(getWord(2, sIndex, eIndex, sentence));
                        // rule 2
                    }
                    else {
                        // invalid
                    }
                    // rule 2
                }
                // 간격이 2보다 작다면 소문자가 연속으로 나온 것이므로 불가능(규칙1,2모두 사이에 단어가 들어가기 때문)
                else {
                    // invalid
                }
            }
            // 규칙1만 가능
            else if(count == 1 || count >= 3) {
                for(int i = 0 ; i < count-1 ; i++) {
                    if(list.get(i+1) - list.get(i) != 2) {
                        // invalid
                        // 간격이 2가 아니라면 규칙1이 될 수 없으므로
                    }
                }
                // rule 1
                if(prevSIndex < sIndex && prevEIndex > eIndex) {
                    System.out.println(getWord(1, sIndex, eIndex, sentence));
                    // 이전 규칙 안에 포함되는 경우
                }
                else if(prevEIndex < eIndex) {
                    System.out.println(getWord(1, sIndex, eIndex, sentence));
                    // 이전 규칙과 겹치지 않는 경우
                }
                else {
                    // invalid
                }
                // rule 1
            }
            prevSIndex = sIndex;
            prevEIndex = eIndex;
        }
        
        System.out.println(map);
        
        
        // String test = "01234567";
        // System.out.println(test.substring(0, 6));
        // test = "aaaaAAAA";
        // System.out.println(test.replaceAll("[a-z]", ""));
        
        return answer;
    }
    
    public String getWord(int rule, int sIndex, int eIndex, String sentence) {
        String result = "";
        // ex) AaAaA 시작인덱스-1,  끝인덱스+1이 단어의 범위입니다.
        if(rule == 1) {
            result = sentence.substring(sIndex-1, eIndex+2).replaceAll("[a-z]", "");
        }
        // 시작인덱스+1, 끝인덱스-1이 단어의 범위입니다.
        else {
            result = sentence.substring(sIndex+1, eIndex).replaceAll("[a-z]", "");
        }
        
        return result;
    }
}

/*

a1b1b1ac2d2d2c

111 222
규칙1 = count 제한없음, 글자사이 간격 = 2고정
규칙2 = count = 2, 글자사이 간격 제한없음
규칙1과 2는 한 단어에 같이쓰일 수 있음(규칙2 안에 규칙1 존재, 규칙1안에 규칙2이 존재는 불가능)
규칙1의 양 끝 인덱스안에 규칙2의 양 끝 인덱스가 포함될경우 가능

count=2,글자사이간격=2 일경우 규칙1인지 규칙2인지 구분해야함
이전 규칙의 인덱스 안에 존재하는 규칙일 경우 규칙1
이전 규칙의 인덱스 밖에 존재하는 규칙일 경우 규칙1,2 둘다 가능

소문자는 순서대로 입력하므로 먼저 입력된 소문자가 규칙2를 우선적으로 배정(규칙2안에 규칙1이 존재가능하므로)


bAaAb
*/