// 거리두기 확인하기
// https://programmers.co.kr/learn/courses/30/lessons/81302?language=java


class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0 ; i < places.length ; i++) {
            // 이미 확인한 대기실인지 저장하는 배열입니다.
            boolean[][] visited = new boolean[5][5];
            int result = 1;
            
            for(int r = 0 ; r < places[i].length ; r++) {
                for(int c = 0 ; c < places[i][r].length() ; c++) {
                    // 응시자가 있는 대기실이면 해당 대기실을 기준으로 거리두기를 확인합니다.
                    if(places[i][r].substring(c, c+1).equals("P")) {
                        result *= checkDistance(r, c, 0, visited, places[i]);
                    }
                }
            }
            
            answer[i] = result;
        }
        
        return answer;
    }
    
    // 기준 대기실로부터 맨해튼 거리가 2 이하인 대기실을 돌며 응시자가 있는지 확인하는 함수입니다.
    // 거리 두기를 지키지 않을 경우 0이므로 result값에 1 또는 0을 곱하면서 거리 두기를 지키는지 확인합니다.
    // r, c : 기준 대기실의 row와 column
    // distance : 맨해튼 거리
    // visited : 확인한 대기실인지 확인하는 배열
    // place :  응시자들의 정보와 대기실 구조가 담긴 배열
    public int checkDistance(int r, int c, int distance, boolean[][] visited, String[] place) {
        int result = 1;
        visited[r][c] = true;
        
        // 맨해튼 거리가 2보다 큰 경우 거리두기를 지키고 있으므로 1을 반환합니다.
        if(distance > 2) {
            return 1;
        }
        
        // 처음 기준 대기실을 시작으로 하기때문에 맨해튼 거리가 0이 아니면서 응시자가 있는 대기실일 경우
        // 거리두기를 지키지 않고 있으므로 0을 반환합니다.
        if(distance != 0 && place[r].substring(c, c+1).equals("P")) {
            return 0;
        }

        // 파티션으로 막혀 있을 경우 맨해튼 거리와 상관없이 거리두기를 지키고 있으므로 1을 반환합니다.
        else if(place[r].substring(c, c+1).equals("X")) {
            return 1;
        }
        
        // 각 if문의 조건은 배열의 범위를 넘지 않는 경우입니다.
        // 본래 위, 아래, 왼쪽, 오른쪽을 모두 확인하여야 하지만, 첫 번째 행부터 응시자를 기준으로 판별하기 때문에
        // 위에서 아래로 거리두기를 확인하는 것과 아래에서 위를 확인하는 것은 중복이 됩니다.
        // 따라서 아래, 왼쪽, 오른쪽만 확인하면 됩니다.

        // 현재 대기실에서 아래행에 위치한 대기실을 확인합니다.
        if(r < 4) {
            if(!visited[r+1][c]) {
                result *= checkDistance(r+1, c, distance+1, visited, place);    
            }
        }
        // 현재 대기실에서 왼쪽열에 위치한 대기실을 확인합니다.
        if(c > 0) {
            if(!visited[r][c-1]) {
                result *= checkDistance(r, c-1, distance+1, visited, place);    
            }
        }
        //현재 대기실에서 오른쪽열에 위치한 대기실을 확인합니다.
        if(c < 4) {
            if(!visited[r][c+1]) {
                result *= checkDistance(r, c+1, distance+1, visited, place);    
            }
        }

        return result;
    }
}