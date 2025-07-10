import java.util.HashMap;

public class Points {
    HashMap<Character, Double> points;

    public Points(HashMap defaultPointMap) {
        points = defaultPointMap;
    }

    public HashMap<Character, Double> getPoints() {
        return points;
    }

    public void AddPoints (HashMap pointsToAdd) {
        for (Object o: pointsToAdd.keySet()){
          Double value = (Double) pointsToAdd.get(o);
          points.put((Character) o, points.get(o) + value); // Add value to existing value
        }
    }

}
