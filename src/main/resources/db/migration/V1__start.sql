create sequence logid;

alter sequence logid
  owner to postgres;

create table log
(
  id           integer not null
    constraint log_pkey
    primary key,
  player       integer,
  lvl          integer,
  stamina      integer,
  nameofstep   varchar(50),
  nameofaction varchar(50)
);

alter table log
  owner to postgres;