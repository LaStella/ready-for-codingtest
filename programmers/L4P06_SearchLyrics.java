// 가사 검색
// https://programmers.co.kr/learn/courses/30/lessons/60060?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        // 단어(words)의 길가 key가 되고, value로 Node객체를 사용합니다.
        // 각 node는 자식맵을 가지고있습니다.
        HashMap<Integer, Node> map = new HashMap<>();
        // 단어를 역순으로 저장하는 맵입니다.
        HashMap<Integer, Node> reverse_map = new HashMap<>();
        
        for(int i = 0 ; i < words.length ; i++) {
            int length = words[i].length();
            
            // 단어의 길이를 key로 사용하여 root node를 가져옵니다.
            Node node = map.computeIfAbsent(length, key -> new Node());
            // 자식을 새로 추가하므로 카운트를 늘려줍니다.
            node.childCount++;
            // 역순으로 저장하는 맵도 위와 같은 과정을 적용합니다.
            Node reverse_node = reverse_map.computeIfAbsent(length, key -> new Node());
            reverse_node.childCount++;
            
            // 단어를 한글자씩 자식맵에 저장합니다.
            // 저장한 자식맵을 node로 가져와 다음 글자를 자식으로 저장할 수 있게 합니다.
            for(int j = 0 ; j < length ; j++) {
                node = node.childMap.computeIfAbsent(words[i].charAt(j), key -> new Node());
                node.childCount++;
                reverse_node = reverse_node.childMap.computeIfAbsent(words[i].charAt(length-j-1), key -> new Node());
                reverse_node.childCount++;
            }
        }
        
        // 키워드에 따라 검색을 합니다.
        for(int i = 0 ; i < queries.length ; i++) {
            // 키워드의 첫글자가 와일드카드라면 역순으로 검색을 합니다.
            if(queries[i].charAt(0) == '?') {
                answer[i] = find(reverse_map, reverseString(queries[i]));
            }
            else {
                answer[i] = find(map, queries[i]);
            }
        }
        
        return answer;
    }
    
    // Trie구조에서 키워드에 해당하는 단어의 개수(자식의 수)를 찾는 함수입니다.
    public int find(HashMap<Integer, Node> map, String query) {
        // 키워드와 같은 길이를 가진 단어가 없다면 0을 리턴합니다.
        if(!map.containsKey(query.length())) {
            return 0;
        }
        
        // 키워드의 길이에서 root node를 찾습니다.
        Node node = map.get(query.length());
        
        // 키워드의 문자를 통해 와일드 카드가 나올때까지 자식 노드를 찾습니다.
        for(int i = 0 ; i < query.length() ; i++) {
            // 키워드의 문자가 와일드 카드라면 지금까지 찾은 단어의 개수(자식의 수)를 리턴합니다.
            if(query.charAt(i) == '?') {
                return node.childCount;
            }
            // 키워드의 문자가 없다면 키워드에 해당하는 단어가 없으므로 0을 리턴합니다.
            else if(!node.childMap.containsKey(query.charAt(i))) {
                return 0;
            }
            // 키워드의 문자가 와일드 카드가 나올때까지 다음 문자의 자식노드를 가져옵니다.
            else {
                node = node.childMap.get(query.charAt(i));
            }
        }
        
        return node.childCount;
    }
    
    // 문자열(키워드)을 뒤집어주는 함수입니다.
    public String reverseString(String query) {
        return new StringBuilder(query).reverse().toString();
    }
    
    // Trie 자료구조에 사용되는 정점을 나타내는 객체입니다.
    class Node {
        int childCount = 0;
        HashMap<Character, Node> childMap = new HashMap<>();
        public Node() {
            
        }
    }
}
/*
1.
쿼리의 단어와 키워드를 비교
- 같은 길이인지 확인
- 같은 길이라면, 서로 같은 위치에 같은 문자인지 확인
시간초과
-----------------------------
2.
같은 위치에 같은 문자를 확인할 필요없이 와일드카드 문자를 제외한 문자열과 그에 맞는 위치의 문자열을 비교
와일드카드는 앞 또는 뒤에서부터 존재, 중간에 끼는 경우가 없으므로
- 쿼리와 '?'을 지운 쿼리의 길이를 비교하여 와일드카드의 개수를 셉니다.
- '?'가 처음으로 나오는 인덱스를 통해 접두인지 접미인지 구분합니다.
- words의 단어들을 와일드카드를 지운 문자열과 같은 위치의 문자열을 자르고 비교합니다.
시간초과
-----------------------------
3.
Trie 자료구조를 이용
- words를 길이에 따라 저장
- 이후 문자에 따라 저장
쿼리에 따른 검색 과정
fro??   길이5탐색 > f,r,o순 탐색 > ?가 나오면 o까지 탐색한 자식의 수를 반환
???do   길이5탐색 > ?가 나오면 탐색한 자식의 수를 반환하므로 문제 발생

위의 문제를 해결하기 위해 한 단어를 정순, 역순으로 저장합니다.
???do   길이5탐색 > o,d순 탐색 > ?가 나오면 d까지 탐색한 자식의 수를 반환

정순과 역순을 하나의 Trie에서 사용하게 되면 od로 시작하는 문자열과 역순od로 시작하는 문자열이 같이 저장되는 문제가 발생합니다.
따라서, 정순과 역순 Trie를 따로 만듭니다.

*/