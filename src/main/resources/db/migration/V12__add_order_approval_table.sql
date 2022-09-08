create table orderschema.order_approval (

  id bigint not null generated always as identity,
  approved_by character varying(255),
  created_date timestamp,
  last_modified_date timestamp,
  constraint order_approval_pk PRIMARY KEY (id)

);

alter table orderschema.order_header
   add column order_approval_id bigint,
   add constraint order_header_approval_fk foreign key(order_approval_id) references orderschema.order_approval(id);

