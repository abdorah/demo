
    create table authorities (
       id uuid not null,
        authority varchar(255),
        username varchar(255),
        primary key (id)
    );

    create table demo (
       id uuid not null,
        content varchar(255),
        primary key (id)
    );

    create table users (
       id uuid not null,
        details varchar(255),
        enabled boolean not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    );

    INSERT INTO public.authorities (id, authority, username) VALUES('7c1b3397-bb75-4ad6-90c8-26c1d4851351', 'typical authority', 'kotbymo@gmail.com');
    INSERT INTO public.demo (id, "content") VALUES('5829a7ac-afc1-453a-9a7e-f4a7a2a266c1', 'typical content');
    INSERT INTO public.users (id, details, enabled, "password", username) VALUES('91bdaa39-b7cb-4133-8476-65f44a154aa3', 'typical user', true, '$2a$10$2LKHCQXOYQd0pI.pWIsU3OLj0nNXgc5hl1JFMGdp8ntaynwMieeF.', 'kotbymo@gmail.com');
