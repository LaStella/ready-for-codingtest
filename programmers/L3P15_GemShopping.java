// 보석 쇼핑
// https://programmers.co.kr/learn/courses/30/lessons/67258?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        // 보석의 종류와 보석이 있는 진열대 번호 set을 map에 저장합니다.
        HashMap<String, TreeSet<Integer>> map = new HashMap<>();
        
        // TreeSet에 진열대 번호를 저장하기 때문에 자동정렬됩니다.
        for(int i = 0 ; i < gems.length ; i++) {
            TreeSet<Integer> set = map.getOrDefault(gems[i], new TreeSet<Integer>());
            set.add(i);
            map.put(gems[i], set);
        }
        
        // map에 저장되어 있는 각 보석의 맨 앞 index를 뽑습니다.
        TreeSet<Integer> range = new TreeSet<>();
        for(String key : map.keySet()) {
            range.add(map.get(key).pollFirst());
        }
        // 시작 진열대 번호와 끝 진열대 번호는 각각 range의 최소값과 최대값이 됩니다.
        // 0번부터 시작하기 때문에 1을 더해줍니다.
        answer[0] = range.first()+1;
        answer[1] = range.last()+1;
        int length = range.last()-range.first()+1;

        // 최소 쇼핑 구간은 보석의 종류의 갯수입니다.
        while(length != map.size()) {
            // 현재 선택된 보석들 중 제일 앞 진열대에 있는 보석을 제외합니다.
            int minIndex = range.pollFirst();
            // 제외된 보석의 다음 진열대 번호를 가져올 수 없을 경우 중단합니다.
            if(map.get(gems[minIndex]).size() == 0) {
                break;
            }
            // 제외된 보석의 다음 진열대 번호를 선택합니다.
            else {
                range.add(map.get(gems[minIndex]).pollFirst());
                // 기존의 쇼핑 구간보다 짧을 경우 쇼핑 구간과 시작 진열대 번호, 끝 진열대 번호를 갱신합니다.
                if(length > range.last()-range.first()+1) {
                    answer[0] = range.first()+1;
                    answer[1] = range.last()+1;
                    length = range.last()-range.first()+1;    
                }
            }
        }
        
        return answer;
    }
}

/*
각 보석의 첫 인덱스를 뽑음
뽑힌 인덱스중 최소와 최대를 뽑음
뽑힌 최소 최대 인덱스와 정답을 비교
최소인덱스의 보석을 다음 인덱스로 옮김
*/