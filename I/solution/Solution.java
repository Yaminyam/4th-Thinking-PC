package I;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tc = br.readLine().split(" ");
        int playerNum = Integer.parseInt(tc[0]);
        int maxLimit = Integer.parseInt(tc[1]);
        ArrayList<LinkedList<PlayerInfo>> rooms = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            String[] playerInfo = br.readLine().split(" ");
            int level = Integer.parseInt(playerInfo[0]);
            String name = playerInfo[1];
            boolean isFind = false;
            for (LinkedList<PlayerInfo> roomList : rooms) {
                if (roomList.size() >= maxLimit) {
                    continue;
                }
                int standard = roomList.peek().getLevel();
                if (standard - 10 <= level && level <= standard + 10) {
                    isFind = true;
                    roomList.add(new PlayerInfo(level, name));
                    break;
                }
            }
            if (!isFind) {
                LinkedList<PlayerInfo> roomList = new LinkedList<>();
                roomList.add(new PlayerInfo(level, name));
                rooms.add(roomList);
            }
        }
        for (LinkedList<PlayerInfo> room : rooms) {
            Collections.sort(room);
            if (room.size() == maxLimit) {
                bw.write("Started!\n");
            } else {
                bw.write("Waiting!\n");
            }
            while (!room.isEmpty()) {
                PlayerInfo info = room.poll();
                bw.write(info.getLevel() + " " + info.getName() + "\n");
            }
        }
        bw.flush();
    }
}

class PlayerInfo implements Comparable<PlayerInfo> {
    private int level;
    private String name;

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public PlayerInfo(int level, String name) {
        this.level = level;
        this.name = name;
    }

    @Override
    public int compareTo(PlayerInfo o) {
        return this.name.compareTo(o.getName());
    }
}