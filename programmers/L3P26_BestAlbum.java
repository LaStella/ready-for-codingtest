// 베스트앨범
// https://programmers.co.kr/learn/courses/30/lessons/42579?language=java#

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        HashMap<String, Integer> genreCount = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> genreMap = new HashMap<>();
        
        for(int i = 0 ; i < genres.length ; i++) {
            genreCount.put(genres[i], genreCount.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        // System.out.println(genreCount.size());
        if(genreCount.size() == 1) {
            List<String> keyList = new ArrayList<>(genreCount.keySet());
            HashMap<Integer, Integer> genre_1 = genreMap.get(keyList.get(0));
            List<Integer> genrekeyList = new ArrayList<>(genre_1.keySet());
        }
        else {
            
        }
        
        
        
//         System.out.println(answerList);
        
//         System.out.println(genreCount);
//         System.out.println(genreMap);
        answer = answerList.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
    
    
}

class Song {
    int i;
    int play;
    String genre;
    public Song(int i, int play, String genre) {
        this.i = i;
        this.play = play;
        this.genre = genre;
    }
}

/*
map 장르, 총 재생수
map 고유번호, 재생수
*/