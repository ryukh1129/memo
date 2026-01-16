show databases;

CREATE DATABASE MVACADEMY;

USE MVACADEMY;
SHOW TABLES;

CREATE TABLE major (
                       major_code varchar(100) primary key,
                       major_name varchar(100) not null,
                       tutor_name varchar(100) not null
);

create table student (
                         student_code varchar(100) primary key,
                         name varchar(100) not null,
                         birth varchar(8) not null,
                         gender varchar(1) not null,
                         phone varchar(11) not null,
                         major_code varchar(100) not null,
                         foreign key(major_code) references major(major_code)
);

create table exam (
                      student_code varchar(100) not null,
                      exam_seq int not null,
                      score decimal(10, 2) not null,
                      result varchar(1) not null
);

show tables;

alter table exam add primary key(student_code, exam_seq);
alter table exam add constraint exam_fk_student_code
    foreign key(student_code)
        references student(student_code);

# DML
# INSERT
desc major;

insert into major values('m1', '스프링', '남병관');
select * from major;

insert into major values('m2', '노드', '남병관');
insert into major values('m3', '플라스크', '홍길동');
insert into major values('m4', '루비온레일즈', '으하하');
insert into major values('m5', '라라벨', '오승혁');
insert into major values('m6', '리액트', '김성실');
insert into major values('m7', '뷰', '남개그');
insert into major values('m8', '앵귤러', '이차차');

desc student;

insert into student values('s1', '최원빈', '20100101', 'M', '01001010101', 'm1');
INSERT INTO STUDENT VALUES('s2', '강준규', '20220501', 'M', '01000000002', 'm1')
                         ,('s3', '김영철', '20220711', 'M', '01000000003', 'm1');
INSERT INTO STUDENT VALUES('s4', '예상기', '20220408', 'M', '01000000004', 'm6'),
                          ('s5', '안지현', '20220921', 'F', '01000000005', 'm6'),
                          ('s6', '이대호', '20221111', 'M', '01000000006', 'm7'),
                          ('s7', '정주혜', '20221117', 'F', '01000000007', 'm8'),
                          ('s8', '고미송', '20220623', 'F', '01000000008', 'm6'),
                          ('s9', '이용우', '20220511', 'M', '01000000009', 'm2'),
                          ('s10', '심선아', '20220504', 'F', '01000000010', 'm8'),
                          ('s11', '변정섭', '20220222', 'M', '01000000020', 'm2');

insert into student(student_code, name, gender, major_code, birth, phone) values('s12', '정오빈', 'M', 'm3', '20030202', '01000000030');

INSERT INTO STUDENT VALUES('s13', '김가은', '20220121', 'F', '01000000030', 'm1');
INSERT INTO STUDENT(student_code, name, gender, major_code, birth, phone) VALUES('s14', '김동현', 'M', 'm4', '20030202', '01000000020'),
                                                                                ('s15', '박은진', 'F', 'm3', '20221101', '01000000040');
INSERT INTO STUDENT(student_code, name, birth, gender, phone, major_code) VALUES('s16', '정영호', '20221105', 'M', '01000000050', 'm5');
INSERT INTO STUDENT(student_code, name, gender, major_code, birth, phone) VALUES('s17', '박가현', 'F', 'm7', '20030202', '01000000020');
INSERT INTO STUDENT(student_code, name, birth, gender, phone, major_code) VALUES('s18', '박용태', '20220508', 'M', '01000000060', 'm6');
INSERT INTO STUDENT VALUES('s19', '김예지', '20220505', 'F', '01000000070', 'm2');
INSERT INTO STUDENT VALUES('s20', '윤지용', '20220909', 'M', '01000000080', 'm3');
INSERT INTO STUDENT VALUES('s21', '손윤주', '20220303', 'F', '01000000090', 'm6');

select * from student;

desc exam;

INSERT INTO EXAM VALUES('s1', 1, 8.5, 'P'),
                       ('s1', 2, 9.5, 'P'),
                       ('s1', 3, 3.5, 'F'),
                       ('s2', 1, 8.2, 'P'),
                       ('s2', 2, 9.5, 'P'),
                       ('s2', 3, 7.5, 'P'),
                       ('s3', 1, 9.3, 'P'),
                       ('s3', 2, 5.3, 'F'),
                       ('s3', 3, 9.9, 'P'),
                       ('s4', 1, 8.4, 'P'),
                       ('s5', 1, 9.5, 'P'),
                       ('s5', 2, 3.5, 'F'),
                       ('s6', 1, 8.3, 'P'),
                       ('s7', 1, 9.2, 'P'),
                       ('s7', 2, 9.9, 'P'),
                       ('s7', 3, 3.6, 'F'),
                       ('s8', 1, 8.4, 'P'),
                       ('s9', 1, 9.7, 'P'),
                       ('s10', 1, 8.4, 'P'),
                       ('s10', 2, 9.8, 'P'),
                       ('s10', 3, 8.4, 'P'),
                       ('s11', 1, 8.6, 'P'),
                       ('s12', 1, 9.2, 'P'),
                       ('s13', 1, 8.1, 'P'),
                       ('s13', 2, 9.5, 'P'),
                       ('s13', 3, 2.1, 'F'),
                       ('s14', 1, 9.2, 'P'),
                       ('s15', 1, 9.7, 'P'),
                       ('s15', 2, 1.7, 'F'),
                       ('s16', 1, 8.4, 'P'),
                       ('s17', 1, 9.3, 'P'),
                       ('s17', 2, 9.9, 'P'),
                       ('s17', 3, 1.3, 'F'),
                       ('s18', 1, 9.9, 'P'),
                       ('s19', 1, 9.4, 'P'),
                       ('s19', 2, 8.9, 'P'),
                       ('s19', 3, 7.4, 'F'),
                       ('s20', 1, 8.1, 'P'),
                       ('s20', 2, 6.4, 'F'),
                       ('s21', 1, 9.5, 'P'),
                       ('s21', 2, 8.8, 'P'),
                       ('s21', 3, 8.2, 'P');

insert into student values('s0', '안될애', '20202020', 'M', '01092922929', 'm1');
update student set major_code = 'm2' where student_code = 's0';

select * from student where student_code = 's0';

delete from student where student_code = 's0';

select * from student;

select name, major_code from student where student_code = 's1';


show tables;
desc exam;

# JOIN 사용
-- 명시적 조인
-- JOIN 키워드를 사용하여 두 테이블을 조인한다.
-- ON 절을 사용하여 조인 조건을 지정한다.
-- 가독성이 좋고 복잡한 조인 조건을 명확하게 표현할 수 있기 때문
-- INNER JOIN, LEFT JOIN, RIGHT JOIN 등 다양한 조인 방식을 사용할 수 있다. (기본은 INNER = 교집합)
select s.name, s.major_code, m.major_name
from student as s
         join major as m
              on s.major_code = m.major_code;

-- 암시적 조인 (권장X)
-- 테이블들을 , 콤마로 나열..
-- WHERE을 사용해서 조인 조건 설정을 함
-- 간단하고, 코드가 짧고, 빠르게 작성 가능.. 하지만 복잡해지면 가독성이 떨어진다. 다양한 조인 방식을 사용할 수 없다.
select s.name, s.major_code, m.major_name
from student s, major m
where s.major_code = m.major_code;