// 매칭 점수
// https://programmers.co.kr/learn/courses/30/lessons/42893?language=java

import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;
        LinkedHashMap<String, Info> map = new LinkedHashMap<>();
        // 모든 알파벳을 소문자로 통일합니다.
        word = word.toLowerCase();
        
        int index = 0;
        for(String page : pages) {
            // 페이지의 정보를 담을 객체를 만들고, 모든 알파벳을 소문자로 통일합니다.
            Info info = new Info(index);
            String page_name;
            page = page.toLowerCase();
            
            // head안의 url을 페이지명으로 저장합니다.
            Pattern head_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
            Matcher head_url_matcher = head_url_pattern.matcher(page);
            head_url_matcher.find();
            page_name = head_url_matcher.group().replace("<meta property=\"og:url\" content=","");
            
            // <body> 이후의 모든 문자를 body로 사용하며, word는 하나의 영단어 이므로 알파벳소문자를 제외한 모든 문자를 공백으로 바꿔줍니다.
            // 페이지의 body에서 word를 찾습니다. word를 찾을 때마다 기본 점수를 증가시킵니다.
            String body = page.split("<body>")[1].replaceAll("[^a-z]", " ");
            Pattern body_word_pattern = Pattern.compile("\\b"+word+"\\b");
            Matcher body_word_matcher = body_word_pattern.matcher(body);
            while(body_word_matcher.find()) {
                info.basic_score++;
            }
            
            // 외부링크를 찾아 리스트에 저장합니다.
            Pattern external_url_pattern = Pattern.compile("<a href=\"(\\S*)\"");
            Matcher external_url_matcher = external_url_pattern.matcher(page);
            while(external_url_matcher.find()) {
                String external_url = external_url_matcher.group().replace("<a href=", "");
                info.link_list.add(external_url_matcher.group().replace("<a href=", ""));
            }
            
            // 페이지명을 키로, 페이지 정보를 담은 객체를 값으로 맵에 저장합니다.
            map.put(page_name, info);
            index++;
        }
        
        // 각 페이지들의 링크점수를 계산합니다.
        // key페이지의 외부링크들에 (key페이지의 기본점수 / key페이지의 외부링크 수)만큼 링크점수를 더합니다.
        // map에 존재하지 않는 외부링크는 계산할 필요가 없습니다.
        for(String key : map.keySet()) {
            Info info = map.get(key);
            for(String external_url : info.link_list) {
                if(map.containsKey(external_url)) {
                    map.get(external_url).link_score += info.basic_score / info.link_list.size();
                }
            }
        }
        
        // 최대 매칭점수를 가지는 페이지를 찾습니다.
        double max_score = 0;
        for(String key : map.keySet()) {
            Info info = map.get(key);
            double matching_score = info.basic_score+info.link_score;
            if(matching_score > max_score) {
                max_score = matching_score;
                answer = info.index;
            }
        }
        
        return answer;
    }
}

class Info {
    int index;
    double basic_score;
    double link_score;
    List<String> link_list;
    
    public Info(int index) {
        this.index = index;
        this.basic_score = 0;
        this.link_score = 0;
        this.link_list = new ArrayList<>();
    }
}

/*
head_url을 id로, Info객체를 value로 하는 해쉬맵 생성
Info객체는 인덱스, 기본점수, 링크점수, 링크리스트로 이루어짐

기본점수 = body내에 등장한 word 수
링크점수 = 다른 페이지에서의 기본점수 / 다른 페이지에서의 외부 링크 수

*/