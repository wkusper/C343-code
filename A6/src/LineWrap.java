import java.util.HashMap;
import java.util.Map;

class LineWrap {
  private final int lineWidth;
  private final Map<Pair<PList<String>,Integer>,Result> hash = new HashMap<>();

  LineWrap (int lineWidth) {
      this.lineWidth = lineWidth;
  }

  //-------------------------------------------------------------------------

  String greedy (PList<String> words, int space) {
        if (words.isEmpty()) {
            return "";
        } else {
            String w = words.get(0);
            PList<String> rest = words.remove(0);
            if (w.length() + 1 <= space) {
                return " " + w + greedy(rest, space - (w.length() + 1));
            } else {
                return "\n" + w + greedy(rest, lineWidth - w.length());
            }
        }
  }

  String runGreedy (String s) {
      PList<String> words = PList.fromArray(s.split("\\s"));
      String w = words.get(0);
      PList<String> rest = words.remove(0);
      return w + greedy(rest, lineWidth - w.length());
    }

  //-------------------------------------------------------------------------
  Result topdown (PList<String> words, int space) {
        if (words.isEmpty()) {
            return new Result("", 0);
        } else {
            Pair<PList<String>,Integer> key = new Pair<>(words, space);
            if (hash.containsKey(key)) {
                return hash.get(key);
            } else {
                String w = words.get(0);
                PList<String> rest = words.remove(0);
                if (w.length() + 1 <= space) {
                    Result r1 = topdown(rest, space - (w.length() + 1));
                    Result r2 = topdown(rest, lineWidth - w.length());
                    if (r1.cost() < r2.cost()) {
                        Result result = r1.updatePara(w);
                        hash.put(key, result);
                        return result;
                    } else {
                        Result result = r2.updateCost(w, space);
                        hash.put(key, result);
                        return result;
                    }
                } else {
                    Result result = topdown(rest, lineWidth - w.length()).updateCost(w, space);
                    hash.put(key, result);
                    return result;
                }
            }
        }
  }

  String runTopdown (String s) {
        hash.clear();
        PList<String> words = PList.fromArray(s.split("\\s"));
        String w = words.get(0);
        PList<String> rest = words.remove(0);
        return w + topdown(rest,lineWidth - w.length()).para();
    }

    //-------------------------------------------------------------------------

}
