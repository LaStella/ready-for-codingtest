// 순위 검색
// https://programmers.co.kr/learn/courses/30/lessons/72412?language=java#

import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        // info의 4가지의 정보를 Key로, 코딩 테스트 점수를 ArrayList에 담아 Value로 저장합니다.
        // ex) Key = javabackendjuniorpizza Value = [150]
        HashMap<String, ArrayList> map = new HashMap<>();
        
        // 주어진 한 사람의 정보에서 만들 수 있는 모든 키에 대하여 값을 저장합니다.
        // ex) Key = javabackendjuniorpizza, -backendjuniorpizza, ---juniorpizza ...
        for(String s : info) {
            String[] infoArray = s.split(" ");
            dfsKey(infoArray, "", 0, map);
        }
        
        // map의 각 Key에 해당하는 점수 ArrayList를 오름차순으로 정렬합니다.
        Set<String> keySet = map.keySet();
        for(String key : keySet) {
            Collections.sort(map.get(key));    
        }
        
        // 주어진 query를 map의 Key와 동일한 형식으로 변환하여 해당하는 query의 점수 ArrayList를 가져옵니다.
        int index = 0;
        for(String s : query) {
            String[] queryArray = s.replaceAll(" and ", "").split(" ");
            String key = queryArray[0];
            int score = Integer.parseInt(queryArray[1]);
            
            // map에서 주어진 query를 Key로 사용하여 가져온 점수 ArrayList에서 이진탐색을 통해 score점수 이상인 지원자의 수를 계산합니다.
            // 주어진 query가 map에 존재하지 않는다면 해당하는 지원자는 0명입니다.
            if(map.containsKey(key)) {
                answer[index++] = binarySearch(map.get(key), score);
            }
            else {
                answer[index++] = 0;
            }
        }
        
        return answer;
    }
    
    // 깊이 우선 탐색 알고리즘을 사용하여 Key를 만들어 저장하는 함수입니다.
    public void dfsKey(String[] s, String key, int depth, HashMap<String, ArrayList> map) {
        // 정보는 총 4개 이므로 깊이가 4일 때 저장합니다.
        if(depth == 4) {
            int score = Integer.parseInt(s[4]);
            // 이미 존재하는 Key라면 해당 Key에 해당하는 ArrayList를 가져와 점수를 저장합니다.
            if(map.containsKey(key)) {
                map.get(key).add(score);
            }
            // 새로운 Key라면 점수를 저장하는 ArrayList를 생성하여 점수를 저장한 후 Map에 저장합니다.
            else {
                ArrayList<Integer> scoreList = new ArrayList<>();
                scoreList.add(score);
                map.put(key, scoreList);
            }
        }
        // 4개의 정보가 입력될 때까지 재귀호출합니다.
        else {
            dfsKey(s, key+"-", depth+1, map);
            dfsKey(s, key+s[depth], depth+1, map);
        }
    }
    
    // 이진탐색 알고리즘을 사용하여 map에서 해당하는 Key에 대한 ArrayList로부터 score 점수 이상의 값의 개수를 return하는 함수입니다.
    public int binarySearch(ArrayList<Integer> list, int score) {
        int left = 0;
        int right = list.size()-1;
        
        while(left <= right) {
            int mid = (left + right)/2;
            if(list.get(mid) < score) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }
        
        return list.size()-left;
    }
}