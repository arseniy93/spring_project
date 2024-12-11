-- users ----------------------
-- Удаление данных из таблиц
DELETE
FROM USER_ROLE;
DELETE
FROM CONTACT;
DELETE
FROM PROFILE;
DELETE
FROM ACTIVITY;
DELETE
FROM TASK;
DELETE
FROM SPRINT;
DELETE
FROM PROJECT;
DELETE
FROM USERS;

-- Создание и сброс последовательностей

-- TASK_ID_SEQ
CREATE SEQUENCE IF NOT EXISTS TASK_ID_SEQ;
ALTER SEQUENCE TASK_ID_SEQ RESTART WITH 1;

-- ACTIVITY_ID_SEQ
CREATE SEQUENCE IF NOT EXISTS ACTIVITY_ID_SEQ;
ALTER SEQUENCE ACTIVITY_ID_SEQ RESTART WITH 1;


-- SPRINT_ID_SEQ
CREATE SEQUENCE IF NOT EXISTS SPRINT_ID_SEQ;
ALTER SEQUENCE SPRINT_ID_SEQ RESTART WITH 1;

-- PROJECT_ID_SEQ
CREATE SEQUENCE IF NOT EXISTS PROJECT_ID_SEQ;
ALTER SEQUENCE PROJECT_ID_SEQ RESTART WITH 1;

-- USERS_ID_SEQ
CREATE SEQUENCE IF NOT EXISTS USERS_ID_SEQ;
ALTER SEQUENCE USERS_ID_SEQ RESTART WITH 1;

insert into USERS (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DISPLAY_NAME)
values ('user@gmail.com', '{noop}password', 'userFirstName', 'userLastName', 'userDisplayName'),
       ('admin@gmail.com', '{noop}admin', 'adminFirstName', 'adminLastName', 'adminDisplayName'),
       ('guest@gmail.com', '{noop}guest', 'guestFirstName', 'guestLastName', 'guestDisplayName'),
       ('manager@gmail.com', '{noop}manager', 'managerFirstName', 'managerLastName', 'managerDisplayName');

-- 0 DEV
-- 1 ADMIN
-- 2 MANAGER

insert into USER_ROLE (USER_ID, ROLE)
values (1, 0),
       (2, 0),
       (2, 1),
       (4, 2);

insert into PROFILE (ID, LAST_FAILED_LOGIN, LAST_LOGIN, MAIL_NOTIFICATIONS)
values (1, null, null, 49),
       (2, null, null, 14),
       (3, null, null, 14),
       (4, null, null, 14);

insert into CONTACT (ID, CODE, "VALUE1")
values (1, 'skype', 'userSkype'),
       (1, 'mobile', '+01234567890'),
       (1, 'website', 'user.com'),
       (2, 'github', 'adminGitHub'),
       (2, 'tg', 'adminTg'),
       (3, 'github', 'guestGitHub'),
       (3, 'tg', 'guestTg'),
       (4, 'github', 'managerGitHub'),
       (4, 'tg', 'managerTg');

-- project ----------------------
insert into PROJECT (code, title, description, type_code, parent_id)
values ('PR1', 'PROJECT-1', 'test project 1', 'task_tracker', null),
       ('PR2', 'PROJECT-2', 'test project 2', 'task_tracker', 1),
       ('PR3', 'PROJECT-3', 'test project 3', 'task_tracker', 2),
       ('PR4', 'PROJECT-4', 'test project 4', 'scrum', null);

-- sprint ----------------------
insert into SPRINT (status_code, startpoint, endpoint, title, project_id)
values ('finished', '2023-05-01 08:05:10', '2023-05-07 17:10:01', 'SP-1.001', 1),
       ('active', '2023-05-01 08:06:00', null, 'SP-1.002', 1),
       ('active', '2023-05-01 08:07:00', null, 'SP-1.003', 1),
       ('planning', '2023-05-01 08:08:00', null, 'SP-1.004', 1),
       ('active', '2023-05-10 08:06:00', null, 'SP-2.001', 2),
       ('planning', '2023-05-10 08:07:00', null, 'SP-2.002', 2),
       ('planning', '2023-05-10 08:08:00', null, 'SP-2.003', 2);

