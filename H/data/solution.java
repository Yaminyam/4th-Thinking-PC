import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class solution {
    static Map<String, String> womanPartner;
    static Map<String, String> manPartner;
    static Map<String, List<String>> manPref;
    static Map<String, Map<String, Integer>> womanPref;
    public static void stableMarriage() {
        boolean isAllEngaged = false;
        while(!isAllEngaged) {
            isAllEngaged = true;
            Iterator<String> manIter = manPref.keySet().iterator();
            while(manIter.hasNext()) {
                String man = manIter.next();
                if(manPartner.get(man) != null) {
                    continue;
                }
                String woman = manPref.get(man).remove(0);
                if(womanPartner.get(woman) == null) {
                    womanPartner.put(woman, man);
                    manPartner.put(man, woman);
                } else {
                    String previousMan = womanPartner.get(woman);
                    if(womanPref.get(woman).get(previousMan) < womanPref.get(woman).get(man)) {
                        manPartner.put(previousMan, null);
                        manPartner.put(man, woman);
                        womanPartner.put(woman, man);
                    }
                    isAllEngaged = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        SortedSet<String> set = new TreeSet<>(manPartner.keySet());
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) {
            String man = iter.next();
            sb.append(man + " " + manPartner.get(man)).append("\n");
        }
        System.out.print(sb);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        manPartner = new HashMap<>();
        womanPartner = new HashMap<>();
        manPref = new HashMap<>();
        womanPref = new HashMap<>();

        /* list of male, females */
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            String man = st.nextToken();
            manPref.put(man, new LinkedList<>());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            String woman = st.nextToken();
            womanPref.put(woman, new HashMap<>());
        }


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String man = st.nextToken();
            for(int j = 0; j < N; j++){
                String partner = st.nextToken();
                manPref.get(man).add(partner);
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String woman = st.nextToken();
            for(int j = 0; j < N; j++){
                String partner = st.nextToken();
                womanPref.get(woman).put(partner, -(j + 1));
            }
        }

        stableMarriage();
        br.close();
    }
}
