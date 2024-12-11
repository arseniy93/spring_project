package com.javarush.jira.bugtracking.task;//package com.javarush.jira.bugtracking.task;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "task_tag")
//@Getter
//@Setter
//@NoArgsConstructor
//public class TaskTag {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    private Task task;
//
//    @Column(name = "tag")
//    private String tags;
//}
////TODO check generation tagtask class