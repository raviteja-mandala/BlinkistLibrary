delete from user_book;
delete from author;
delete from book;
delete from user;
insert into author values(29,'Anand@xyz.com','Anand','123434');
insert into book values (17,201,'Physics',29,'Science','DEMOUSER',CURRENT_TIMESTAMP());
insert into user values (111,"vinay@xyz.com","98076","vinay");
insert into user values (112,"akhil@xyz.com","98076","akhil");
insert into user_book values(17,112,CURRENT_TIMESTAMP(),'CR');

commit;


