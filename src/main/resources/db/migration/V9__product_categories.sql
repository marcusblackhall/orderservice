
create table if not exists  orderschema.category (
     id bigint not null generated always as identity,
     description character varying (255) ,
     created_date timestamp,
     last_modified_date timestamp,
     constraint category_pkey PRIMARY KEY (id)
);

-- create join table product category

create table if not exists orderschema.product_category (
   product_id bigint not null,
   category_id bigint not null,
   primary key (product_id,category_id),
   constraint product_id_key foreign key(product_id) references orderschema.product(id),
   constraint category_id_key foreign key(category_id) references orderschema.category(id)

);

insert into product (description, created_date, last_modified_date ) values ('prod1',now(),now());
insert into product (description, created_date, last_modified_date ) values ('prod2',now(),now());
insert into product (description, created_date, last_modified_date ) values ('prod3',now(),now());

insert into category (description, created_date, last_modified_date ) values ('cat1',now(),now());
insert into category (description, created_date, last_modified_date ) values ('cat2',now(),now());
insert into category (description, created_date, last_modified_date ) values ('cat3',now(),now());


insert into product_category (product_id,category_id)
  select p.id, c.id from product p, category c
  where p.description = 'prod1' and c.description = 'cat1';

insert into product_category (product_id,category_id)
  select p.id, c.id from product p, category c
  where p.description = 'prod2' and c.description = 'cat1';

insert into product_category (product_id,category_id)
  select p.id, c.id from product p, category c
  where p.description = 'prod1' and c.description = 'cat3';

insert into product_category (product_id,category_id)
  select p.id, c.id from product p, category c
  where p.description = 'prod4' and c.description = 'cat3';


