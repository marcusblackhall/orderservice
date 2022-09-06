create table if not exists orderschema.order_line (

  id bigint  not null generated always as identity,
  quantity_ordered int,
  created_date timestamp,
  last_modified_date timestamp,
  order_header_id bigint,
  constraint order_line_pkey PRIMARY KEY (id),
  constraint order_header_pk foreign key(order_header_id) references orderschema.order_header(id)



)