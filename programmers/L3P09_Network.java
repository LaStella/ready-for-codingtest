// 네트워크
// https://programmers.co.kr/learn/courses/30/lessons/43162?language=java

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        // 컴퓨터의 방문 여부를 확인하는 boolean 배열입니다.
        boolean[] visited = new boolean[n];
        
        // 각 컴퓨터를 순회하며 연결된 컴퓨터를 확인합니다.
        for(int i = 0 ; i < computers.length ; i++) {
            // 방문하지않은 컴퓨터라면 기존의 네트워크에 연결되어 있지 않는 새로운 네트워크입니다.
            if(!visited[i]) {
                checkConnectedComputer(computers, visited, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    // 컴퓨터의 연결 정보에 따라 연결된 컴퓨터들을 방문하는 함수입니다.
    public void checkConnectedComputer(int[][] computers, boolean[] visited, int n) {
        // n번 컴퓨터의 연결 정보를 확인하므로 해당 컴퓨터의 방문을 true로 변경합니다.
        visited[n] = true;
        // n번 컴퓨터에 연결된 컴퓨터들을 확인합니다.
        for(int j = 0 ; j < computers.length ; j++) {
            // n번 컴퓨터와 연결되어 있으면서 방문하지 않은 컴퓨터가 있다면
            // 해당 컴퓨터와 연결된 다른 컴퓨터들을 찾습니다.
            if(computers[n][j] == 1 && !visited[j]) {
                checkConnectedComputer(computers, visited, j);
            }
        }
    }
}