

insert into user(id, email, first_name, last_name, phone, password, role, username, address_id)
VALUES ('1', 'emmastone@hollywood.com', 'Emma', 'Stone', '555-025618', 'emmastone', 'auth_user', 'emmastone', null );

insert into project(id, title, customer_id)
VALUES ('1', 'La la land', null),
       ('2', 'The amazing Spider-man 2', null),
       ('3', 'Cruella', null);

insert into user_projects(users_id, projects_id)
VALUES('1', 1), ('1', 2), ('1', 3);

insert into activity(id, cost_per_hour, title, project_id)
VALUES ('1', null, 'Table read', 1),
       ('2', null, 'Practice dancemoves', 1),
       ('3', null, 'Sing practice', 1),
       ('4', null, 'Table read', 2),
       ('5', null, 'Read script with Andrew Garfield', 2),
       ('6', null, 'Table read', 3),
       ('7', null, 'Meet make up artist', 3),
       ('8', null, 'Clothing testing', 3);


