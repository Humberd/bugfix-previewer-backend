drop table if exists bugfix;
drop table if exists sprint;

create table sprint
(
    id     varchar(32)  not null primary key,
    number integer      not null,
    name   varchar(255) not null
);

create table bugfix
(
    id              varchar(32)  not null primary key,
    sprint_id       varchar(32)  not null references sprint (id) on delete cascade,
    ticket          varchar(255) not null,
    name            varchar(255) not null,
    bug_preview_url varchar(255),
    fix_preview_url varchar(255)
);

insert into sprint (id, number, name)
values ('sprint-32', 32, 'Sprint 32 (Maciej)');

insert into bugfix(id, sprint_id, ticket, name, bug_preview_url, fix_preview_url)
values ('bugfix-1', 'sprint-32', 'SPS-3322', 'Broken button',
        'https://ridecell-mobile-storage.s3.eu-central-1.amazonaws.com/Dreamworld+-+Ice+Age_+No+Time+For+Nuts+4+D.mp4',
        'https://testmobilehub-hosting-mobilehub-825908255.s3.eu-central-1.amazonaws.com/example-video-1.mp4'),
       ('bugfix-2', 'sprint-32', 'SPS-4387', 'View crashes', null, null);
