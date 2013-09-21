package org.joow.codingame3;

import java.util.*;

public class Solution {
    class Measure {
        private final int nbItems;

        private final int duration;

        Measure(String measure) {
            final StringTokenizer stringTokenizer = new StringTokenizer(measure);
            nbItems = Integer.parseInt(stringTokenizer.nextToken());
            duration = Integer.parseInt(stringTokenizer.nextToken());
        }

        int getNbItems() {
            return nbItems;
        }

        int getDuration() {
            return duration;
        }
    }

    public static void main(String args[]) {
        final Scanner in = new Scanner(System.in);
        final int nbMeasures = in.nextInt();
        in.nextLine();
        final String[] measures = new String[nbMeasures];

        for (int i = 0; i < nbMeasures; i++) {
            measures[i] = in.nextLine();
        }

        final Solution solution = new Solution();

        System.out.println(solution.solve(nbMeasures, measures));
    }

    public String solve(int nbMeasures, String... measuresLines) {
        final List<Measure> measures = buildMeasures(nbMeasures, measuresLines);

        final int avgPerItem = avgPerItem(measures);

        final Measure measureWithMinItems = measures.get(0);
        final Measure measureWithMaxItems = measures.get(measures.size() - 1);

        if (measureWithMaxItems.getDuration() <= measureWithMinItems.getDuration()) {
            return "O(1)";
        }
        final int durationPerItemForMeasureWithMinItems = getDurationPerItem(measureWithMinItems);
        final int durationPerItemForMeasureWithMaxItems = getDurationPerItem(measureWithMaxItems);
        final int difference = Math.abs(durationPerItemForMeasureWithMaxItems - durationPerItemForMeasureWithMinItems);

        if (difference / avgPerItem > 5) {
            return "O(log n)";
        }



        if (durationPerItemForMeasureWithMaxItems == avgPerItem) {
            return "O(n)";
        }

        return "O(n log n)";
    }

    private List<Measure> buildMeasures(int nbMeasures, String[] measuresLines) {
        final List<Measure> measures = new ArrayList<>(nbMeasures);

        for (int i = 0; i < nbMeasures; i++) {
            measures.add(new Measure(measuresLines[i]));
        }

        return measures;
    }

    private int avgPerItem(List<Measure> measures) {
        int avgPerItem = 0;

        for (final Measure measure : measures) {
            avgPerItem += getDurationPerItem(measure);
        }

        return avgPerItem / measures.size();
    }

    private int avg(List<Measure> measures) {
        return sum(measures) / measures.size();
    }

    private int sum(List<Measure> measures) {
        int sum = 0;

        for (final Measure measure : measures) {
            sum += measure.getDuration();
        }

        return sum;
    }

    private int getDurationPerItem(final Measure measure) {
        return measure.getDuration() / measure.getNbItems();
    }

    private int min(List<Measure> measures) {
        int min = Integer.MAX_VALUE;

        for (final Measure measure : measures) {
            min = Math.min(min, measure.getDuration());
        }

        return min;
    }

    private int max(List<Measure> measures) {
        int max = Integer.MIN_VALUE;

        for (final Measure measure : measures) {
            max = Math.max(max, measure.getDuration());
        }

        return max;
    }
}