-- task ----------------------
insert into TASK (TITLE, TYPE_CODE, STATUS_CODE, PROJECT_ID, SPRINT_ID, STARTPOINT)
values ('Data', 'epic', 'in_progress', 1, 1, '2023-05-15 09:05:10'),
       ('Trees', 'epic', 'in_progress', 1, 1, '2023-05-15 12:05:10'),
       ('task-3', 'task', 'ready_for_test', 2, 5, '2023-06-14 09:28:10'),
       ('task-4', 'task', 'ready_for_review', 2, 5, '2023-06-14 09:28:10'),
       ('task-5', 'task', 'todo', 2, 5, '2023-06-14 09:28:10'),
       ('task-6', 'task', 'done', 2, 5, '2023-06-14 09:28:10'),
       ('task-7', 'task', 'canceled', 2, 5, '2023-06-14 09:28:10');

-- activity ----------------------
insert into ACTIVITY(AUTHOR_ID, TASK_ID, UPDATED, COMMENT, TITLE, DESCRIPTION, ESTIMATE, TYPE_CODE, STATUS_CODE,
                     PRIORITY_CODE)
values (1, 1, '2023-05-15 09:05:10', null, 'Data', 'Trees desc', 3, 'epic', 'in_progress', 'low'),
       (2, 1, '2023-05-15 12:25:10', null, 'Data', 'Trees desc', null, null, null, 'normal'),
       (1, 1, '2023-05-15 14:05:10', null, 'Data', 'Trees desc', 4, null, null, null),
       (1, 2, '2023-05-15 12:05:10', null, 'Trees', 'Trees desc', 4, 'epic', 'in_progress', 'normal');

-- user belong ----------------------
insert into USER_BELONG (OBJECT_ID, OBJECT_TYPE, USER_ID, USER_TYPE_CODE, STARTPOINT, ENDPOINT)
values (1, 2, 2, 'task_developer', '2023-06-14 08:35:10', '2023-06-14 08:55:00'),
       (1, 2, 2, 'task_reviewer', '2023-06-14 09:35:10', null),
       (1, 2, 1, 'task_tester', '2023-06-14 15:20:00', null),
       (2, 2, 1, 'task_tester', '2023-06-10 16:37:00', null);

-- reference ----------------------
INSERT INTO REFERENCE (CODE, TITLE, REF_TYPE, AUX)
VALUES ('task', 'Task', 2, NULL),
       ('story', 'Story', 2, NULL),
       ('bug', 'Bug', 2, NULL),
       ('epic', 'Epic', 2, NULL),
       ('planning', 'Planning', 4, NULL),
       ('active', 'Active', 4, NULL),
       ('finished', 'Finished', 4, NULL),
       ('scrum', 'Scrum', 1, NULL),
       ('task_tracker', 'Task tracker', 1, NULL),
       ('skype', 'Skype', 0, NULL),
       ('tg', 'Telegram', 0, NULL),
       ('mobile', 'Mobile', 0, NULL),
       ('phone', 'Phone', 0, NULL),
       ('website', 'Website', 0, NULL),
       ('linkedin', 'LinkedIn', 0, NULL),
       ('github', 'GitHub', 0, NULL),
       ('critical', 'Critical', 7, NULL),
       ('high', 'High', 7, NULL),
       ('normal', 'Normal', 7, NULL),
       ('low', 'Low', 7, NULL),
       ('neutral', 'Neutral', 7, NULL),
       ('assigned', 'Assigned', 6, '1'),
       ('three_days_before_deadline', 'Three days before deadline', 6, '2'),
       ('two_days_before_deadline', 'Two days before deadline', 6, '4'),
       ('one_day_before_deadline', 'One day before deadline', 6, '8'),
       ('deadline', 'Deadline', 6, '16'),
       ('overdue', 'Overdue', 6, '32'),
       ('todo', 'ToDo', 3, '21');