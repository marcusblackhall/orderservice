create schema if not exists orderschema;

create table if not exists  orderschema.order_header (
     id bigint not null generated always as identity,
     customer_name character varying (255) ,
     constraint order_header_pkey PRIMARY KEY (id)
)