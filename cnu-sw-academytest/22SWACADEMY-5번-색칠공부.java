import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int targetRow = Integer.parseInt(st.nextToken());
        int targetCol = Integer.parseInt(st.nextToken());
        String color = st.nextToken();

        String[][] map = new String[row][column];
        for (int i = 0 ; i < row ; i++) {
            map[i] = br.readLine().split("");
        }

        boolean[][] visited = new boolean[row][column];

        paintColor(targetRow, targetCol, color, map, visited);
        printMap(map);
    }



    // 이미지(map)에서 지정된 (row, column)에 색(color)을 칠하는 함수입니다.
    private static void paintColor(int row, int column, String color, String[][] map, boolean[][] visited) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        visited[row][column] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int nextRow = row+dr[i];
            int nextColumn = column+dc[i];

            // 범위를 벗어나는 좌표는 칠하지 못하므로 넘어갑니다.
            if (!isInRange(nextRow, nextColumn, map)) continue;
            // 근접한 픽셀의 색이 같은 경우에만 칠합니다. 이미 칠한 픽셀이 아니어야합니다.
            if (map[nextRow][nextColumn].equals(map[row][column]) && !visited[nextRow][nextColumn]) {
                paintColor(nextRow, nextColumn, color, map, visited);
            }
        }

        map[row][column] = color;
    }

    // 주어진 좌표가 이미지 범위를 벗어나는지 확인하는 함수입니다.
    private static boolean isInRange(int row, int column, String[][] map) {
        return 0 <= row && row < map.length && 0 <= column && column < map[0].length;
    }

    // 이미지(map)을 출력하는 함수입니다.
    private static void printMap(String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (String[] m : map) {
            sb.append(String.join("", m)+"\n");
        }
        System.out.println(sb);
    }
}
