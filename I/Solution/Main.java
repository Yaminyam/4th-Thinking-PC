package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static char[][] map;
    static boolean[][][] bfsIsVisited;
    static int xSize, ySize, playerNum, bossHP, bossX, bossY, count;
    static int[] xArrow, yArrow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        ySize = Integer.parseInt(info[0]);
        xSize = Integer.parseInt(info[1]);
        playerNum = Integer.parseInt(info[2]);
        map = new char[ySize][xSize];
        bfsIsVisited = new boolean[playerNum][ySize][xSize];
        xArrow = new int[]{1, 0, -1, 0};
        yArrow = new int[]{0, 1, 0, -1};
        PlayerInfo[] playerInfo = new PlayerInfo[playerNum];
        for (int i = 0; i < ySize; i++) {
            String input = br.readLine();
            for (int j = 0; j < xSize; j++) {
                map[i][j] = input.charAt(j);
                if ('a' <= map[i][j] && map[i][j] <= 'z') {
                    playerInfo[map[i][j] - 'a'] = new PlayerInfo(map[i][j] - 'a', -1, i, j, false);
                    bfsIsVisited[map[i][j] - 'a'][i][j] = true;
                } else if (map[i][j] == 'B') {
                    bossY = i;
                    bossX = j;
                }
            }
        }
        LinkedList<PlayerInfo> queue = new LinkedList<>();
        for (int i = 0; i < playerNum; i++) {
            String[] p = br.readLine().split(" ");
            int id = p[0].charAt(0);
            int dps = Integer.parseInt(p[1]);
            playerInfo[id - 'a'].setDps(dps);
            queue.add(playerInfo[id - 'a']);
        }
        bossHP = Integer.parseInt(br.readLine());
        bfs(queue);
        System.out.println(count);
    }

    public static void bfs(LinkedList<PlayerInfo> queue) {
        int dps, id, x, y;
        boolean isFindBoss;
        while (queue.size() != 0) {
            int queueSize = queue.size();
            if (bossHP <= 0) {
                break;
            }
            for (int s = 0; s < queueSize; s++) {
                PlayerInfo next = queue.poll();
                id = next.getId();
                dps = next.getDps();
                x = next.getX();
                y = next.getY();
                isFindBoss = next.isFindBoss();
                if (x == bossX && y == bossY) {
                    if (!isFindBoss) { // 첫 발견
                        count++;
                    }
                    bossHP -= dps;
                    queue.add(new PlayerInfo(id, dps, y, x, true));
                } else {
                    for (int i = 0; i < 4; i++) {
                        if (!(y + yArrow[i] >= ySize) && !(x + xArrow[i] >= xSize) && !(y + yArrow[i] < 0) && !(x + xArrow[i] < 0) && map[y + yArrow[i]][x + xArrow[i]] != 'X' && !bfsIsVisited[id][y + yArrow[i]][x + xArrow[i]]) {
                            queue.add(new PlayerInfo(id, dps, y + yArrow[i], x + xArrow[i], isFindBoss));
                            bfsIsVisited[id][y + yArrow[i]][x + xArrow[i]] = true;
                        }
                    }
                }
            }
        }
    }
}

class PlayerInfo {
    private int id;
    private int dps;
    private int y;
    private int x;
    private boolean isFindBoss;

    public PlayerInfo(int id, int dps, int y, int x, boolean isFindBoss) {
        this.id = id;
        this.dps = dps;
        this.y = y;
        this.x = x;
        this.isFindBoss = isFindBoss;
    }

    public int getId() {
        return id;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFindBoss() {
        return isFindBoss;
    }
}