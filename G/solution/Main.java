import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tc = br.readLine().split(" ");
        int skillNum = Integer.parseInt(tc[0]);
        int mobHp = Integer.parseInt(tc[1]);
        LinkedList<Skill> userState = new LinkedList<>();
        for (int i = 0; i < skillNum; i++) {
            String[] skillInfo = br.readLine().split(" ");
            userState.add(new Skill(Integer.parseInt(skillInfo[0]), Integer.parseInt(skillInfo[1]), 0));
        }
        int timeCount = 0;
        while (mobHp > 0) {
            reduceRemainTimeAll(userState);
            Collections.sort(userState);
            Skill skill = userState.peek();
            if (skill.getRemainTime() == 0) {
                skill = userState.poll();
                mobHp -= skill.useSkill();
                userState.add(skill);
            }
            timeCount++;
        }
        System.out.println(timeCount);
    }

    public static void reduceRemainTimeAll(LinkedList<Skill> userState) {
        for (Skill skill : userState) {
            skill.reduceRemainTime();
        }
    }
}

class Skill implements Comparable<Skill> {
    private int dps;
    private int remainTime;
    private int cooldown;

    public int getDps() {
        return dps;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public int useSkill() {
        this.remainTime = cooldown;
        return dps;
    }

    public void reduceRemainTime() {
        if (remainTime > 0) {
            this.remainTime--;
        }
    }

    public Skill(int cooldown, int dps, int remainTime) {
        this.cooldown = cooldown;
        this.dps = dps;
        this.remainTime = remainTime;
    }

    @Override
    public int compareTo(Skill o) {
        if (this.remainTime < o.getRemainTime()) {
            return -1;
        } else if (this.remainTime == o.getRemainTime()) {
            if (this.dps < o.getDps()) {
                return 1;
            } else if (this.dps == o.getDps()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 1;
        }
    }
}