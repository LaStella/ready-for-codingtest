// 베스트앨범
// https://programmers.co.kr/learn/courses/30/lessons/42579?language=java#

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // 정답리스트입니다.
        ArrayList<Integer> answerList = new ArrayList<>();
        // 장르별 재생된 횟수를 저장하는 멥입니다.
        HashMap<String, Integer> genreCount = new HashMap<>();
        // 장르별 곡들의 리스트를 저장하는 멥입니다.
        HashMap<String, ArrayList<Song>> genreMap = new HashMap<>();
        
        for(int i = 0 ; i < genres.length ; i++) {
            // i번째 노래의 장르의 재생된 횟수를 더합니다.
            genreCount.put(genres[i], genreCount.getOrDefault(genres[i], 0)+plays[i]);
            // i번째 노래가 속한 장르에 i번째 노래를 넣습니다.
            ArrayList<Song> list = genreMap.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, plays[i]));
            genreMap.put(genres[i], list);
        }
        
        // 재생된 횟수에 따라 장르명을 정렬합니다.
        ArrayList<String> genre_Ordered = new ArrayList<>(genreCount.keySet());
        Collections.sort(genre_Ordered, (o1, o2) -> genreCount.get(o2).compareTo(genreCount.get(o1)));
        
        // 정렬된 장르 순서대로 노래를 뽑게됩니다.
        for(String genre : genre_Ordered) {
            // 해당 장르에 해당하는 노래의 리스트를 가져옵니다.
            ArrayList<Song> list = genreMap.get(genre);
            // 노래가 하나뿐일 경우 정답리스트에 추가합니다.
            if(list.size() == 1) {
                answerList.add(list.get(0).i);
            }
            // 노래 리스트를 재생된 횟수에 따라 정렬하여 첫번째, 두번째를 정답리스트에 추가합니다.
            else {
                Collections.sort(list, (o1, o2) -> o2.play - o1.play);
                answerList.add(list.get(0).i);
                answerList.add(list.get(1).i);
            }
        }
        
        answer = answerList.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}

// 노래의 고유번호와 재생된 횟수를 나타내는 객체입니다.
class Song {
    int i;
    int play;
    public Song(int i, int play) {
        this.i = i;
        this.play = play;
    }
}

/*
map 장르, 총 재생수
map 고유번호, 재생수
*/