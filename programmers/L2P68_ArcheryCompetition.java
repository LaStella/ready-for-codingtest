// 양궁대회
// https://programmers.co.kr/learn/courses/30/lessons/92342?language=java

class Solution {
    // 라이언이 맞혀야 하는 화살의 개수가 담긴 배열과 최대 점수차이를 저장할 변수를 전역변수로 만듭니다.
    int[] answer;
    int maxGap;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        maxGap = 0;
        
        // 과녁의 점수는 많이 맞힌 선수가 가져가므로 라이언은 어피치보다 화살을 많이 맞춰 점수를 가져가거나 맞추지 않고 가져가지 않는 두 경우로 나뉘게 됩니다.
        // 맞추거나 맞추지않는 두 선택의 조합을 계산합니다.
        comb(n, info, new int[11], 0);
        
        if(maxGap == 0) {
            answer = new int[] {-1};
        }
        
        return answer;
    }
    
    // 화살을 맞추는 조합을 만드는 함수입니다.
    public void comb(int n, int[] info, int[] ryaninfo, int depth) {
        // 모든 과녁을 확인 했을 경우
        if(depth == 11) {
            // 남은 화살은 제일 낮은 점수에 맞힙니다.
            ryaninfo[10] += n;
            // 라이언과 어피치의 점수차이를 계산합니다.
            int gap = getGap(ryaninfo, info);
            // 점수차이가 최대값일 경우 최대값과 라이언이 쏠 과녁을 갱신합니다.
            if(gap > maxGap) {
                maxGap = gap;
                setAnswer(ryaninfo);
            }
            // 점수차이가 최대값과 같을 경우 제일 낮은 점수의 과녁을 맞춘 개수를 비교합니다.
            else if(gap == maxGap) {
                if(checkLow(ryaninfo)) {
                    setAnswer(ryaninfo);
                }
            }
        }
        else {
            // 라이언이 과녁에 화살을 맞춰 점수를 가져가는 경우
            // 남은 화살이 어피치가 맞춘 화살의 개수+1만큼 있어야 점수를 가져갈 수 있습니다.
            if(n > info[depth]) {
                ryaninfo[depth] = info[depth]+1;
                comb(n-(info[depth]+1), info, ryaninfo, depth+1);
            }
            // 라이언이 과녁에 화살을 맞추지 않고 점수를 가져가지 않는 경우
            ryaninfo[depth] = 0;
            comb(n, info, ryaninfo, depth+1);
        }
    }
    
    // 라이언과 어피치의 점수 차이를 계산하는 함수입니다.
    public int getGap(int[] ryaninfo, int[] info) {
        int gap = 0;
        for(int i = 0 ; i < 11 ; i++) {
            if(ryaninfo[i] == 0 && info[i] == 0) {
                continue;
            }
            else if(ryaninfo[i] > info[i]) {
                gap += 10-i;
            }
            else {
                gap -= 10-i;
            }
        }

        return gap;
    }
    
    // 라이언이 맞혀야 하는 과녁의 화살 개수를 저장하는 함수입니다.
    public void setAnswer(int[] ryaninfo) {
        for(int i = 0 ; i < 11 ; i++) {
            answer[i] = ryaninfo[i];
        }
    }
    
    // 점수차이가 같을 경우 낮은 점수의 과녁부터 화살의 개수가 더 적은지 확인하는 함수입니다.
    public boolean checkLow(int[] ryaninfo) {
        for(int i = 10 ; i >= 0 ; i--) {
            if(answer[i] < ryaninfo[i]) {
                return true;
            }
            else if(answer[i] == ryaninfo[i]) {
                continue;
            }
            else {
                break;
            }
        }
        
        return false;
    }
}