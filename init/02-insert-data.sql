insert into category(name) values('Business');
insert into category(name) values('Technical');
insert into category(name) values('Private');


insert into task(name, description, date_from, date_to, category_id)
values('Task 1 Business', 'Task 1 Business Description', CURRENT_DATE, CURRENT_DATE + 5, 1);
insert into task(name, description, date_from, date_to, category_id)
values('Task 2 Business', 'Task 2 Business Description', CURRENT_DATE, CURRENT_DATE + 5, 1);
insert into task(name, description, date_from, date_to, category_id)
values('Task 3 Business', 'Task 3 Business Description', CURRENT_DATE, CURRENT_DATE + 5, 1);
insert into task(name, description, date_from, date_to, category_id)
values('Task 4 Technical', 'Task 4 Technical Description', CURRENT_DATE, CURRENT_DATE + 10, 2);
insert into task(name, description, date_from, date_to, category_id)
values('Task 5 Technical', 'Task 5 Technical Description', CURRENT_DATE, CURRENT_DATE + 10,2);
insert into task(name, description, date_from, date_to, category_id)
values('Task 6 Technical', 'Task 6 Technical Description', CURRENT_DATE, CURRENT_DATE + 10, 2);
insert into task(name, description, date_from, date_to, category_id)
values('Task 7 Private', 'Task 7 Private Description', CURRENT_DATE, CURRENT_DATE + 20, 3);
insert into task(name, description, date_from, date_to, category_id)
values('Task 8 Private', 'Task 8 Private Description', CURRENT_DATE, CURRENT_DATE + 20, 3);
insert into task(name, description, date_from, date_to, category_id)
values('Task 9 Private', 'Task 9 Private Description', CURRENT_DATE, CURRENT_DATE + 20, 3);
