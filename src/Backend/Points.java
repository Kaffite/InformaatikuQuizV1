package Backend;

import java.util.*;
/*
    Purpose:
    1) Contains the points for each student type
    2) Adds points to the types, based on user's answers
 */
public class Points {
    HashMap<Character, Double> points;

    public Points(HashMap defaultPointMap) {
        points = defaultPointMap;
    }

    public HashMap<Character, Double> getPoints() {
        return points;
    }

    public void addPoints(HashMap pointsToAdd) {
        for (Object o: pointsToAdd.keySet()){
          Double value = (Double) pointsToAdd.get(o);
          points.put((Character) o, points.get(o) + value); // Add value to existing value
        }
    }


}
