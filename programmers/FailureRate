import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        int[] stagesCount = new int[N+1]; //N+1은 마지막 스테이지까지 클리어  한 사용자
        Fail[] failRate = new Fail[N]; //스테이지 번호와 실패율이 담길 객체

        //멈춰있는 스테이지 각 갯수를 저장
        for(int i : stages) {
            stagesCount[i-1]++;
        }
        
        //스테이지는 1번부터시작이나 i는 0번부터시작것에 주의
        for(int i = 0 ; i < N ; i++) {
            int stagesSum = 0;
            //i번째 스테이지의 실패율은 i/(i부터 N번째에 도달한 사람의 수)이다. 여기서 N번째는 모든 스테이지를 클리어한 사람이다.
            for(int j = i ; j < stagesCount.length ; j++) {
                stagesSum += stagesCount[j];
            }
            //스테이지에 도달한 유저가 없을 경우
            if(stagesSum == 0) {
                failRate[i] = new Fail(i, 0);    
            }
            else {
                failRate[i] = new Fail(i, (double) stagesCount[i]/stagesSum);    
            }
        }
        
        Arrays.sort(failRate);
        
        for(int i = 0 ; i < N ; i++) {
            answer[i] = failRate[i].stageNum+1;
        }

        return answer;
    }
}
class Fail implements Comparable<Fail> {
    int stageNum;
    double failRate;

    public Fail(int stageNum, double failRate) {
        this.stageNum = stageNum;
        this.failRate = failRate;
    }

    public int compareTo(Fail fail) {
        //실패율은 내림차순
        if(this.failRate < fail.failRate) {
            return 1;
        }
        //실패율이 같으면 스테이지 번호 오름차순(작은 번호의 스테이지가 먼저)
        else if(this.failRate == fail.failRate) {
            if(this.stageNum > fail.stageNum) {
                return 1;
            }
        }
        return -1;
    } 
}