
alter table orderschema.order_header
   drop column customer_name;

alter table orderschema.order_header

   add column customer_id bigint,
   add constraint  order_header_customer_fk  foreign key (customer_id) references orderschema.customer(id);


