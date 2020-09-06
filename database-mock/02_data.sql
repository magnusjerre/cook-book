insert into public.cook_book_user (external_user_provider_id) values('aaaaa');

insert into recipe (name, owner_id) values ('Dagens burger', 1);

insert into recipe_group (recipe_id, name, sequence_number) values(1, 'Kjøttet', 1);

insert into ingredient (recipe_group_id, name, measure_unit, measurement) values(1, 'Kjøttdeig', 'gram', 400);

insert into recipe_group_step (sequence_number, description) values(1, 'Rør om kjøttdeig med litt salt og pepper.');
