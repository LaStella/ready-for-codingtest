// 후보키
// https://programmers.co.kr/learn/courses/30/lessons/42890?language=java

import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int answer = 0;
        
        // 후보키를 저장할 공간입니다.
        List<String> list = new ArrayList<>();
        
        getDfsKey("", 0, relation[0].length, relation, list);
        
        answer = list.size();
        
        return answer;
    }
    
    // 깊이 우선 탐색알고리즘을 사용하여 후보키로 사용할 속성을 뽑아 유일성과 최소성을 확인하여 list에 저장하는 함수입니다.
    // index = 속성의 index를 저장, depth = dfs알고리즘에서의 깊이, size = 속성의 총 개수
    public void getDfsKey(String index, int depth, int size, String[][] relation, List<String> list) {
        if(depth == size) {
            if(index.length() != 0) {
                // 유일성을 검사합니다.
                if(isUnique(relation, index)) {
                    // 최소성을 검사합니다.
                    if(isMinimal(list, index)) {
                        list.add(index);
                    }
                }    
            }
        }
        else {
            // 현재 깊이의 속성을 고르지 않고 다음 깊이로 넘어갑니다.
            getDfsKey(index, depth+1, size, relation, list);
            // 현재 깊이의 속성을 고르고 다음 깊이로 넘어갑니다.
            getDfsKey(index+depth, depth+1, size, relation, list);
        }
    }
    
    // 유일성을 확인하는 함수입니다.
    public boolean isUnique(String[][] relation, String index) {
        HashSet<String> set = new HashSet<>();
        
        for(int i = 0 ; i < relation.length ; i++) {
            String key = "";
            // index의 각 번호에 해당하는 속성의 값을 key에 저장합니다.
            // ex) index = 12 일 경우 1 = 이름, 2 = 전공 이므로 key = 이름전공 이 됩니다.
            for(String s : index.split("")) {
                key += relation[i][Integer.parseInt(s)];
            }
            // 이미 있는 key라면 유일성을 확보하지 못한것이므로 false를 return합니다.
            if(!set.add(key)) {
                return false;
            }
        }
        
        return true;
    }
    
    // 최소성을 확인하는 함수입니다.
    public boolean isMinimal(List<String> list, String index) {
        // 후보키가 저장된 list에서 index를 비교합니다.
        // index를 한 글자씩 포함하는지 확인합니다.
        for(String key : list) {
            int count = 0;
            for(String s : index.split("")) {
                if(key.contains(s)) {
                    count++;
                    // 포함하고 있는 글자의 개수가 후보키의 글자 개수와 같다면
                    // 최소성을 확보하지 못한것이므로 false를 return합니다.
                    if(count == key.length()) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}