insert into muscle_group (id, name) values ('a5926568-0eb2-43d3-b4b5-435c56303b2e', 'Core');
insert into muscle_group (id, name) values ('559c03ed-98ba-4c0c-9e51-5dab46baff1a', 'Arme');
insert into muscle_group (id, name) values ('b69b80d2-fe63-4fde-ad54-7edbbab55236', 'Rücken');
insert into muscle_group (id, name) values ('e56d0340-daa6-4523-bb61-dd047eefa340', 'Brust');
insert into muscle_group (id, name) values ('0f13721d-13a4-4f50-90f4-dc3ec8966498', 'Oberschenkel');

insert into exercise (id, name, description, instructions)
values ('3b092329-89c6-4bdb-bba7-24341dcf5ba1', 'Kniebeugen', 'Eine Übung für die Beine',
        'Stell dich mit den Füßen schulterbreit auseinander und beuge die Knie, als ob du dich hinsetzen würdest.'),
       ('a752da83-1281-4eba-9298-da041f249bb9', 'Liegestütze', 'Eine Übung für die Brust und Arme',
        'Leg dich auf den Bauch, stütze dich auf die Hände und die Zehen, und senke deinen Körper bis fast zum Boden.'),
       ('3122e162-032f-4cc0-8389-89aa794c3896', 'Bauchpressen', 'Eine Übung für die Bauchmuskeln',
        'Lieg auf dem Rücken, die Beine angewinkelt, und hebe den Oberkörper an, als ob du dich aufsetzen würdest.'),
       ('3e02cf6b-e22b-4385-9b14-94ccc150fd7e', 'Klimmzüge', 'Eine Übung für den oberen Rücken',
        'Häng dich an eine Stange und zieh deinen Körper nach oben, bis dein Kinn über der Stange ist.'),
       ('481b1e54-d834-43b8-bb55-ff10bf21d42c', 'Plank', 'Eine Übung für die Körpermitte',
        'Stütz dich auf die Unterarme und die Zehen, halte deinen Körper in einer geraden Linie.');

insert into exercise_muscle_group (exercise_id, muscle_group_id)
values ('3b092329-89c6-4bdb-bba7-24341dcf5ba1', '0f13721d-13a4-4f50-90f4-dc3ec8966498'), -- Kniebeugen -> Oberschenkel
       ('a752da83-1281-4eba-9298-da041f249bb9', '559c03ed-98ba-4c0c-9e51-5dab46baff1a'), -- Liegestütz -> Arme
       ('a752da83-1281-4eba-9298-da041f249bb9', 'e56d0340-daa6-4523-bb61-dd047eefa340'), -- Liegestütz -> Brust
       ('3e02cf6b-e22b-4385-9b14-94ccc150fd7e', 'b69b80d2-fe63-4fde-ad54-7edbbab55236'), -- Klimmzüge -> Rücken
       ('3e02cf6b-e22b-4385-9b14-94ccc150fd7e', '559c03ed-98ba-4c0c-9e51-5dab46baff1a'); -- Klimmzüge -> Arme
