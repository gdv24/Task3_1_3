insert into role (name) value ('ROLE_USER');
insert into role (name) value ('ROLE_ADMIN');
insert into users (age, email, name, password, username) VALUES (30,'admin@mail.ru','admin','admin','admin');
insert into users_role (users_id, roles_id) VALUES (1,2);
insert into users (age, email, name, password, username) VALUES (30,'user@mail.ru','user','user','user');
insert into users_role (users_id, roles_id) VALUES (2,1);