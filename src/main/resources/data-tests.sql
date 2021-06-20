delete from user_book;
delete from book_author;
delete from author;
delete from book;
delete from user;

insert into author values(29,'Anand@xyz.com','Anand','9988775509');
insert into author values(33,'Gopi@xyz.com','Gopi','9988775509');
insert into book values (17,201,'Physics',29,'Science','DEMOUSER',CURRENT_TIMESTAMP());
insert into book_author values (1,CURRENT_TIMESTAMP(),29,17);
insert into user values (111,"vinay@xyz.com","9988775509","vinay");
insert into user values (112,"akhil@xyz.com","9988775509","akhil");
insert into user_book values(17,112,CURRENT_TIMESTAMP(),'CR');

commit;


