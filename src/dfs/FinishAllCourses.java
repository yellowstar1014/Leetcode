package src.dfs;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/12/15.
 */
public class FinishAllCourses {
    //List<course> , each course has List<timeSlot>
    //find a list<timeSlot> so that u can finish all the courses
    static class Course {
        List<TimeSlot> slots = new ArrayList<>();
        public Course (List<TimeSlot> slots) {
            this.slots = slots;
        }

        public Course() {

        }

        public void add(TimeSlot timeSlot) {
            slots.add(timeSlot);
        }
    }

    static class TimeSlot {
        int start;
        int end;
        public TimeSlot(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {

        FinishAllCourses finishAllCourses = new FinishAllCourses();
        TimeSlot l1 =  new TimeSlot(1, 2);
        TimeSlot l2 =  new TimeSlot(2, 3);
        TimeSlot l3 =  new TimeSlot(4, 5);

        TimeSlot l4 =  new TimeSlot(1, 2);
        TimeSlot l5 =  new TimeSlot(1, 4);
        TimeSlot l6 =  new TimeSlot(4, 5);

        TimeSlot l7 =  new TimeSlot(1, 2);
        TimeSlot l8 =  new TimeSlot(2, 3);
        TimeSlot l9 =  new TimeSlot(4, 5);

        Course c1 = new Course();
        c1.add(l1);
        c1.add(l2);
        c1.add(l3);

        Course c2 = new Course();
        c2.add(l4);
        c2.add(l5);
        c2.add(l6);

        Course c3 = new Course();
        c3.add(l7);
        c3.add(l8);
        c3.add(l9);

        List<Course> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);

        List<TimeSlot> timeSlots = finishAllCourses.select(list);
    }

    public List<TimeSlot> select(List<Course> courses) {
        List<TimeSlot> ret = new ArrayList<>();
        dfs(courses, 0, ret);
        return ret;
    }

    private boolean dfs(List<Course> courses, int cur, List<TimeSlot> sol) {
        if (cur == courses.size()) {
            return true;
        }
        for (TimeSlot slot : courses.get(cur).slots) {
            //if (hasOverlap(slot, sol)) continue;
            int index = binarySearch(sol, slot);
            TimeSlot pre = null;
            TimeSlot next = null;
            if (index == 0) {
                next = sol.get(index);
                if (slot.end > next.start) continue;
            } else if (index == -1) {
                index = 0;
            } else if (index < sol.size()){
                pre = sol.get(index - 1);
                next = sol.get(index);
                if (slot.start > pre.end || slot.end > next.start) continue;
            }
            sol.add(index, slot);
            if (dfs(courses, cur + 1, sol)) return true;
            sol.remove(index);
        }
        return false;
    }

    private boolean hasOverlap(TimeSlot cur, List<TimeSlot> sol) {
        for (TimeSlot pre : sol) {
            if (cur.end > pre.start && cur.start < pre.end) {
                return true;
            }
        }
        return false;
    }

    private int binarySearch(List<TimeSlot> list, TimeSlot target) {
        if (list.isEmpty()) return -1;
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            TimeSlot cur = list.get(mid);
            if (target.start > cur.start) {
                l = mid + 1;
            } else if (target.start < cur.start) {
                r = mid;
            } else return mid;
        }
        return target.start <= list.get(l).start ? l : l + 1;
    }
}
