create table userform(

    id int not null auto_increment,
    firstname varchar(255),
    lastname varchar(255),
    email varchar(255),
    password varchar(255),

    primary key (id)
);

create table Topic(

    id int not null auto_increment,
    name varchar(255),

    primary key (id)
);


create table questions(

    id int not null auto_increment,
    question varchar(255),


    primary key (id)


);

create table Option(

     id int not null auto_increment,
     optiontext varchar(255),
     correct boolean,
     question_id int,

     primary key (id),
     foreign key(question_id) references questions(id)
);

create table usertopicscore(

     id int not null auto_increment,
     score int,
     user_id int,
     topic_id int,

     primary key (id),
     foreign key(user_id) references userform(id),
     foreign key(topic_id) references Topic(id)
);

create table topic_questions(

    topic_id int not null,
    question_id int not null

);

create table user_subscriptions(

    user_id int not null,
    subscription_id int not null

);


alter table user_subscriptions add constraint test foreign key(user_id) references userform(id);
alter table user_subscriptions add constraint test1 foreign key(subscription_id) references Topic(id);

alter table topic_questions add constraint test2 foreign key(topic_id) references Topic(id);
alter table topic_questions add constraint test3 foreign key(question_id) references questions(id);