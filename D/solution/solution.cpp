#include<stdio.h>
#include<queue>
#include<vector>
#include<algorithm>
#define INF 987654321;
using namespace std;
int N, M, X, Y;
int Dist[501];//우리집에서 이웃집 까지의 거리
vector<pair<int, int>> v[501];//pair<b,거리>[a]
priority_queue<pair<int, int>> pq;//pair< 가중치와 현재 집 위치 
int main() {
	scanf("%d %d %d %d", &N, &M, &X, &Y);
	for (int i = 0; i < M; i++) {
		int a, b, len;
		scanf("%d %d %d", &a, &b, &len);
		v[a].push_back(make_pair(b, len));
		v[b].push_back(make_pair(a, len));
	}
	//다익스트라
	for (int i = 0; i < 501; i++) {
		Dist[i] = INF;
	}
	Dist[Y] = 0;
	pq.push(make_pair(0, Y));
	while (!pq.empty()) {
		int cur = pq.top().second;
		int cCost = -pq.top().first;
		pq.pop();
		for (int i = 0; i < v[cur].size(); i++) {
			int next = v[cur][i].first;
			int nCost = v[cur][i].second;
			if (cCost + nCost < Dist[next]) {
				Dist[next] = cCost + nCost;
				pq.push(make_pair(-Dist[next], next));
			}
		}
	}
	sort(Dist, Dist + 501);
	for (int i = 0; i < N; i++) {
		Dist[i] *= 2;//왕복이므로
	}
	int temp = X;
	int Date = 1;
	for (int i = 0; i < N; i++) {
		if (temp - Dist[i] > 0) {
			temp -= Dist[i];
		}
		else {
			if (Dist[i] >= X) {
				Date = -1;
				break;
			}
			else {
				temp = X;
				Date++;
				i--;
			}
		}
	}
	printf("%d", Date);
}