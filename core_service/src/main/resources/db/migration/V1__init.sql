create table categories (
    id          bigserial primary key,
    title       varchar(255) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title) values ('Food'), ('Others');

create table products
(
    id bigserial primary key,
    title varchar(255),
    category_id bigint references categories (id),
    price double precision,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price, category_id)
    values
        ('milk', 80.0, 1),
        ('Bread', 25.0, 1),
        ('Cheese', 300.0, 1),
        ('Car', 3000000.0, 2),
        ('Chocolate', 30.0, 1),
        ('TV', 30000.0, 2),
        ('Toy car', 300.0, 2),
        ('Cup', 300.0, 2),
        ('Cake', 3000.0, 1),
        ('Tea', 300.0, 1),
        ('Coffee', 600.0, 1),
        ('Pen', 100.0, 2),
        ('Lamp', 100.0, 2),
        ('Ball', 600.0, 2),
        ('Laptop', 60000.0, 2),
        ('Butter', 300.0, 1);

create table orders
(
    id          bigserial primary key,
    username     varchar(255) not null,
    total_price double precision not null,
    address      varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   double precision not null,
    price               double precision not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);