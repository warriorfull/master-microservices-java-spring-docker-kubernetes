drop table if exists cards;

create table cards (
  card_id int not null auto_increment,
  card_number varchar(100) not null,
  customer_id int not null,
  card_type varchar(100) not null,
  total_limit int not null,
  amount_used int not null,
  available_amount int not null,
  create_dt date default null,
  primary key (card_id)  
);  

insert into cards 
  (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
  values
  ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());
  
  insert into cards 
  (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
  values
  ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());
  
  insert into cards 
  (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
  values
  ('2350XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());