class CoursesGraph {
    
    Map<Integer, Course> courses;
    
    CoursesGraph(int numCourses) {
        this.courses = new HashMap<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            this.courses.put(i, new Course(i));
        }
    }
}

class Course {
    int id;
    Set<Course> dependentCourses = new HashSet<>();               // Set of incoming edges
    int dependeeCourses;                                          // # of outgoing edges
    
    Course(int id) {
        this.id = id;
    }
}

class Solution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        CoursesGraph graph = buildCoursesGraph(numCourses, prerequisites);
        return canFinish(graph);
    }
    
    private CoursesGraph buildCoursesGraph(int numCourses, int[][] prerequisites) {
        CoursesGraph graph = new CoursesGraph(numCourses);
        
        for (int i = 0; i < prerequisites.length; i++) {
            int dependentCourseID = prerequisites[i][0];
            Course dependentCourse = graph.courses.get(dependentCourseID);
            
            int dependeeeCourseID = prerequisites[i][1];
            Course dependeeCourse = graph.courses.get(dependeeeCourseID);
            
            dependentCourse.dependeeCourses++;
            dependeeCourse.dependentCourses.add(dependentCourse);
        }
        
        return graph;
    }
    
    private boolean canFinish(CoursesGraph graph) {
        
        if (graph.courses.size() == 0) {
            return true;
        }
        
        List<Course> coursesToDelete = new LinkedList<>();
        
        for (Map.Entry<Integer, Course> c: graph.courses.entrySet()) {
            Course course = c.getValue();
            if (course.dependeeCourses == 0) {                              // If course has no dependencies, remove it (-1 from all the courses that depend on him)
                for (Course dependee: c.getValue().dependentCourses) {
                    dependee.dependeeCourses--;
                }
                coursesToDelete.add(c.getValue());
            }
        }
        
        if (coursesToDelete.size() == 0) {                                  // If no course to delete is found in the current step, there is a loop
            return false;
        }
        
        for (Course c: coursesToDelete) {
            graph.courses.remove(c.id);
        }
        
        return canFinish(graph);
    }
}