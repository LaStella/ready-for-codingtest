// 브라이언의 고민
// https://programmers.co.kr/learn/courses/30/lessons/1830?language=java

import java.util.*;

class Solution {
    public String solution(String sentence) {
        String answer = "";
        LinkedHashMap<Character, ArrayList<Integer>> map = new LinkedHashMap<>();
        ArrayList<String> answerList = new ArrayList<>();
        
        for(int i = 0 ; i < sentence.length() ; i++) {
            Character key = sentence.charAt(i);
            if(Character.isLowerCase(key)) {
                // ArrayList<Integer> list = map.getOrDefault(key, new ArrayList<Integer>());
                // list.add(i);
                // map.put(key, list);
                if(!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(i);
            }
        }
        
        int sentenceIndex = 0;
        int prevSIndex = -1;
        int prevEIndex = -1;
        int prevWSIndex = -1;
        int prevWEIndex = -1;
        int prevRule = 0;
        String prevWord = "";
        
        for(char key : map.keySet()) {
            ArrayList<Integer> list = map.get(key);
            int count = list.size();
            int sIndex = list.get(0);
            int eIndex = list.get(count-1);
            int wordSIndex, wordEIndex;
            int rule = 0;
            String word = "";
            
            // 소문자의 개수에 따라 규칙1과 규칙2를 구분합니다.
            if(count == 2) {
                int interval = eIndex-sIndex;
                // 간격이 2일 경우 규칙1과 규칙2 둘 다 가능
                if(interval == 2 && prevSIndex < sIndex && prevEIndex > eIndex) {
                    // 이전 규칙안에 존재할 수 있는 규칙은 1번 규칙만 가능 (2번규칙 안에 1번규칙 존재가능, 1번규칙안에 2번규칙 존재불가능)
                    rule = 1;
                    // System.out.println("1"+getWord(sIndex, eIndex, sentence));
                }
                // 간격이 2보다 크다면 규칙2만 가능
                else if(interval >= 2) {
                    rule = 2;
                }
                // 간격이 2보다 작다면 소문자가 연속으로 나온 것이므로 불가능(규칙1,2모두 사이에 단어가 들어가기 때문)
                else {
                    // invalid
                    return answer = "invalid";
                }
            }
            
            // 소문자가 2개가 아니므로 규칙1만 가능합니다.
            else if(count == 1 || count >= 3) {
                // 소문자사이의 간격을 확인합니다.
                for(int i = 0 ; i < count-1 ; i++) {
                    if(list.get(i+1) - list.get(i) != 2) {
                        // invalid
                        // 간격이 2가 아니라면 규칙1이 될 수 없으므로
                        return answer = "invalid";
                    }
                }
                rule = 1;
            }
            
            // 규칙에 따라 규칙 적용 전의 단어로 가져옵니다.
            // 규칙1은 첫번째 소문자보다 1작고 마지막 소문자보다 1큰 범위가 단어의 범위가 됩니다.
            // System.out.println(rule);
            if(rule == 1) {
                wordSIndex = sIndex-1;
                wordEIndex = eIndex+1;
                // System.out.println(wordSIndex);
                // System.out.println(wordEIndex);
                if(prevWSIndex < wordSIndex && prevWEIndex > wordEIndex) {
                    if(prevRule == 2) {
                        // 같은 단어인지 확인
                        word = getWord(wordSIndex, wordEIndex, sentence);
                        if(!prevWord.equals(word)) {
                            return "invalid";
                        }
                        else {
                            continue;
                        }
                    }
                    else {
                        return "invalid";
                    }
                }
                else if(prevWEIndex < wordSIndex) {
                    word = getWord(wordSIndex, wordEIndex, sentence);
                }
                else {
                    return "invalid";
                }
            }
            // 규칙2은 첫번째 소문자와 마지막 소문자가 단어의 범위가 됩니다.
            else {
                wordSIndex = sIndex;
                wordEIndex = eIndex;
                // System.out.println(wordSIndex);
                // System.out.println(wordEIndex);
                if(prevWSIndex < wordSIndex && prevWEIndex > wordEIndex) {
                    return "invalid";
                }
                else if(prevWEIndex < wordSIndex) {
                    word = getWord(wordSIndex, wordEIndex, sentence);
                }
                else {
                    return "invalid";
                }
                
            }
            
            if(sentenceIndex < wordSIndex) {
                // System.out.println(sentenceIndex);
                // System.out.println(sIndex);
                String remainWord = getWord(sentenceIndex, sIndex-1, sentence);
                if(!remainWord.equals("")) {
                    // System.out.println(remainWord);
                    answerList.add(remainWord);
                }
                // sentence.substring(sentenceIndex, sIndex);
            }
            answerList.add(word);
            sentenceIndex = wordEIndex+1;
            prevSIndex = sIndex;
            prevEIndex = eIndex;
            prevWSIndex = wordSIndex;
            prevWEIndex = wordEIndex;
            prevRule = rule;
            prevWord = word;
        }
        if(sentenceIndex < sentence.length()) {
            String remainWord = getWord(sentenceIndex, sentence.length()-1, sentence);
            if(!remainWord.equals("")) {
                // System.out.println(remainWord);
                answerList.add(remainWord);
            }
        }
        
        // System.out.println(map);
        // System.out.println(String.join(" ", answerList));
        answer = String.join(" ", answerList);
        
        return answer;
    }
    
    public String getWord(int wordSIndex, int wordEIndex, String sentence) {
        String result = "";
        
        result = sentence.substring(wordSIndex, wordEIndex+1).replaceAll("[a-z]", "");
        
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

AxAxAxAoBoBoB
규칙x의 범위 1~5
규칙x의 단어 범위 0~6
규칙o의 범위 7~11
규칙o의 단어 범위 6~12


CCCoAAAoDDDBpBpB
CCC AAA DDD BBB
sentenceIndex = 0
sIndex = 3
eIndex = 7
sentenceIndex = eIndex+1 = 8
sIndex = 11






bAaAb
*/