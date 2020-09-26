#define _USE_MATH_DEFINES

#include<iostream>
#include<cstdio>
#include<cmath>
#include<cstring>

#include<algorithm>
#include<functional>

#include<string>
#include<vector>
#include<map>
#include<queue>
#include<stack>
#include<tuple>

using namespace std;
const int INF = numeric_limits<int>::max();
const double pi = M_PI;

int main()
{
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		if (31 % (i + 1) == 1) {
			cout << i << "\n";
		}
	}
}