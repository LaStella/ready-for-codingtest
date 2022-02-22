// 순위
// https://programmers.co.kr/learn/courses/30/lessons/49191?language=java

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        // 선수들의 전적을 저장할 배열입니다.
        int[][] records = new int[n][n];
        
        // 승리시 1을 패배시 -1을 저장합니다.
        for(int[] result : results) {
            records[result[0]-1][result[1]-1] = 1;
            records[result[1]-1][result[0]-1] = -1;
        }
        
        // j번 선수가 i번 선수에게 이겼으며, i번 선수가 k번 선수에게 이겼다면
        // j번 선수는 k번 선수를 이깁니다. 또한 k번 선수는 j번 선수에게 패합니다.
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                for(int k = 0 ; k < n ; k++) {
                    if(records[j][i] == 1&& records[i][k] == 1) {
                        records[j][k] = 1;
                        records[k][j] = -1;
                    }
                }
            }
        }
        
        // answer를 선수 n명으로 시작하며 전적에서 0인 경우 전적을 알 수 없어서 순위를 정확하게 알 수 없는 선수이므로 answer를 1씩 줄여줍니다.
        answer = n;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                // 자기 자신과의 전적은 필요으므로 continue로 넘어갑니다.
                if(i == j) continue;
                if(records[i][j] == 0) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }
}