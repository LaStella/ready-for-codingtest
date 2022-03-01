// 불량 사용자
// https://programmers.co.kr/learn/courses/30/lessons/64064?language=java

import java.util.*;

class Solution {
    // 제재된 아이디 목록의 내용이 중복되는 것을 거르기 위해 HashSet에 저장합니다.
    HashSet<String> set = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        // 제재 아이디 목록을 나타낼 변수입니다.
        // T = true, F = false를 뜻하며 user_id의 수만큼의 길이를 가집니다.
        StringBuilder result = new StringBuilder("F".repeat(user_id.length));
        
        dfs(user_id, banned_id, 0, result);
        // 제재된 경우의 수 중 내용이 동일하다면 같은것으로 처리하므로 set의 size만큼의 경우의 수를 가지게 됩니다.
        answer = set.size();
        
        return answer;
    }
    
    // 깊이 우선 탐색 알고리즘을 사용하여 아이디를 제재하는 경우의 수를 구하는 함수입니다.
    public void dfs(String[] user_id, String[] banned_id, int depth, StringBuilder result) {
        // 제재된 아이디의 수가 불량 사용자 아이디 목록의 수와 동일하면 제재 아이디 목록을 set에 저장합니다.
        if(depth == banned_id.length) {
            set.add(result.toString());
        }
        else {
            for(int i = 0 ; i < user_id.length ; i++) {
                // 유저 아이디와 제재 아이디의 길이가 같아야 합니다.
                if(user_id[i].length() == banned_id[depth].length()) {
                    // 아직 제재하지 않은 아이디이며, 가려진 문자를 포함한 제재 아이디와 일치하는 경우 제재할 수 있습니다.
                    if(result.charAt(i) == 'F' && checkId(user_id[i], banned_id[depth])) {
                        // 제재된 아이디에 해당하는 result의 index(i)번째 문자를 T(true)로 바꿔줍니다.
                        result.setCharAt(i, 'T');
                        // 다음 제재 아이디로 넘어갑니다.
                        dfs(user_id, banned_id, depth+1, result);
                        // 제재 하지않고 넘어갑니다.
                        result.setCharAt(i, 'F');
                    }
                }
            }
        }
    }
    
    // 주어진 유저 아이디와, 제재 아이디를 비교하여 제재 가능 여부를 return하는 함수입니다.
    public boolean checkId(String uid, String bid) {
        for(int i = 0 ; i < uid.length() ; i++) {
            if(uid.charAt(i) == bid.charAt(i) || bid.charAt(i) == '*') {
                continue;
            }
            else {
                return false;
            }
        }
        
        return true;
    }
}