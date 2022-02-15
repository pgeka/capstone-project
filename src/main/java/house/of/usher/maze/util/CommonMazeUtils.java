package house.of.usher.maze.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static house.of.usher.maze.MazeComponent.FINISH_POINT;
import static house.of.usher.maze.MazeComponent.GRAPH;

public class CommonMazeUtils {
    public static List<Integer> findShortestPath(int nextPoint) {
        List<List<Integer>> paths = new ArrayList<>();
        findAllPaths(nextPoint, new LinkedList<>(), paths);
        List<Integer> shortestPath = new ArrayList<>();
        int minSize = Integer.MAX_VALUE;
        for (List<Integer> iteratedPath : paths) {
            if (iteratedPath.size() < minSize) {
                minSize = iteratedPath.size();
                shortestPath = iteratedPath;
            }
        }
        return shortestPath;
    }

    public static void findAllPaths(int nextPoint, LinkedList<Integer> path, List<List<Integer>> paths) {
        if (nextPoint == FINISH_POINT) {
            path.add(nextPoint);
            paths.add(path);
            return;
        }
        if (!path.contains(nextPoint)) {
            path.add(nextPoint);
        }
        List<Integer> points = GRAPH.get(nextPoint);
        for (Integer point : points) {
            if (path.contains(point)) {
                continue;
            }
            LinkedList<Integer> branchList = new LinkedList<>(path);
            findAllPaths(point, branchList, paths);
        }
    }
}
