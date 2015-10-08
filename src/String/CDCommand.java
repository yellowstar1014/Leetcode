package src.String;

/**
 * Created by yellowstar on 10/7/15.
 */
public class CDCommand {
    public static void main(String[] args) {
        CDCommand cdCommand = new CDCommand();
        System.out.println(cdCommand.cd2("/", "ab//c//"));
        System.out.println(cdCommand.cd2("/ab/cd", "..///cd//"));
        System.out.println(cdCommand.cd2("/a", "..//../cd//"));
        System.out.println(cdCommand.cd2("/","///ab//bb"));
    }
    // newPath = "" -> return curPath
    // newPath = "/abc" -> curPath = "/" newPath = "abc"
    // curPath = "/"  -> curPath = ""
    // . -> skip
    // a/b/.. -> a
    // a//b -> a/b
    // curPath = / newPath = .. return /
    public String cd(String curPath, String newPath) {
        if (newPath.isEmpty()) return curPath;
        // absolute path
        if (newPath.charAt(0) == '/') {
            curPath = "/";
            // remove prefix "/"
            int k = 0;
            while (newPath.charAt(++k) == '/');
            newPath = newPath.substring(k);
        }
        int i = 0;
        int j = i;
        String ret = curPath;
        if (ret.equals("/")) ret = "";
        while (i < newPath.length()) {
            while (j < newPath.length() && newPath.charAt(j) != '/') j++;
            String sub = newPath.substring(i, j);
            if (sub.equals("..")) {
                if (!ret.equals("")) ret = ret.substring(0, ret.lastIndexOf("/"));
            } else if (!sub.equals(".") && !sub.isEmpty()){
                ret += "/" + sub;
            }
            j++;
            i = j;
        }
        if (ret.isEmpty()) ret = "/";
        return ret;
    }

    public String cd2(String curPath, String newPath) {
        String path = curPath + "/" + newPath;
        String[] subs = path.split("/");
        String ret = "";
        for (String sub : subs) {
            if (sub.isEmpty() || sub.equals(".")) continue;
            else if (sub.equals("..")) {
                if (!ret.equals("")) {
                    ret = ret.substring(0, ret.lastIndexOf("/"));
                }
            }
            else {
                ret += "/" + sub;
            }
        }
        if (ret.isEmpty()) ret = "/";
        return ret;
    }
}
