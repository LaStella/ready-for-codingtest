// 징검다리 건너기
// https://programmers.co.kr/learn/courses/30/lessons/64062?language=java

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int start = 0;
        // 모든 디딤돌을 최대 200,000,000번 밟을 수 있다면 최대로 건널 수 있는 인원이 됩니다.
        int end = 200000000;
        
        // 이분탐색으로 건널수 있는 사람의 수를 찾습니다.
        while(start <= end) {
            int mid = (start + end) / 2;
            
            // 징검다리 통과여부를 나타낼 변수입니다.
            boolean pass = true;
            int count = 0;
            
            // mid만큼의 사람이 모든 디딤돌을 건너뛸 수 있는지 확인합니다.
            for(int i = 0 ; i < stones.length ; i++) {
                // 디딤돌을 밟을 수 없다면 건너뛰는 횟수가 증가합니다.
                if(mid > stones[i]) {
                    count++;
                    // 건너뛰는 횟수가 k번이 되면 건너뛸 수 없으므로 중단합니다. (다음 디딤돌로 가기위해 한번은 건너기 때문에 k+1번 건너뛰게 되므로 중단)
                    if(count == k) {
                        end = mid-1;
                        pass = false;
                        break; 
                    }
                }
                // 밟을 수 있는 디딤돌이라면 건너뛰는 횟수를 초기화합니다.
                else {
                    count = 0;
                }
            }
            
            // mid만큼의 인원이 모든 돌다리를 건넜다면 더 많은 인원이 건널 수 있을 수 있으므로
            // 탐색 범위의 시작값을 mid+1로 바꿔줍니다.
            if(pass) {
                answer = mid;
                start = mid+1;
            }
        }
        
        return answer;
    }
}