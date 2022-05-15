// 징검다리
// https://programmers.co.kr/learn/courses/30/lessons/43236?language=java

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        // 거리의 최소값을 이분탐색으로 찾습니다.
        // 거리의 최소값은 1(출발지와 도착지에 바위가 놓여있지 않으므로)부터 distance(바위를 모두 제거하여 없을 경우)까지의 범위를 가집니다.
        int start = 1;
        int end = distance;
        int mid = 0;
        
        while(start<=end) {
            mid = (start+end)/2;
            // 이전 바위의 위치를 저장할 변수입니다.
            int prev_rock = 0;
            // 제거할 바위의 개수를 저장할 변수입니다.
            int remove_count = 0;
            
            for(int i = 0 ; i < rocks.length ; i++) {
                // 이전 바위와 현재 바위 사이의 거리가 mid보다 작으면 바위를 제거하여 거리를 늘립니다.
                if(rocks[i]-prev_rock < mid) {
                    if(++remove_count > n) break;
                }
                else {
                    prev_rock = rocks[i];
                }
            }
            
            // 마지막 바위에서 도착지까지의 거리를 mid와 비교합니다.
            if(distance-prev_rock < mid) remove_count++;
            
            // 바위를 n개를 초과해서 제거할 수 없으므로 거리의 최소값은 mid보다 더 작은 값이어야합니다.
            if(remove_count > n) {
                end = mid-1;
            }
            // 조건에 맞는 거리의 최소값을 찾았으므로 저장합니다.
            else {
                answer = Math.max(answer, mid);
                start = mid+1;
            }
        }
        
        return answer;
    }
}

/*
n개의 바위를 제거뒤 바위 사이의 거리의 최소값이 가장 크게
즉, 최소값(x)가 가장 크게 되는 바위들.
바위를 정렬함
정렬된 바위를 차례로 방문 하며 거리를 측정
최소값(x)를 기준으로 이분탐색
x보다 작은 간격의 바위가 존재한다면 바위를 제거(count++)
더이상 x보다 작은 간격의 바위가 존재하지 않다면 x가 최소값이 될 수 있으며 x를 줄여서 더 작은 값으로 되는지 확인해야한다.
n개의 바위만큼 제거하였으나 x보다 작은 간격의 바위가 존재한다면 x를 늘려야한다.
*/