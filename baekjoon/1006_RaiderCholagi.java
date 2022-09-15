// 습격자 초라기
// https://www.acmicpc.net/problem/1006

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] e = new int[2][N+1];

        for (int i = 0 ; i <= 1 ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++)
            e[i][j] = Integer.parseInt(st.nextToken());
        }



    }
}

/*
도넛 형태로 구성된 건물은 2*N 형태의 배열로 표현합니다.
e[0]은 안쪽 원을, e[1]은 바깥쪽 원을 나타냅니다.

i번째 열에서 안쪽과 바깥쪽 중 한쪽만 특수소대를 침투시키거나 둘 모두 침투하는 경우 총 3가지 경우를 고려해야합니다.
3가지 경우를 각각 inside, outside, bothside로 표현합니다.
inside[i] : i번째 열의 안쪽 원을 점령하는데 드는 최소 특수소대의 수
outside[i] : i번째 열의 바깥쪽 원을 점령하는데 드는 최소 특수소대의 수
bothside[i] : i번째 열을 점령하는데 드는 최소 특수소대의 수

1. 안쪽을 침투하는 경우(inside[i])
    1-1. i번째 열의 안쪽을 침투한다면 i-1번째 열에서 안쪽과 바깥쪽 모두 침투한 상황에서 e[0][i]를 침투하는 방법이 있습니다.
         따라서, inside[i] = bothside[i-1]+1이 됩니다.
    1-2. e[0][i]와 e[0][i-1]의 합이 특수 소대원의 수(W)보다 작다면 한번 투입으로 두 구역을 침투할 수 있습니다.
         즉, i-1번째 열에서 바깥쪽을 점령하는데 드는 최소 특수소대의 수에 1을 더하는 결과가 됩니다.
         if((e[0][i] + e[0][i-1]) <= W) inside[i] = outside[i-1]+1
    따라서 (e[0][i] + e[0][i-1]) <= W 인 경우 위 두 경우 중 더 작은 값이 inside[i]가 됩니다.

2. 바깥쪽을 침투하는 경우(outside[i])
    2-1. 1-1과 유사하며 outside[i] = bothside[i-1]+1 입니다.
    2-2. if((e[1][i] + e[1][i-1]) <= W) outside[i] = inside[i-1]+1
    (e[1][i] + e[1][i-1]) <= W 인 경우 위 두 경우 중 더 작은 값이 outside[i]가 됩니다.

3. 양쪽을 침투하는 경우(bothside[i])
    3-1. i번째 열의 안쪽을 침투한 상황에서 바깥쪽(e[1][i])을 침투하는 방법이 있습니다.
         따라서, bothside[i] = inside[i]+1
    3-2. i번째 열의 바깥쪽을 침투한 상황에서 안쪽(e[0][i])을 침투하는 방법이 있습니다.
         따라서, bothside[i] = outside[i]+1
    3-3. i-1번째 열의 안쪽과 바깥족 모두 침투한 상황에서 i번째 열의 안쪽 바깥족을 모두 침투하는 방법이 있습니다.
         단, i번째 열의 안쪽과 바깥쪽 적의 합이 특수소대원의 수보다 작은 경우에만 가능합니다. (e[0][i] + e[1][i] <= W)
         따라서, bothside[i] = bothside[i-1]+1

*/