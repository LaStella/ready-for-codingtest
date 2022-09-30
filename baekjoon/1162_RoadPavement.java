// 도로 포장
// https://www.acmicpc.net/problem/1162


/*
다익스트라와 우선순위큐블 이욯하는 유리해보입니다.
1. 큐에 저장될 Node에는 현재 위치, 현재 포장한 도로의 개수, 현재 비용이 저장됩니다.
2. 우선순위큐를 이용하여 비용이 적게 드는 순으로 뽑히도록 합니다.
3. 현재 위치에서 연결된 도로를 이용하여 다음 위치로 이동합니다.
4. 출발지인 1번 도시에서 목적지인 N번 도시로 이동해야합니다.
5. dp[n][k] : n번 도시로 k개의 도로가 포장되었을때 가는 최소 비용이라고 정의합니다.
6. 이동한 정보를 도로를 포장하는 경우와 포장하지 않은 경우로 나누어서 큐에 넣습니다.
7. 도로를 포장하는 경우 도로의 비용이 0으로 변하므로 현재 위치에서 다음 위치로 이동하는 비용이 저렴해지는 경우에만 가능합니다.
dp[next_index][now_count+1] > dp[now_index][now_count]

 */