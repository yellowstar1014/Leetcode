package src.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yellowstar on 11/1/15.
 */
public class GraphNode {
    int label;
    List<GraphNode> nexts;
    public GraphNode(int label) {
        this.label = label;
        this.nexts = new LinkedList<>();
    }
}
