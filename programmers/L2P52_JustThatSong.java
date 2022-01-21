// 방금그곡
// https://programmers.co.kr/learn/courses/30/lessons/17683?language=java

import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int answerTime = 0;
        
        for(String minfo : musicinfos) {
            String[] musicinfo = minfo.split(",");
            String[] startTime = musicinfo[0].split(":");
            String[] endTime = musicinfo[1].split(":");
            String title = musicinfo[2];
            String score = musicinfo[3];
            int hours = Integer.parseInt(endTime[0])-Integer.parseInt(startTime[0]);
            int minutes = Integer.parseInt(endTime[1])-Integer.parseInt(startTime[1]);
            // 노래의 재생시간(분)을 계산합니다.
            int playTime = 60*hours+minutes;
            // 재생시간만큼 재생된 노래의 멜로디를 가져옵니다.
            String[] melody = makeMelody(playTime, makeArray(score));
            // 기억하는 멜로디를 배열로 바꿉니다.
            String[] mArray = makeArray(m);
            
            // 재생된 노래의 멜로디안에 기억하는 멜로디가 포함되는지 확인합니다.
            if(containMelody(mArray, melody)) {
                // 재생시간이 제일 긴 음악을 반환합니다.
                if(playTime > answerTime) {
                    answer = title;
                    answerTime = playTime;
                }
            }
        }
        
        
        
        return answer;
    }
    
    // 재생시간(분)만큼 노래를 반복한 멜로디를 반환하는 함수입니다.
    public String[] makeMelody(int playTime, String[] score) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < playTime ; i++) {
            list.add(score[i%score.length]);
        }
        return list.toArray(new String[list.size()]);
    }
    
    // 주어진 멜로디 또는 악보를 배열 형태로 바꿔주는 함수입니다.
    // 악보에 사용되는 음에 '#'붙은 경우 하나로 취급하기 위해 배열로 바꿔줍니다.
    public String[] makeArray(String score) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < score.length() ; i++) {
            if(i+1 < score.length()) {
                if(score.charAt(i+1) == '#') {
                    list.add(score.charAt(i)+"#");
                    i++;
                    continue;
                }    
            }
            list.add(Character.toString(score.charAt(i)));
        }
        return list.toArray(new String[list.size()]);
    }
    
    // 주어진 멜로디(melody)안에 기억하는 멜로디(m)이 포함되는지 확인하는 함수입니다.
    public boolean containMelody(String[] m, String[] melody) {
        for(int i = 0 ; i < melody.length ; i++) {
            // m의 첫 음과 같다면 이어지는 다음 음들을 확인합니다.
            if(melody[i].equals(m[0])) {
                for(int j = 0 ; j < m.length ; j++) {
                    // melody의 범위를 초과하면 안됩니다.
                    if(i+j >= melody.length) break;
                    // 일치하지 않는 음이 나오면 중단합니다.
                    if(!m[j].equals(melody[i+j])) break;
                    // m의 마지막 음까지 같다면 true를 반환합니다.
                    else {
                        if(j == m.length-1) return true;
                    }
                }
            }
        }
        
        return false;
    }
}