// 추석 트래픽
// https://programmers.co.kr/learn/courses/30/lessons/17676?language=java

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[] count = new int[lines.length];

        for(int i = 0 ; i < lines.length ; i++) {
            // 응답완료시간 = 처리종료시간을 구합니다.
            String endTime = lines[i].split(" ")[1];
            // 처리종료시간을 기준으로 +1초구간을 정합니다.
            // 시간, 분을 초로 환산하여 초단위로 계산합니다.
            double oneSecondArea = getSeconds(endTime)+1;

            // 현재 로그부터 이후 로그들을 확인합니다.
            // 로그들이 처리종료시간순으로 되어있으므로 현재 로그보다 이후의 로그들은 먼저 종료될 수 없습니다.
            for(int j = i ; j < lines.length ; j++) {
                // 시작시간을 구합니다.
                double startTime = getSeconds(lines[j].split(" ")[1])-Double.parseDouble(lines[j].split(" ")[2].replace("s", ""))+0.001;
                // 시작시간이 위의 1초구간 안에 포함되는지 확인합니다.
                if(startTime < oneSecondArea) count[i]++;
            }
        }
        
        // 처리량이 최대인 구간을 찾습니다.
        for(int i : count) {
            if(i > answer) {
                answer = i;
            }
        }
        
        
        
        return answer;
    }

    // 주어진 시간 String값을 초단위로 환산하여 return해주는 함수입니다.
    public double getSeconds(String s) {
        double result = 0.0;
        String[] sArray = s.split(":");
        result += Double.parseDouble(sArray[0])*3600+Double.parseDouble(sArray[1])*60+Double.parseDouble(sArray[2]);
        return result;
    }
}



/*
응답 완료시간을 통해 처리시작시간과 처리종료시간을 구함
1초 구간을 기준으로
1. 처리시작시간이 1초 구간안에 드는경우
2. 처리종료시간이 1초 구간안에 드는경우
3. 처리시작시간이 1초구간시작보다 작고, 처리 종료시간이 1초구간종료보다 큰경우
3가지경우
하지만 주어진 로그들이 시간순으로 정렬되어있다면
현재 로그보다 뒤에있는 로그는 종료시간이 무조건 현재로그보다 같거나 크다.
|<--1-->       |
|    <--2-->   |
|  <---3---->  |
|     <--4-->  |
|      <--5--->|
따라서 현재로그에서 종료시간을 기준으로 +1초구간에 해당하는 구간을 살피는것이 초당 최대 처리량을 파악하기에 용이한 구간이다.
현재로그+1초 보다 작은 처리시작시간을 가지고 있는 로그라면 1초구간에 해당하는 로그다.



*/