// 셔틀버스
// https://programmers.co.kr/learn/courses/30/lessons/17678?language=java

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        // 크루의 도착 시각을 정렬합니다.
        Arrays.sort(timetable);
        Queue<Integer> queue = new LinkedList<>();

        // 크루의 도착 시각을 분으로 환산하여 queue에 저장합니다.
        for(String time : timetable) {
            queue.add(getMinutes(time));
        }
        
        // 셔틀의 도착시간을 분으로 환산합니다.
        int arrivalTime = getMinutes("09:00");

        // 셔틀은 총 n회 운행하므로 n회까지 반복합니다.
        for(int i = 1 ; i <= n ; i++) {
            // 셔틀에 탑승한 크루 수를 나타낼 변수입니다.
            int count = 0;
            // 셔틀 정원 m명까지 크루가 탑승합니다.
            while(count < m) {
                // 대기열이 없거나 셔틀의 도착시각 이전에 도착한 크루가 없을 경우 탑승을 중단합니다.
                if(queue.isEmpty() || queue.peek() > arrivalTime) {
                    // 콘은 마지막 셔틀에 탑승해야하므로 콘의 도착시각은 마지막 셔틀의 도착시각입니다.
                    if(i == n) {
                        answer = getHoursMinutes(arrivalTime);
                    }
                    break;
                }
                // 대기열에 있는 크루가 셔틀에 탑승합니다.
                else {
                    // 콘은 마지막 셔틀에 탑승해야하며 대기열의 m-1번째까지 셔틀에 탑승했다면
                    // m번째 크루보다 먼저 콘이 탑승해야 하므로 콘의 도착시각은 m번째 크루의 도착시각 -1분입니다.
                    if(i == n && count == m-1) {
                        answer = getHoursMinutes(queue.peek()-1);
                        break;
                    }
                    // 셔틀에 크루가 탑승하므로 대기열에서 크루를 제거하고 현재 탑승 크루 수를 증가시킵니다.
                    else {
                        queue.poll();
                        count++;    
                    }
                }
            }
            // 다음 도착시각은 t분 후입니다.
            arrivalTime += t;
        } 
        
        return answer;
    }
    
    // HH:MM 형태의 시각을 분으로 바꾸는 함수입니다.
    public int getMinutes(String time) {
        return Integer.parseInt(time.split(":")[0])*60+Integer.parseInt(time.split(":")[1]);
    }
    
    // 분으로 표현된 시각을 HH:MM형태로 바꾸는 함수입니다.
    public String getHoursMinutes(int time) {
        String hours = String.format("%02d", time/60);
        String minutes = String.format("%02d", time%60);
        return hours+":"+minutes;
    }
}

/*
셔틀의 마지막 도착시간
대기열큐에서 도착시간에 맞는 인원을 태움
1. m-1명까지 태우고 대기열에서 m번째에 해당하는 크루보다 -1분 빠른시간
2. 도착시간에 맞는 크루를 다 태우고도 정원이 차지 않은경우 셔틀의 마지막 도착시간
*/