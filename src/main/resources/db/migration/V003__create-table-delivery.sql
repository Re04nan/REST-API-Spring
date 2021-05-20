create table delivery(

	id bigint not null auto_increment,
	client_id bigint not null,
	rate decimal(10,2) not null,
	status varchar(20) not null,
	date_order datetime not null,
	date_completed datetime,
	
	addressee_name varchar(60) not null,
	addressee_place varchar(255) not null,
	addressee_number varchar(30) not null,
	addressee_complement varchar(80),
	addressee_district varchar(30) not null,
	
	primary key (id)
) engine = InnoDB;

alter table delivery add constraint fk_delivery_client
foreign key (client_id) references client (id); 