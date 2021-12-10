// 메뉴 리뉴얼
// https://programmers.co.kr/learn/courses/30/lessons/72411?language=java

import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> resultList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int r : course) {
            for(String order : orders) {
                if(order.length() >= r) {
                    // 주문을 알파벳 순으로 정렬
                    order = getSortedOrder(order);
                    // 주문에서 r개를 뽑아 조합(nCr)을 만들어 map에 저장
                    addCombtoMap(order, "", 0, r, map);
                }
            }
            // map에서 최대값을 지니는 조합을 뽑아내 result에 저장
            addResult(map, resultList);
            map.clear();
        }
        // result를 오름차순 정렬
        Collections.sort(resultList);
        answer = resultList.toArray(answer);
        
        return answer;
    }
    
    // 받은 주문을 알파벳 순으로 정렬해주는 함수
    public String getSortedOrder(String order) {
        char[] charArray = order.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    // 받은 주문에서 r개의 단품 메뉴를 뽑아내는 조합을 구해 map에 저장하는 함수
    public void addCombtoMap(String order, String result, int index, int r, HashMap map) {
        // r개의 단품 메뉴를 뽑았을 경우
        if(result.length() == r) {
            // 이미 저장되있는 조합이라면 value를 1 증가
            if(map.containsKey(result)) {
                map.replace(result, (int) map.get(result)+1);
            }
            // 저장되어있지 않은 조합이라면 새로 추가
            else {
                map.put(result, 1);
            }
        }
        // r개 뽑히지 않은 조합
        else if(index == order.length()) {
            
        }
        else {
            // 받은 주문의 index번째에 해당하는 메뉴를 넣고 다음 순서로 이동
            addCombtoMap(order, result+String.valueOf(order.charAt(index)), index+1, r, map);
            // 받은 주문의 index번째에 해당하는 메뉴를 넣지 않고 다음 순서로 이동
            addCombtoMap(order, result, index+1, r, map);
        }
    }

    // 단품 메뉴 조합들이 담긴 map에서 주문이 가장 많은 조합을 resultList에 저장
    public void addResult(HashMap map, ArrayList resultList) {
        int max = 0;
        HashMap<String, Integer> thismap = map;
        
        // 가장 많은 조합의 주문 횟수(value)를 구함
        for(Integer value : thismap.values()) {
            if(value > max) {
                max = value;
            }
        }
        
        // 문제에서 최소 2명 이상의 손님에게서 주문된 조합만 코스요리 후보에 들어가므로
        if(max > 1) {
            // 가장 많은 주문 횟수(max)를 가진 조합들을 result에 저장
            for(Map.Entry<String, Integer> entry : thismap.entrySet()) {
                if(entry.getValue() == max) {
                    resultList.add(entry.getKey());
                }
            }
        }
    }
}