create table if not exists com_user_mst
(
    user_id        serial constraint com_user_mst_pk primary key,
    email          varchar(30),
    user_nm        varchar(30),
    use_yn         varchar(1),
    create_user_id integer,
    update_user_id integer,
    create_dt      timestamp with time zone default CURRENT_TIMESTAMP not null,
    update_dt      timestamp with time zone
);

comment on table com_user_mst is '사용자 마스터';
comment on column com_user_mst.user_id is '사용자 ID';
comment on column com_user_mst.email is '이메일';
comment on column com_user_mst.user_nm is '고객명';
comment on column com_user_mst.create_user_id is '생성자 ID';
comment on column com_user_mst.update_user_id is '수정자 ID';
comment on column com_user_mst.create_dt is '생성일시';
comment on column com_user_mst.update_dt is '수정일시';

insert into com_user_mst( email , user_nm, use_yn, create_user_id ) values ( '1@a.com' ,'aaa', 'Y', 0);
insert into com_user_mst( email , user_nm, use_yn, create_user_id ) values ( '2@a.com' ,'bbb', 'Y', 0);
insert into com_user_mst( email , user_nm, use_yn, create_user_id ) values ( '3@a.com' ,'ccc', 'Y', 0);
insert into com_user_mst( email , user_nm, use_yn, create_user_id ) values ( '4@a.com' ,'ddd', 'Y', 0);
insert into com_user_mst( email , user_nm, use_yn, create_user_id ) values ( '5@a.com' ,'eee', 'N', 0);