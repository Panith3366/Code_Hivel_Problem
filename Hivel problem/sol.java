import java.io.*;
import org.json.*;
public class Main {
    public static long dec(String v, int b) {
        long r = 0;
        for (char c : v.toCharArray()) {
            int d = Character.isDigit(c) ? c - '0' : 10 + Character.toLowerCase(c) - 'a';
            r = r * b + d;
        }
        return r;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String t;
        while ((t = br.readLine()) != null) {
            sb.append(t);
        }
        JSONObject root = new JSONObject(sb.toString());
        JSONObject ks = root.getJSONObject("keys");
        int n = ks.getInt("n");
        int k = ks.getInt("k");
        int[] xs = new int[k];
        long[] ys = new long[k];
        int idx = 0;
        for (int i = 1; i <= n && idx < k; i++) {
            if (!root.has(String.valueOf(i))) continue;
            JSONObject o = root.getJSONObject(String.valueOf(i));
            int b = Integer.parseInt(o.getString("base"));
            long y = dec(o.getString("value"), b);
            xs[idx] = i;
            ys[idx] = y;
            idx++;
        }
        double c = 0;
        for (int i = 0; i < k; i++) {
            double L = 1;
            for (int j = 0; j < k; j++) {
                if (i == j) continue;
                L *= (-xs[j]) / (double)(xs[i] - xs[j]);
            }
            c += ys[i] * L;
        }
        System.out.println(Math.round(c));
    }
}
