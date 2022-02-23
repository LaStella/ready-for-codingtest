// 다단계 칫솔 판매
// https://programmers.co.kr/learn/courses/30/lessons/77486?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        // 판매원들의 index가 저장될 map입니다.
        HashMap<String, Integer> map = new HashMap<>();

        // 판매원들의 index를 map에 판매원명, index쌍으로 저장합니다.
        int index = 0;
        for(String e : enroll) {
            map.put(e, index++);
        }
        
        // 판매량 집계 데이터를 순회합니다.
        for(int i = 0 ; i < seller.length ; i++) {
            // seller[i]는 판매원의 이름을 나타내므로 map에서 판매원의 index를 가져옵니다.
            // answer[index]는 index번 판매원이 득한 이익금으로 판매량*칫솔가격의 90%를 더해줍니다. (amount[i] * 100 * 90 / 100)
            answer[map.get(seller[i])] += amount[i] * 90;
            // index번 판매원의 추천인을 가져옵니다.
            String s = referral[map.get(seller[i])];
            // index번 판매원의 추천인이 득할 이익금으로 판매량*칫솔가격의 10%입니다. (amount[i] * 100 * 10 / 100)
            int a = amount[i] * 10;
            // 추천인이 없을 때까지 반복하게됩니다.
            while(!s.equals("-")) {
                // 이익금의 10%가 1원 미만인 경우
                if(a/10 == 0) {
                    // 이익금을 추천인에게 분배하지않고 모두 가집니다.
                    answer[map.get(s)] += a;
                    // 더이상 추천인을 찾을 필요가 없으므로 중단합니다.
                    break;
                }
                // 이익금의 10%가 1원 이상인 경우
                else {
                    // 이익금의 10%를 제외한 나머지를 가집니다.
                    answer[map.get(s)] += a - a/10;
                    // 판매원의 추천인이 득할 이익금은 10%로 갱신합니다.
                    a /= 10;
                    // 다음 추천인을 갱신합니다.
                    s = referral[map.get(s)];
                }
            }
        }
        
        
        return answer;
    }
}