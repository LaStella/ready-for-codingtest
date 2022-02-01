// 주차 요금 계산
// https://programmers.co.kr/learn/courses/30/lessons/92341?language=java

import java.util.*;
import java.util.Map.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        // 차량번호을 key값으로 가지며 주차 요금을 저장하는 TreeMap입니다.
        // 차량번호를 기준으로 올림차순으로 정렬한 주차요금을 answer에 저장해야 하므로 TreeMap을 사용했습니다.
        TreeMap <Integer, Integer> parkingTime = new TreeMap<>();
        // 주차공간을 나타낼 HashMap입니다. 
        HashMap <Integer, String> parkingLot = new HashMap<>();
        
        for(String s : records) {
            String[] record = s.split(" ");
            int carNumb = Integer.parseInt(record[1]);
            // 입차일 경우 주차공간 HashMap에 차량번호와 주차시간을 저장합니다.
            if(record[2].equals("IN")) {
                parkingLot.put(carNumb, record[0]);
            }
            // 출차일 경우 주차공안에서 해당 차량번호에 해당하는 입차시간을 통해 주차한 시간을 계산합니다.
            else {
                int time = getMinutes(record[0]) - getMinutes(parkingLot.get(carNumb));
                parkingTime.put(carNumb, parkingTime.getOrDefault(carNumb, 0) + time);
                parkingLot.remove(carNumb);
            }
        }

        // 입차된 후로 출차된 내역이 없는 차들은 23:59분에 출차한 것으로 주차한 시간을 계산합니다.
        for(Entry<Integer, String> entry : parkingLot.entrySet()) {
            int time = getMinutes("23:59") - getMinutes(entry.getValue());
            parkingTime.put(entry.getKey(), parkingTime.getOrDefault(entry.getKey(), 0) + time);
        }

        answer = new int[parkingTime.size()];
        int index = 0;
        
        // 주차 시간을 가지고 주차 요금을 계산합니다.
        for(Integer key : parkingTime.keySet()) {
            // 기본 시간 이상이라면 기본 요금에 단위 시간마다 단위 요금을 계산합니다.
            if(parkingTime.get(key) > fees[0]) {
                answer[index++] = fees[1] + (int)Math.ceil((parkingTime.get(key)-fees[0])/(double)fees[2]) * fees[3];
            }
            // 기본 시간 이하라면 기본 요금만 청구합니다.
            else {
                answer[index++] = fees[1];
            }
        }
        
        return answer;
    }
    
    // HH:MM형태의 시간을 분으로 변환하는 함수입니다.
    public int getMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0,2));
        int minutes = Integer.parseInt(time.substring(3,5));
        minutes += hours*60;
        
        return minutes;
    }
}