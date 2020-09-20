insert into public.cook_book_user (external_user_provider_id) values('auth0|5f54b03cbb9ffd0067ed5c45');

insert into recipe (name, owner_id) values ('Dagens burger', 1);

insert into section (recipe_id, name, sequence_number) values(1, 'Kjøttet', 1);

insert into ingredient (section_id, name, measure_unit, measurement) values(1, 'Kjøttdeig', 'gram', 400);

insert into section_step (section_id, sequence_number, description) values(1, 1, 'Rør om kjøttdeig med litt salt og pepper.');
