// 기지국 설치
// https://programmers.co.kr/learn/courses/30/lessons/70130?language=java

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        // 1번째 아파트가 전파 범위에 못들어가는 경우입니다.
        if(1 < stations[0]-w) {
            // distance = 첫 번째 기지국의 왼쪽 전파 범위-1
            double distance = stations[0]-w-1;
            answer += Math.ceil(distance / (w*2+1));
        }
        
        // 기지국과 기지국사이에 전파범위에 들어가지 못하는 아파트들의 수(distance)를 계산합니다.
        // 아파트들의 수를 전파범위(w*2+1)로 나눈 몫의 올림값 만큼 기지국을 추가로 건설합니다.
        for(int i = 1 ; i < stations.length ; i++) {
            // distance = (현재 기지국의 왼쪽 전파 범위) - (이전 기지국의 오른쪽 전파 범위+1)
            double distance = (stations[i]-w)-(stations[i-1]+w+1);
            answer += Math.ceil(distance / (w*2+1));
        }
        
        // 마지막번째 아파트가 전파 범위에 못들어가는 경우입니다.
        if(n > stations[stations.length-1]+w) {
            // distance = n번째 아파트 - (마지막 기지국의 오른쪽 전파 범위)
            double distance = n-(stations[stations.length-1]+w);
            answer += Math.ceil(distance / (w*2+1));
        }

        return answer;
    }
}

/*
1번째부터 stations[0]-w-1까지의 거리에 w*2+1을 나눈 값의 올림값만큼 기지국을 설치
stations[0]+w+1부터 stations[1]-w-1까지의 거리를 마찬가지로 w*2+1을 나눈 값의 올림값만큼 기지국 설치

즉, 기지국과 기지국 전파 범위 사이의 거리를 계산하여 전파의 범위로 나누는 것
단, 첫 번째 아파트와 첫 번째 기지국, 마지막 아파트와 마지막 기지국은 전파 범위안에 포함 되는지에 따라 기지국을 설치 여부가 결정된다.
*/