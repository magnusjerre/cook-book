insert into public.cook_book_user (external_user_provider_id) values('auth0|5f54b03cbb9ffd0067ed5c45');
insert into public.cook_book_user (external_user_provider_id) values('auth0|5f55236af83add00671a85fd');

insert into recipe (name, owner_id) values ('Dagens burger', 1);
insert into section (recipe_id, name, sequence_number) values(1, 'Kjøttet', 1);
insert into ingredient (section_id, name, measure_unit, measurement) values(1, 'Kjøttdeig', 'gram', 400);
insert into section_step (section_id, sequence_number, description) values(1, 1, 'Rør om kjøttdeig med litt salt og pepper.');

insert into recipe (name, owner_id) values ('Lasagne', 2);
insert into section (recipe_id, name, sequence_number) values(2, 'Rød saus', 1);
insert into ingredient (section_id, name, measure_unit, measurement) values(2, 'Kjøttdeig', 'gram', 400);
insert into section_step (section_id, sequence_number, description) values(2, 1, 'Brun kjøttdeig i stekepannen.');
insert into section_step (section_id, sequence_number, description) values(2, 2, 'Ha i hakkede tomater.');

insert into recipe (name, owner_id) values ('Brownie', 2);
insert into section (recipe_id, name, sequence_number) values(3, 'Det tørre', 1);
insert into ingredient (section_id, name, measure_unit, measurement) values(3, 'Hvetemel', 'gram', 400);
insert into ingredient (section_id, name, measure_unit, measurement) values(3, 'Sukker', 'gram', 100);
insert into section_step (section_id, sequence_number, description) values(3, 1, 'Ha Hvetemel og sukker i en bolle.');