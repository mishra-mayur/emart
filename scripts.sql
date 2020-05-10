####### Database Setup #######
create database e_mart; #create database
create user 'admin'@'%' identified by 'AdminPassword'; #create user and password
grant all on e_mart.* to 'admin'@'%'; #give all access of database to created user