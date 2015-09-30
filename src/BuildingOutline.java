package src;

import java.util.*;

/**
 * Created by yellowstar on 9/21/15.
 */
public class BuildingOutline {
    public static void main(String[] args) {
        BuildingOutline buildingOutline = new BuildingOutline();
        int[][] buildings = {{1,3,3},{2,4,4},{5,6,1}};
        buildingOutline.buildingOutline(buildings);
    }
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int row = buildings.length;
        if (row == 0) return ret;
        int col = buildings[0].length;
        List<Building> buildingList = new ArrayList<>();
        int endX = 0;
        for (int i = 0; i < row; i++) {
            if (buildings[i][2] != 0) {
                buildingList.add(new Building(buildings[i][0], buildings[i][1], buildings[i][2]));
                endX = Math.max(endX, buildings[i][1]);
            }
        }
        Collections.sort(buildingList, new Comparator<Building>() {
            @Override
            public int compare(Building a, Building b) {
                return a.start - b.start;
            }
        });
        PriorityQueue<Building> queue = new PriorityQueue<>(100, new Comparator<Building>() {
            @Override
            public int compare(Building a, Building b) {
                return b.height - a.height;
            }
        });
        int startX = buildingList.get(0).start;
        int i = 0;
        int curStart = startX;
        int curHeight = 0;
        for (int x = startX; x <= endX; x++) {
            while (i < buildingList.size()) {
                Building building = buildingList.get(i);
                if (building.start == x) {
                    queue.offer(building);
                    i++;
                }
                else
                    break;
            }
            if (queue.isEmpty()) {
                curHeight = 0;
                continue;
            }
            Building maxBuilding = queue.peek();
            while (maxBuilding.end < x) {
                maxBuilding = queue.poll();
            }
            if (maxBuilding.height != curHeight && curHeight != 0) {
                ArrayList<Integer> outline = new ArrayList<>();
                outline.add(curStart);
                outline.add(x);
                outline.add(curHeight);
                ret.add(outline);
                curStart = x;
                curHeight = maxBuilding.height;
            }
            curHeight = maxBuilding.height;
        }
        return ret;
    }

    class Building {
        int start;
        int end;
        int height;
        Building(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
}
