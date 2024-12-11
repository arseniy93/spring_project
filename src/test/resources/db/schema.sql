CREATE TABLE IF NOT EXISTS PROJECT
(
    ID
    bigserial
    primary
    key,
    CODE
    varchar
(
    32
) not null
    constraint UK_PROJECT_CODE unique,
    TITLE varchar
(
    1024
) not null,
    DESCRIPTION varchar
(
    4096
) ,
    TYPE_CODE varchar
(
    32
) not null,
    STARTPOINT timestamp,
    ENDPOINT timestamp,
    PARENT_ID bigint,
    constraint FK_PROJECT_PARENT foreign key
(
    PARENT_ID
) references PROJECT
(
    ID
) on delete cascade
    );

CREATE TABLE IF NOT EXISTS MAIL_CASE
(
    ID
    bigserial
    primary
    key,
    EMAIL
    varchar
(
    255
) not null,
    NAME varchar
(
    255
) not null,
    DATE_TIME timestamp not null,
    RESULT varchar
(
    255
) not null,
    TEMPLATE varchar
(
    255
) not null
    );

CREATE TABLE IF NOT EXISTS SPRINT
(
    ID
    bigserial
    primary
    key,
    STATUS_CODE
    varchar
(
    32
) not null,
    STARTPOINT timestamp,
    ENDPOINT timestamp,
    TITLE varchar
(
    1024
) not null,
    PROJECT_ID bigint not null,
    constraint FK_SPRINT_PROJECT foreign key
(
    PROJECT_ID
) references PROJECT
(
    ID
) on delete cascade
    );
CREATE TABLE IF NOT EXISTS REFERENCE
(
    ID
    bigserial
    primary
    key,
    CODE
    varchar
(
    32
) not null,
    REF_TYPE smallint not null,
    ENDPOINT timestamp,
    STARTPOINT timestamp,
    TITLE varchar
(
    1024
) not null,
    AUX varchar,
    constraint UK_REFERENCE_REF_TYPE_CODE unique
(
    REF_TYPE,
    CODE
)
    );



CREATE TABLE IF NOT EXISTS USERS
(
    ID
    bigserial
    primary
    key,
    DISPLAY_NAME
    varchar
(
    32
) not null
    constraint UK_USERS_DISPLAY_NAME unique,
    EMAIL varchar
(
    128
) not null
    constraint UK_USERS_EMAIL unique,
    FIRST_NAME varchar
(
    32
) not null,
    LAST_NAME varchar
(
    32
),
    PASSWORD varchar
(
    128
) not null,
    ENDPOINT timestamp,
    STARTPOINT timestamp
    );

CREATE TABLE IF NOT EXISTS PROFILE
(
    ID
    bigint
    primary
    key,
    LAST_LOGIN
    timestamp,
    LAST_FAILED_LOGIN
    timestamp,
    MAIL_NOTIFICATIONS
    bigint,
    constraint
    FK_PROFILE_USERS
    foreign
    key
(
    ID
) references USERS
(
    ID
) on delete cascade
    );

CREATE TABLE IF NOT EXISTS CONTACT
(
    ID
    bigint
    not
    null,
    CODE
    varchar
(
    32
) not null,
    "VALUE1" varchar
(
    256
) not null,
    primary key
(
    ID,
    CODE
),
    constraint FK_CONTACT_PROFILE foreign key
(
    ID
) references PROFILE
(
    ID
) on delete cascade
    );

CREATE TABLE IF NOT EXISTS TASK
(
    ID
    bigserial
    primary
    key,
    TITLE
    varchar
(
    1024
) not null,
    DESCRIPTION varchar
(
    4096
) ,
    TYPE_CODE varchar
(
    32
) not null,
    STATUS_CODE varchar
(
    32
) not null,
    PRIORITY_CODE varchar
(
    32
) ,
    ESTIMATE integer,
    UPDATED timestamp,
    PROJECT_ID bigint not null,
    SPRINT_ID bigint,
    PARENT_ID bigint,
    STARTPOINT timestamp,
    ENDPOINT timestamp,
    constraint FK_TASK_SPRINT foreign key
(
    SPRINT_ID
) references SPRINT
(
    ID
) on delete set null,
    constraint FK_TASK_PROJECT foreign key
(
    PROJECT_ID
) references PROJECT
(
    ID
)
  on delete cascade,
    constraint FK_TASK_PARENT_TASK foreign key
(
    PARENT_ID
) references TASK
(
    ID
)
  on delete cascade
    );

CREATE TABLE IF NOT EXISTS ACTIVITY
(
    ID
    bigserial
    primary
    key,
    AUTHOR_ID
    bigint
    not
    null,
    TASK_ID
    bigint
    not
    null,
    UPDATED
    timestamp,
    COMMENT
    varchar
(
    4096
),
--     history of task field change
    TITLE varchar
(
    1024
),
    DESCRIPTION varchar
(
    4096
) ,
    ESTIMATE integer,
    TYPE_CODE varchar
(
    32
),
    STATUS_CODE varchar
(
    32
),
    PRIORITY_CODE varchar
(
    32
),
    constraint FK_ACTIVITY_USERS foreign key
(
    AUTHOR_ID
) references USERS
(
    ID
),
    constraint FK_ACTIVITY_TASK foreign key
(
    TASK_ID
) references TASK
(
    ID
) on delete cascade
    );

CREATE TABLE IF NOT EXISTS TASK_TAG
(
    TASK_ID
    bigint
    not
    null,
    TAG
    varchar
(
    32
) not null,
    constraint UK_TASK_TAG unique
(
    TASK_ID,
    TAG
),
    constraint FK_TASK_TAG foreign key
(
    TASK_ID
) references TASK
(
    ID
) on delete cascade
    );

CREATE TABLE IF NOT EXISTS USER_BELONG
(
    ID
    bigserial
    primary
    key,
    OBJECT_ID
    bigint
    not
    null,
    OBJECT_TYPE
    smallint
    not
    null,
    USER_ID
    bigint
    not
    null,
    USER_TYPE_CODE
    varchar
(
    32
) not null,
    STARTPOINT timestamp,
    ENDPOINT timestamp,
    constraint FK_USER_BELONG foreign key
(
    USER_ID
) references USERS
(
    ID
)
    );
create unique index UK_USER_BELONG on USER_BELONG (OBJECT_ID, OBJECT_TYPE, USER_ID, USER_TYPE_CODE);
create index IX_USER_BELONG_USER_ID on USER_BELONG (USER_ID);

CREATE TABLE IF NOT EXISTS ATTACHMENT
(
    ID
    bigserial
    primary
    key,
    NAME
    varchar
(
    128
) not null,
    FILE_LINK varchar
(
    2048
) not null,
    OBJECT_ID bigint not null,
    OBJECT_TYPE smallint not null,
    USER_ID bigint not null,
    DATE_TIME timestamp,
    constraint FK_ATTACHMENT foreign key
(
    USER_ID
) references USERS
(
    ID
)
    );

CREATE TABLE IF NOT EXISTS USER_ROLE
(
    USER_ID
    bigint
    not
    null,
    ROLE
    smallint
    not
    null,
    constraint
    UK_USER_ROLE
    unique
(
    USER_ID,
    ROLE
),
    constraint FK_USER_ROLE foreign key
(
    USER_ID
) references USERS
(
    ID
) on delete cascade
    );