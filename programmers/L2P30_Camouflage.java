// 위장
// https://programmers.co.kr/learn/courses/30/lessons/42578?language=java

import  java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        // 의상의 종류와 개수를 저장하는 HashMap입니다.
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes) {
            // 의상의 종류에 해당하는 개수를 가져와 +1 해줍니다.
            // 처음 저장하는 의상의 종류는 0을 가져와 +1 합니다.
            map.put(cloth[1], map.getOrDefault(cloth[1], 0)+1);
        }
        
        // 조합의 개수는 각 의상의 종류별 개수를 곱하는 것입니다.
        // 한 종류의 의상을 입지 않을 수 있기 때문에 개수에 1을 더해준 값을 곱합니다.
        for(int i : map.values()) {
            answer *= i+1;
        }
        
        // 의상을 아무것도 입지 않는 경우를 제외해야 하므로 1을 빼줍니다.
        return answer-1;
    }
}


// DFS 알고리즘을 이용하여 조합까지 찾는 solution입니다. (조합의 개수를 찾는데 있어서 시간초과)
// 문제에서는 조합의 개수만을 요구하므로 불필요한 시간이 투자되어 시간초과가 됩니다.
/*
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashSet<String> set = new HashSet<>();

        answer = getComb(clothes, set, 0, "");

        return answer;
    }
    
    public int getComb(String[][] clothes, HashSet<String> set, int depth, String result) {
        int count = 0;
        HashSet<String> usedClothesSet = new HashSet<>(set);
        
        if(depth == clothes.length) {
            if(!result.equals("")) {
                count++;
            }
        }
        else {
            count += getComb(clothes, usedClothesSet, depth+1, result);

            if(usedClothesSet.add(clothes[depth][1])) {
                count += getComb(clothes, usedClothesSet, depth+1, result+clothes[depth][0]);
            }
        }
        
        return count;
    }
}
*/