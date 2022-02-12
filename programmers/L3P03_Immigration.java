// 입국심사
// https://programmers.co.kr/learn/courses/30/lessons/43238?language=java

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        Arrays.sort(times);
        
        // 이분 탐색 알고리즘을 사용하여 최소 시간을 구합니다.
        // 최소 심사 시간 = 입국 심사를 기다리는 사람이 1명 이상이므로 가장 빠른 심사관이 심사할 경우 times[0]
        // 최대 심사 시간 = 입국 심사를 기다리는 사람들(n)을 가장 느린 심사관이 심사할 경우 n*times[lastindex];
        long start = times[0];
        long end = (long)n*times[times.length-1];
        long mid;
        
        while(start <= end) {
            // 중간값의 심사 시간
            mid = (start+end)/2;
            long people = 0;
            for(int i = 0 ; i < times.length ; i++) {
                // 중간값의 심사 시간동안 심사관들이 처리하는 사람의 수를 구합니다.
                people += mid / times[i];
                // 기다리는 사람의 수 이상으로 처리한 경우
                // 심사시간이 충분하므로 현재의 mid값이 최소 시간이거나 mid보다 적은 시간범위에 최소시간이 존재합니다.
                // 따라서 심사 시간 범위의 최대값을 mid-1로 바꿔줍니다.
                if(people >= n) {
                    end = mid-1;
                    answer = mid;
                    break;
                }
            }
            // 기다리는 사람의 수보다 적게 처리한 경우
            // 심사시간이 부족하므로 중간값의 심사 시간보다 많은 시간 범위에 최소시간이 존재합니다.
            // 따라서 심사 시간 범위의 최소값을 mid+1로 바꿔줍니다.
            if(people < n) {
                start = mid+1;
            }
        }
        return answer;
    }
}