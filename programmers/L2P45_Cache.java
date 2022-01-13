// [1차]캐시
// https://programmers.co.kr/learn/courses/30/lessons/17680?language=java

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 캐시를 나타낼 cache와 중복허용을 하지 않기위한 cacheSet입니다.
        Queue<String> cache = new LinkedList<>();
        HashSet<String> cacheSet = new HashSet<>();
        
        for(String city : cities) {
            // 모든 도시 이름을 소문자로 바꿔줍니다.
            city = city.toLowerCase();
            // 캐시에 없는 도시라면 해당 도시를 캐시에 추가하고
            // 추가한 이후 캐시 크기가 초과된다면 캐시에 가장 먼저 저장된 도시(Queue의 front)를 제거합니다.
            if(cacheSet.add(city)) {
                cache.add(city);
                if(cacheSet.size() > cacheSize) {
                    cacheSet.remove(cache.poll());
                }
                answer += 5;
            }
            // 캐시에 있는 도시라면 해당 도시를 캐시에서 가장 뒤(Queue의 back)로 옮겨줍니다.(제거 후 다시 삽입)
            else {
                cache.remove(city);
                cache.add(city);
                answer++;
            }
        } 
        return answer;
    }
}