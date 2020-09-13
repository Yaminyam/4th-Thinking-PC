import sys

parent = {}
rank = {}

def make_set(v):
    parent[v] = v
    rank[v] = 0

def find(v):
    if parent[v] != v:
        parent[v] = find(parent[v])
        
    return parent[v]

def union(v, u):
    root1 = find(v)
    root2 = find(u)
    
    if root1 != root2:
        if rank[root1] > rank[root2]:
            parent[root2] = root1
        else:
            parent[root1] = root2
            
            if rank[root1] == rank[root2]:
                rank[root2] += 1

def getMST(n, edges):    
    for i in range(n):
        make_set(i)
    cost = 0
    mst = []
    edges.sort(key = lambda x : x[2])
    
    for edge in edges:
        v, u, weight = edge
        if find(v) != find(u):
            union(v, u)
            mst.append(edge)
            cost += weight
    return mst, cost

def dfs(data, visited, index):
    visited[index] = True
    d_list = []
    h_list = []
    for v, w in data[index]:
        if v in visited:
            continue

        diameter, height = dfs(data, visited, v)
        d_list.append(diameter) # sub d
        h_list.append(height + w) # sub h

    if len(h_list) == 0 and len(d_list) == 0:
        return 0, 0

    h_list = sorted(h_list, reverse=True)
    max_d = max(d_list)
    if len(h_list) >= 2:
        max_cand_d = h_list[0] + h_list[1]
        if max_d < max_cand_d:
            max_d = max_cand_d

    return max_d, h_list[0]

def getDiameter(data):
    visited = dict()
    d, h = dfs(data, visited, 0)
    return max(d, h)

def edgesToTree(n, edges):
    tree = {}
    for i in range(n):
        tree[i] = list()
    for s, e, cost in edges:
        tree[s].append((e, cost))
        tree[e].append((s, cost))
    return tree

def solver():
    costs = []
    N, K = sys.stdin.readline().rsplit()
    N = int(N)
    K = int(K)
    for i in range(K):
        s, e, cost = sys.stdin.readline().rsplit()
        s = int(s)
        e = int(e)
        cost = int(cost)
        costs.append([s, e, cost])
    mst, cost = getMST(N, costs)
    tree = edgesToTree(N, mst)
    diameter = getDiameter(tree)
    print(cost)
    print(diameter)

solver()
