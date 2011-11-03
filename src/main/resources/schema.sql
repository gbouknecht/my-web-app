create table account (
    id        integer      primary key,
    userid    varchar( 50) not null,
    firstname varchar(150) not null,
    lastname  varchar(150) not null,

    constraint account_userid_unique unique (userid)
);

create sequence id_seq;
