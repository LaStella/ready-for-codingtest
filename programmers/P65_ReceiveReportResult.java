// 신고 결과 받기
// https://programmers.co.kr/learn/courses/30/lessons/92334?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        // 신고 정보를 저장할 멥입니다.
        HashMap<String, HashSet<String>> reportMap = new HashMap<>();
        // 신고 결과를 저장할 멥입니다.
        HashMap<String, Integer> resultMap = new HashMap<>();
        
        // 신고 정보를 저장합니다.
        for(String r : report) {
            // user[0] = 신고한 유저, user[1] = 신고 당한 유저
            String[] user = r.split(" ");
            
            // 신고 당한 유저의 신고한 유저들 set에 신고한 유저를 저장합니다.
            HashSet<String> users = reportMap.getOrDefault(user[1], new HashSet<>());
            users.add(user[0]);
            reportMap.put(user[1], users);
        }
        
        // 신고 당한 유저들 중 신고한 유저의 수가 k 이상인 유저에 대한 처리 결과 메일을 신고한 유저들이 받게됩니다.
        for(String key : reportMap.keySet()) {
            HashSet<String> users = reportMap.get(key);
            // 신고한 유저의 수가 k 이상인 경우
            if(users.size() >= k) {
                // 신고한 유저들의 처리 결과 메일 수를 1씩 늘립니다.
                for(String user : users) {
                    resultMap.put(user, resultMap.getOrDefault(user, 0)+1);
                }
            }
        }
        
        // id_list에 따라 answer배열의 값을 저장합니다.
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = resultMap.getOrDefault(id_list[i], 0);
        }
        
        return answer;
    }
}