// 징검다리
// https://programmers.co.kr/learn/courses/30/lessons/43236?language=java

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
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