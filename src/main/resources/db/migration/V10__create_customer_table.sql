
create table orderschema.customer (

   id bigint not null generated always as identity,
     customer_name character varying (255),
     email character varying (255) ,
     phone varchar(30),
     created_date timestamp,
     last_modified_date timestamp,

     address varchar(30),
     city varchar(30),
     state varchar(30),
     zip_code varchar(30),

     constraint customer_pkey PRIMARY KEY (id)

)