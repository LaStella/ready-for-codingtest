// 튜플
// https://programmers.co.kr/learn/courses/30/lessons/64065?language=java#

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        // 정답을 저장할 LinkedHashSet입니다. 저장된 순서를 유지하며 중복을 허용하지 않습니다.
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        
        // 주어진 문자열의 앞, 뒤 2자리씩 괄호들을 제거합니다.
        s = s.substring(2, s.length()-2);
        // 문자열 "},{" 을 기준으로 잘라 배열에 저장합니다.
        String[] sArray = s.split("\\},\\{");
        
        // 배열의 원소들의 길이를 비교하여 정렬합니다.
        // 예를 들어 튜플이 (2, 1, 3, 4)인 경우 이는
        // {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}} 와 같이 표현되며 집합의 원소의 순서는 상관이 없습니다.
        // 즉 원소가 적은 집합부터 lhs에 원소를 넣게되면 튜플을 구할 수 있습니다.
        // 문자열의 길이가 길수록 원소가 많습니다.
        Arrays.sort(sArray, (a, b) ->Integer.compare(a.length(), b.length()));
        
        for(String set : sArray) {
            for(String element : set.split(",")) {
                lhs.add(Integer.parseInt(element));
            }
        }
        
        answer = lhs.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}