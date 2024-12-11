package com.javarush.jira;

import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.common.internal.config.AppProperties;
import org.apache.commons.collections4.set.TransformedSet;
import org.hibernate.mapping.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableCaching
public class JiraRushApplication {

    public static void main(String[] args) {

        SpringApplication.run(JiraRushApplication.class, args);
//        Task task=new Task();
//        TaskTag taskTag=new TaskTag();
//        taskTag.setTask(task);
//        taskTag.setTags("lol");
//        Set<TaskTag> taskTags=new HashSet<>();
//        taskTags.add(taskTag);
//
//        task.setTags(taskTags);


    }
}
