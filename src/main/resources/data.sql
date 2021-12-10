INSERT INTO ROLE (id, role) VALUES (1, 'ADMIN');
INSERT INTO ROLE (id, role) VALUES (2, 'SELLER');
INSERT INTO ROLE (id, role) VALUES (3, 'BUYER');

INSERT INTO USER (id, firstname, middlename, lastname, username, password) VALUES (1, "Admin", "", "MiniMart", "admin@minimart.com", "Admin123");
INSERT INTO USERS_ROLE (user_id, role_id) VALUES (1, 1);

create sequence if not exists USER start with 2;