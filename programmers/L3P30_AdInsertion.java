// 광고 삽입
// https://programmers.co.kr/learn/courses/30/lessons/72414?language=java

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        // 동영상의 최대 시간은 99:59:59로 초로 환산하면 359999초입니다.
        // 초단위로 누적 시청 시간을 계산하므로 360000크기의 배열을 사용합니다.
        int[] s_time = new int[360000];
        
        // 시청자들이 재생한 구간에 해당하는 초의 값을 증가시킵니다.(해당 구간을 재생할 경우 초값만큼 누적 재생시간이 증가합니다.)
        for(String log : logs) {
            String[] l_arr = log.split("-");
            int start = timeToSeconds(l_arr[0]);
            int end = timeToSeconds(l_arr[1]);
            
            for(int i = start ; i < end ; i++) {
                s_time[i]++;
            }
        }
        
        // max_time_sum : 최대 누적 시간, time_sum : 누적 시간, p_sec : 동영상 재생시간, a_sec : 광고 재생시간, adv_start_time : 광고 시작 시각
        long max_time_sum = 0;
        long time_sum = 0;
        int p_sec = timeToSeconds(play_time);
        int a_sec = timeToSeconds(adv_time);
        int adv_start_time = 0;
        
        // 초기값으로 동영상의 시작 시각부터 광고 재생시간만큼 누적 시간을 계산합니다.
        for(int i = 0 ; i < a_sec ; i++) {
            time_sum += s_time[i];
        }
        max_time_sum = time_sum;
        
        // 광고 종료시각부터 1초씩 광고 재생구간을 변경하며 누적 시간을 계산합니다.
        for(int i = a_sec ; i < p_sec ; i++) {
            // 다음 1초의 누적 시간을 더하고, 광고 시작 시각 1초의 누적 시간을 뺍니다.
            time_sum += s_time[i];
            time_sum -= s_time[i-a_sec];
            if(time_sum > max_time_sum) {
                max_time_sum = time_sum;
                // i는 광고 종료 시각이므로 i에서 재생 시간을 빼야 시작 시각이 됩니다.
                // 1부터 시작하는게 아닌 0부터 시작하는 것이므로 1을 더해줍니다.
                adv_start_time = i-a_sec+1;
            }
        }
        
        
        answer = secondsToTime(adv_start_time);
        
        return answer;
    }
    
    
    // HH:MM:SS 형태의 시간을 초 형태로 변환하는 함수입니다.
    public int timeToSeconds(String t) {
        int seconds = 0;
        String[] t_arr = t.split(":");
        seconds += Integer.parseInt(t_arr[0])*3600;
        seconds += Integer.parseInt(t_arr[1])*60;
        seconds += Integer.parseInt(t_arr[2]);
        
        return seconds;
    }
    
    // 초 형태의 시간을 HH:MM:SS 형태로 변환하는 함수입니다.
    public String secondsToTime(int t) {
        String hours = Integer.toString(t/3600);
        t %= 3600;
        if(hours.length() < 2) {
            hours = "0"+hours;
        }
        String minutes = Integer.toString(t/60);
        t %= 60;
        if(minutes.length() < 2) {
            minutes = "0"+minutes;
        }
        String seconds = Integer.toString(t);
        if(seconds.length() < 2) {
            seconds = "0"+seconds;
        }
        return hours+":"+minutes+":"+seconds;
    }
}

/*
초단위로 환산
99시간 59분 59초 = 100시간 미만
100시간 = 360000초
359999까지
*/