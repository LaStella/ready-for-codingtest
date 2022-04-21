// 스타 수열   
// https://programmers.co.kr/learn/courses/30/lessons/70130?language=java

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        return answer;
    }
}

/*
a집합이 짝수일 경우 2n개를 제거, 홀수일 경우 2n-1개를 제거하여 부분집합을 생성

만들어진 부분집합의 n개의 집합 중 첫번째 집합을 기준으로 2n-1번째 집합까지 있는 원소는 유지, 없는 원소는 제거
    원소의 개수가 0이되면 스타수열이 아님


*/