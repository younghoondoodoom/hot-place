-- auto-generated definition
create table places
(
    id                     bigint auto_increment
        primary key,
    seoul_common_data_no   bigint       not null comment '서울공공데이터 번호',
    seoul_common_data_code varchar(20)  not null comment '서울공공데이터 코드',
    name                   varchar(40)  not null comment '이름',
    english_name           varchar(100) not null comment '영어 이름',
    latitude               decimal      not null comment '위도',
    longitude              decimal      not null comment '경도',
    category               varchar(30)  not null comment '카테고리'
)
    comment '장소';
