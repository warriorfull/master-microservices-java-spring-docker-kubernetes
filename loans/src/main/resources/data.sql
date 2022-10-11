drop table if exists loans;

create table loans (
  loan_number int not null auto_increment,
  customer_id int not null,
  start_dt date not null,
  loan_type varchar(50) not null,
  total_loan int not null,
  amount_paid int not null,
  outstanding_amount int not null,
  create_dt date default null,
  primary key (loan_number)
);

insert into loans 
  (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
  values (1, '2022-10-13', 'Home', 200000, 50000, 150000, '2020-10-13'); 
  
insert into loans 
  (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
  values (1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');
  
insert into loans 
  (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
  values (1, '2022-02-14', 'Home', 50000, 10000, 40000, '2020-02-14'); 
  
insert into loans 
  (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
  values (1, '2020-02-14', 'Personal', 10000, 3500, 6500, '2020-02-14');