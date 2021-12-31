import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // 논문 인용 횟수를 오름차순으로 정렬합니다.
        Arrays.sort(citations);
        
        // ex) citations = [1, 2, 18, 19, 20] 이라면 H-Index는 3입니다. (3회 이상 3개 = true, 4회 이상 4개 = false)
        // H-Index의 최대치는 논문의 총 개수(n) 이므로 n을 하나씩 줄여가며 인용 횟수와 비교합니다.

        for(int i = 0 ; i < citations.length ; i++) {
            int h = citations.length-i;
            
            // 인용 횟수는 오름차순으로 정렬되어 있으므로
            // citations[i]번 이상 인용된 논문의 개수는 citations-i가 됩니다.
            // 논문의 총 개수인 n이 H-Index가 되려면 가장 적은 인용 횟수가 n이상이어야합니다.
            // n보다 작다면 그다음 인용 횟수가 n을 하나씩 줄여가며 다음으로 적은 인용 횟수와 비교합니다.
            // 즉 citataions[i]가 n보다 작으면 n-1과citation[i+1]비교합니다.
            // ex) citations = [1, 2, 18, 19, 20] 이라면
            // i = 2 일때 citations[2] = 18이며 18번 이상 인용된 논문의 개수는 3입니다.
            
            if(citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}


/* 문제를 직관적으로 풀이한 코드 for문이 2번 사용되어 속도가 느리다.
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int h = 0 ; h < citations.length+1 ; h++) {
            for(int i = 0 ; j < citations.length ; i++) {
                if(i <= citations[i]) {
                    if(i <= citations.length-i) {
                        answer = h;
                    }
                }
            }
        }
        
        return answer;
    }
}
*/