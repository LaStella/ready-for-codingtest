import java.util.*;

public class P18_Budget {
    // import java.util.*;

    class Solution {
        public int solution(int[] d, int budget) {
            int answer = 0;
            
            //부서별 신청 금액을 정렬한다. 예산에서 작은 금액부터 차감하는 것이 최대한 많은 부서를 지원할 수 있기때문.
            Arrays.sort(d);

            for(int i : d) {
                budget -= i;
                //예산 차감 후 음수가 나오면 중지
                if(budget < 0) {
                    break;
                }
                //음수가 아니면 지원한 부서수를 증가시킨다.
                else {
                    answer++;
                }    
            }
        
            return answer;
        }
    }
}