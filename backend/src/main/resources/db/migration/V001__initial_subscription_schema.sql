create table subscription_types (
    id ${uuid_type} primary key,
    name varchar(80) not null unique,
    active boolean not null default true,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table payment_methods (
    id ${uuid_type} primary key,
    kind varchar(32) not null,
    display_name varchar(100) not null,
    nickname varchar(80),
    institution_name varchar(100),
    active boolean not null default true,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table subscriptions (
    id ${uuid_type} primary key,
    name varchar(120) not null,
    description varchar(500),
    monthly_cost ${money_type} not null,
    currency varchar(3) not null,
    status varchar(32) not null,
    type_id ${uuid_type} not null,
    payment_method_id ${uuid_type} not null,
    started_at date,
    cancelled_at date,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint fk_subscriptions_type foreign key (type_id) references subscription_types (id),
    constraint fk_subscriptions_payment_method foreign key (payment_method_id) references payment_methods (id)
);

create index idx_subscriptions_status on subscriptions (status);
create index idx_subscriptions_type_id on subscriptions (type_id);
create index idx_subscriptions_payment_method_id on subscriptions (payment_method_id);
