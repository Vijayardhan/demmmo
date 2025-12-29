package model;

import java.util.HashMap;

public class Policy {
    private HashMap<String, Double> categoryLimits = new HashMap<>();

    public Policy() {
        categoryLimits.put("TRAVEL", 10000.0);
        categoryLimits.put("FOOD", 7000.0);
    }

    public double getLimit(String category) {
        return categoryLimits.get(category);
    }
}
