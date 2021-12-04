import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        // 각 작업의 필요한 시간을 배열에 저장
        int[] timeRequired = new int[progresses.length];
        // 한번 배포당 배포될 기능의 개수
        int count = 1;
        
        for(int i = 0 ; i < progresses.length ; i++) {
            timeRequired[i] = (int)Math.ceil((double)(100-progresses[i])/speeds[i]);
        }
        
        // 첫 번째 작업의 필요시간을 기준으로 시작, 기준보다 시간이 더 소요 될 경우 count값을 저장
        // 반대로 기준과 같거나 작을 경우 count를 증가
        for(int i = 1 ; i < timeRequired.length ; i++) {
            if(timeRequired[i] > timeRequired[i-count]) {
                answerList.add(count);
                count = 1;
            }
            else {
                count++;
            }
        }
        // 배열의 마지막값은 비교 후 for문내에서 저장이 안되었으므로 마지막 값에 대해서 저장
        answerList.add(count);
        
        answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}