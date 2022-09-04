create table if not exists  orderschema.product (
     id bigint not null generated always as identity,
     description character varying (255) ,
     product_status varchar(30),
     created_date timestamp,
     last_modified_date timestamp,
     constraint product_pkey PRIMARY KEY (id)
)