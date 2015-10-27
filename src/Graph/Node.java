package src.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yellowstar on 10/24/15.
 */
class Node {
    char label;
    Set<Node> neighbors;
    int status;
    Node(char label) {
        this.label = label;
        this.neighbors = new HashSet<>();
        this.status = 0;
    }
}
