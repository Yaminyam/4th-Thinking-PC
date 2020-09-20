import java.io.*;
import java.util.*;

public class Main {
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tc = br.readLine().split(" ");
        int skillNum = Integer.parseInt(tc[0]);
        int mobHp = Integer.parseInt(tc[1]);
        int[] skillCooldownArr = new int[skillNum];
        int[] remainTimeArr = new int[skillNum];
        int[] dpsArr = new int[skillNum];
        for (int i = 0; i < skillNum; i++) {
            String[] skill = br.readLine().split(" ");
            skillCooldownArr[i] = Integer.parseInt(skill[0]);
            dpsArr[i] = Integer.parseInt(skill[1]);
        }
        dfs(0, skillCooldownArr, remainTimeArr, dpsArr, skillNum, mobHp);
        System.out.println(minTime);
    }

    public static void dfs(int time, int[] skillCooldownArr, int[] remainTimeArr, int[] dpsArr, int skillNum, int mobHp) {
        if (time > minTime) {
            return;
        }
        if (skillNum == 1 && minTime != Integer.MAX_VALUE) {
            return;
        }
        if (mobHp <= 0) {
            minTime = Math.min(time, minTime);
            return;
        }
        boolean isNextAvail = false;
        int minNext = Integer.MAX_VALUE;
        for (int i = 0; i < skillNum; i++) {
            if (remainTimeArr[i] == 0) {
                isNextAvail = true;
                int[] nextRemainTime = Arrays.copyOf(remainTimeArr, skillNum);
                nextRemainTime[i] = skillCooldownArr[i];
                reduceRemainTime(nextRemainTime, 1, skillNum);
                dfs(time + 1, skillCooldownArr, nextRemainTime, dpsArr, skillNum, mobHp - dpsArr[i]);
            } else {
                minNext = Math.min(minNext, remainTimeArr[i]);
            }
        }
        if (!isNextAvail) {
            int[] nextRemainTime = Arrays.copyOf(remainTimeArr, skillNum);
            reduceRemainTime(nextRemainTime, minNext, skillNum);
            dfs(time + minNext, skillCooldownArr, nextRemainTime, dpsArr, skillNum, mobHp);
        } else {
            int[] nextRemainTime = Arrays.copyOf(remainTimeArr, skillNum);
            reduceRemainTime(nextRemainTime, 1, skillNum);
            dfs(time + 1, skillCooldownArr, nextRemainTime, dpsArr, skillNum, mobHp);
        }
    }

    public static void reduceRemainTime(int[] remainTime, int time, int skillNum) {
        for (int j = 0; j < skillNum; j++) {
            remainTime[j] -= time;
            if (remainTime[j] < 0) {
                remainTime[j] = 0;
            }
        }
    }
}