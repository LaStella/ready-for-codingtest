// 구명보트
// https://programmers.co.kr/learn/courses/30/lessons/42885?language=java#

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        // 사람들을 무게순으로 정렬한 후 최대 무게와 최소 무게의 합을 구합니다.
        // 합이 limit보다 작거나 같다면 최소 무게인 사람이 같이 탈 수 있으므로 min을 +1합니다.
        // 합이 limit보다 크다면 최대 무게만 탈 수 있습니다. (max--는 for문에서 처리합니다.)
        int min = 0;
        for(int max = people.length-1 ; max >= min ; max--) {
            if(people[min]+people[max] <= limit) {
                min++;
            }
            answer++;
        }
        
        return answer;
    }
}



/* 시간초과 코드입니다.
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int p : people) {
            list.add(p);
        }
        
        Collections.sort(list);
        
        
        while(list.size() > 0) {
            int min = 0;
            int max = list.size()-1;
            if(min == max) {
                list.remove(min);
            }
            else if(list.get(min)+list.get(max) <= limit) {
                list.remove(max);
                list.remove(min);
            }
            else {
                list.remove(max);
            }
            answer++;
        }
        
        return answer;
    }
}

*/