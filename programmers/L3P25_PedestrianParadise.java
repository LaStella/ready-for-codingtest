// 보행자 천국
// https://programmers.co.kr/learn/courses/30/lessons/1832?language=java#

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        // 아래쪽 방향으로 이동하는 경로를 0, 오른쪽 방향 이동하는 경로를 1로 저장합니다.
        int[][][] route = new int[m][n][2];
        
        // m, n이 1일 경우 경로는 하나뿐이므로 1을 리턴합니다.
        if(m == 1 && n == 1) return answer = 1;
        
        // 아래쪽 방향으로 이동하는 경로를 초기화합니다.
        for(int i = 0 ; i < m ; i++) {
            // 통행 금지구역이 존재한다면 더 이상 아래로 이동할 수 없으므로 중단합니다.
            if(cityMap[i][0] == 1) {
                break;
            }
            else {
                route[i][0][0] = 1;
            }
        }
        
        // 위와 동일하며 오른쪽 방향으로 이동하는 경로를 초기화합니다.
        for(int i = 0 ; i < n ; i++) {
            if(cityMap[0][i] == 1) {
                break;
            }
            else {
                route[0][i][1] = 1;
            }
        }
        
        // 출발지에서 아래쪽과 오른쪽으로 각각 일직선 경로를 초기화했으므로 (1,1)에서 부터 가능한 경로의 수를 계산합니다.
        for(int r = 1 ; r < m ; r++) {
            for(int c = 1 ; c < n ; c++) {
                // 통행 금지구역인 경우 넘어갑니다.
                if(cityMap[r][c] == 1) {
                    continue;
                }
                else {
                    // (r, c)로의 경로는 (r-1, c)로의 경로와 (r, c-1)로의 경로의 개수의 합입니다.
                    // (r-1, c)에서 방향 전환이 금지되는 경우
                    if(cityMap[r-1][c] == 2) {
                        // (r-1, c)에서 (r, c)로 아래쪽 방향 이동을 하게되나
                        // (r-1, c)로 오른쪽 방향 이동한 경우 방향 전환을 해야하므로 더할 수 없습니다.
                        route[r][c][0] += route[r-1][c][0] % MOD;
                    }
                    // 방향전환 금지가 없다면 아래쪽 방향 경로와 오른쪽 방향 경로를 모두 더할 수 있습니다.
                    else {
                        route[r][c][0] += (route[r-1][c][0] + route[r-1][c][1]) % MOD;
                    }
                    // 위와 마찬가지로 (r, c-1)에서 방향 전환이 금지되는 경우
                    if(cityMap[r][c-1] == 2) {
                        // (r, c-1)에서 (r, c)로 오른쪽 방향 이동을 해야하며, (r, c-1)로 아래쪽 방향으로 이동한 경로는 더할 수 없습니다.
                        route[r][c][1] += route[r][c-1][1] % MOD;
                    }
                    else {
                        route[r][c][1] += (route[r][c-1][0] + route[r][c-1][1]) % MOD;
                    }
                }
            }
        }
        
        // 도착지로의 아래쪽 방향 경로와 오른쪽 방향 경로의 개수의 합이 정답이 됩니다.
        answer += (route[m-1][n-1][0] + route[m-1][n-1][1]) % MOD;
        
        return answer;
    }
}