package com.javarush.jira;//package com.javarush.jira;
//
//import com.javarush.jira.bugtracking.Handlers;
//import com.javarush.jira.bugtracking.UserBelongRepository;
//import com.javarush.jira.bugtracking.sprint.SprintRepository;
//import com.javarush.jira.bugtracking.task.Task;
//import com.javarush.jira.bugtracking.task.TaskRepository;
//import com.javarush.jira.bugtracking.task.TaskService;
//import com.javarush.jira.bugtracking.task.TaskTag;
//import com.javarush.jira.bugtracking.task.mapper.TaskExtMapper;
//import com.javarush.jira.bugtracking.task.mapper.TaskFullMapper;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@AllArgsConstructor
//public class Main {
//    private  static Handlers.TaskExtHandler handler;
//    private static Handlers.ActivityHandler activityHandler;
//    private static TaskFullMapper fullMapper;
//    private static SprintRepository sprintRepository;
//    private static TaskExtMapper extMapper;
//    private static UserBelongRepository userBelongRepository;
//    private static TaskService taskService;
//    Main main=new Main();
//
//    public static void main(String[] args) {
//        taskService = new TaskService(handler, activityHandler, fullMapper,
//                sprintRepository, extMapper, userBelongRepository);
//
////        Task task=new Task();
////        TaskTag taskTag=new TaskTag();
////        taskTag.setTask(task);
////        taskTag.setTags("lol");
////        Set<TaskTag> taskTags=new HashSet<>();
////        taskTags.add(taskTag);
////
////        task.setTags(taskTags);
//    }
//}
