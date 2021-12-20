// 조이스틱
// https://programmers.co.kr/learn/courses/30/lessons/42860?language=java#

import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // A~Z까지 조작 횟수를 map에 저장합니다.
        // A는 초기값이므로 0이며 이후 1씩 증가합니다.
        // 역순으로 이동이 가능하기 때문에 Z는 1이며, 역순으로 1씩 증가합니다.
        // 알파벳은 총 26자 이므로 양끝에서 1씩 증감하며 알파벳 아스키코드에 맞춰서 값을 저장합니다.
        for(int i = 0 ; i < 14 ; i++) {
            map.put(65+i, i);
            map.put(91-i, i);
        }
        
        // 문자열 name을 한글자씩 map의 key로 value를 확인하여 더합니다.
        // charAt형은 char형으로 나오므로 (int) 캐스팅을 해주어 아스키코드에 해당하는 key를 가져옵니다.
        for(int i = 0 ; i < name.length() ; i++) {
            answer += map.get((int) name.charAt(i));
        }
        
        // 커서의 이동횟수가 최대일 경우를 초기 min값으로 저장해줍니다.
        int moveCount = name.length()-1;
        
        // 첫 번째 위치는 이동할 필요가 없으므로 name의 1번째부터 시작합니다.
        for(int i = 1 ; i < name.length() ; i++) {
            int count = 0;
            // 해당하는 위치의 알파벳이 A일 경우
            if(name.charAt(i) == 'A') {
                // 연속해서 나오는 A를 모두 찾습니다.
                while(i < name.length() && name.charAt(i) == 'A') {
                    count++;
                    i++;
                }
                // 'A' 가 나오는 문자열을 기준으로 앞, 뒤로 나눕니다.
                // 1번부터 연속해서 'A'가 마지막으로 나오는 부분까지 substring하면 앞 문자열이 나옵니다.
                String prev = name.substring(1, i-count);
                // i번째부터 남은 문자열은 뒷 문자열이 됩니다.
                String next = name.substring(i, name.length());
                // 앞, 뒤 문자열중 짧은 문자열이 곧 짧은 이동 횟수가 되므로 왕복할 문자열은 짧은 문자열을 선택합니다.
                moveCount = Math.min(moveCount, prev.length()+next.length()+Math.min(prev.length(), next.length()));
            }
        }
       
        answer += moveCount;
        
        return answer;
    }
}