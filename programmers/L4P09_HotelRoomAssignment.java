// 호텔 방 배정
// https://programmers.co.kr/learn/courses/30/lessons/64063?language=java

import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];
        // 배정된 방을 key로, 배정된 방의 다음 빈 방을 value로 가지는 맵입니다.
        HashMap<Long, Long> map = new HashMap<>();
        
        // 원하는 방 번호를 통해 빈 방의 번호를 찾아서 저장합니다.
        for(int i = 0 ; i < n ; i++) {
            answer[i] = findEmptyRoom(room_number[i], map);
        }
        
        return answer;
    }
    
    // 원하는 방 번호를 통해 빈 방을 찾는 함수입니다.
    public long findEmptyRoom(long number, HashMap<Long, Long> map) {
        // 원하는 방이 비어있는 방이라면 원하는 방 번호(number)를 그대로 리턴합니다.
        if(!map.containsKey(number)) {
            map.put(number, number+1);
            return number;
        }
        // 원하는 방이 비어있지 않는 방이라면 방 번호를 통해 다음 빈 방 번호(map.get(number))를 가져옵니다.
        // 다음 빈 방 번호를 통해 빈 방을 찾습니다. (다음 빈 방 번호가 빈 방이 아닐 수 있기 때문에 탐색합니다.)
        else {
            long empty_room_number = findEmptyRoom(map.get(number), map);
            // 원하는 방 번호(number)에 해당하는 빈 방은 새로 배정되었으므로, 다음 빈 방을 새롭게 저장합니다.
            map.put(number, empty_room_number+1);
            return empty_room_number;
        }
    }
}

/*
방 배정시 빈방과 함께 맵에 저장합니다.
1번방 배정, key:1 value:2 저장
3번방 배정, key:3 value:4 저장
4번방 배정, key:4 value:5 저장
1번방 배정불가, 맵에서 찾은 빈방 2
    > 2번방에 대해서 다시 탐색(빈방인지확인)
    > 2번방이 빈방이라면 key:2 value:3이 저장됩니다.
    > 2번방이 빈방이 아니라면 key:2에 해당하는 빈방을 가져와 다시 탐색합니다.
*/