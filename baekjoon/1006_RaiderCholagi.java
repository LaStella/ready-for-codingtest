// 습격자 초라기
// https://www.acmicpc.net/problem/1006

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, W;
    static int[][] e;
    static int[] inside, outside, bothside;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            e = new int[2][N];

            for (int i = 0 ; i <= 1 ; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < N ; j++)
                    e[i][j] = Integer.parseInt(st.nextToken());
            }

            inside = new int[N];
            outside = new int[N];
            bothside = new int[N];
            int answer = Integer.MAX_VALUE;

            inside[0] = 1;
            outside[0] = 1;
            bothside[0] = e[0][0]+e[1][0] < W ? 1 : 2;
            solve();

            answer = Math.min(answer, bothside[N-1]);

            System.out.println(answer);
        }




    }

    public static void solve() {
        for (int i = 1 ; i < N ; i++) {
            // i번 안쪽 구역까지 침투시키는 최소 개수
            inside[i] = bothside[i-1]+1;

            // i번 안쪽 구역의 붙어있는 두 구역의 적들이 W보다 작다면 한번의 침투로 가능합니다. (인접한 구역으로 한번 더 침투가 가능하므로)
            if (e[0][i]+e[0][i-1] < W) {
                inside[i] = Math.min(inside[i], outside[i-1]+1);
            }

            // i번 바깥쪽 구역까지 침투시키는 최소 개수
            outside[i] = bothside[i-1]+1;

            // 위와 마찬가지로 인접한 두 구역 적들이 W보다 작아 한번의 침투로 가능합니다.
            if (e[1][i]+e[1][i-1] < W) {
                outside[i] = Math.min(outside[i], inside[i-1]+1);
            }

            // i번 안쪽과 바깥쪽까지 모두 침투시키는 최소 개수
            bothside[i] = Math.min(inside[i]+1, outside[i]+1);

            // i번 구역의 안쪽과 바깥쪽 적들이 W보다 작다면 한번의 침투로 가능합니다.
            if (e[0][i]+e[1][i] < W) {
                bothside[i] = Math.min(bothside[i], bothside[i-1]+1);
            }
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

0번째열부터 N-1번째 열까지 계산하므로 0번째열에서의 초기값을 저장해야합니다.
문제는 주어진 구조가 도넛 형태로 배열의 선형구조가 아닌 원형구조라는 점입니다.
구역의 0번째 열과 마지막 N-1번째 열이 붙어있으므로 두 열안의 적의 수를 보고 초기값을 다르게 저장합니다.
1. 0번째 열과 N-1번째 열을 고려하지 않고 0번째부터 시작하는 경우
    0번째 열의 안쪽을 침투하는 경우는 1가지입니다.
    insidie[0] = 1
    0번째 열의 바깥쪽을 침투하는 경우는 1가지입니다.
    outside[0] = 1
    0번째 열 전체를 침투하는 방법은 안쪽과 바깥쪽 두 번을 침투하는 경우와 안쪽과 바깥쪽의 적의 수가 W보다 작아 한번의 침투로 가능한 경우가 있습니다.
    bothside[0] = if(e[0][0]+e[1][0] < W) 1 else 2

2. 0번째 열과 N-1번째 열의 안쪽 원을 한번의 침투로 가능한 경우
    e[0][0]+e[0][N-1] < W 인 경우 한번의 침투로 가능합니다. 초기에 이 두 구역을 침투하므로 두 구역을 없는 구역으로 생각하여 나머지 구역을 계산하면 됩니다.
    0번재 열의 안쪽이 없으므로 1번째 열에서 초기값을 정할 수 있습니다. (0번째 열의 바깥쪽만 고려하면 되므로)
    e[1][0]구역과 e[0][1]구역을 침투해야하며 두 구역은 붙어있지 않아 두 번 침투해야합니다.
    inside[1] = 2
    e[1][0]구역과 e[1][1]구역이 붙어있어 두 구역의 적의 수가 W보다 작다면 한 번에 침투가 가능합니다.
    outside[1] = if(e[1][0]+e[1][1] < W) 1 else 2
    위에서 구한 outside[1]이 1인 경우 e[0][1]구역을 추가로 침투하면 됩니다.
    또는 e[0][1]+e[1][1] < W라면 1번째 열은 한 번에 침투가 가능하므로 bothside[1] = 2가 가능합니다.
    bothside[1] = if(outside[1] == 1 || (e[0][1]+e[1][1] < W)) 2 else 3

3. 0번째 열과 N-1번째 열의 바깥쪽 원을 한번의 침투로 가능한 경우
    e[1][0]+e[1][N-1] < W 인 경우 한번의 침투로 가능합니다. 초기에 이 두 구역을 침투하므로 두 구역을 없는 구역으로 생각하여 나머지 구역을 계산하면 됩니다.
    2.와 마찬가지로 1번째 열에서 초기값을 정할 수 있습니다.
    e[0][0]구역과 e[0][1]구역이 붙어있어 두 구역의 적의 수가 W보다 작다면 한 번에 침투가 가능합니다.
    inside[1] = if(e[0][0]+e[0][1] < W) 1 else 2
    e[0][0]구역과 e[1][1]구역을 침투해야하며 두 구역은 붙어있지 않아 두 번 침투해야합니다.
    outside[1] = 2
    위에서 구한 inside[1]이 1인 경우 e[1][1]구역을 추가로 침투하면 됩니다.
    또는 e[0][1]+e[1][1] < W라면 1번째 열은 한 번에 침투가 가능하므로 bothside[1] = 2가 가능합니다.
    bothside[1] = if(inside[1] == 1 || (e[0][1]+e[1][1] < W)) 2 else 3





*/