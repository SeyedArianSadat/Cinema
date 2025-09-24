CREATE TABLE customers
(
    customer_id  NUMBER PRIMARY KEY,
    name         NVARCHAR2(50) NOT NULL,
    family       NVARCHAR2(50) NOT NULL,
    phone_number VARCHAR2(20) UNIQUE NOT NULL,
    age          NUMBER(3)
);
create sequence customer_seq start with 1 increment by 1;

CREATE TABLE users
(
    user_id  NUMBER PRIMARY KEY ,
    username NVARCHAR2(50) UNIQUE NOT NULL,
    password NVARCHAR2(50) NOT NULL,
    role     NVARCHAR2(50) DEFAULT 'customer',
    customer_id NUMBER NOT NULL ,
    CONSTRAINT fk_user_customer FOREIGN KEY(customer_id) REFERENCES  customers(customer_id)
);

create sequence user_seq start with 1 increment by 1;

CREATE TABLE payments
(
    payment_id   number  PRIMARY KEY,
    amount       number(10,2) NOT NULL,
    payment_type VARCHAR2(50) NOT NULL,
    payment_time TIMESTAMP DEFAULT SYSTIMESTAMP
);

create sequence payment_seq start with 1 increment by 1;

CREATE TABLE saloons
(
    saloon_id  NUMBER PRIMARY KEY,
    name       NVARCHAR2(50) NOT NULL,
    address    NVARCHAR2(50),
    capacity   NUMBER,
    manager_id NUMBER,
    CONSTRAINT fk_saloon_manager FOREIGN KEY (manager_id) REFERENCES users (user_id)
);

create sequence saloon_seq start with 1 increment by 1;

CREATE TABLE events

(
    event_id         NUMBER  PRIMARY KEY ,
    title            NVARCHAR2(50) NOT NULL,
    description      NVARCHAR2(50) NOT NULL,
    event_start_date TIMESTAMP NOT NULL ,
    event_end_date   TIMESTAMP NOT NULL,
    duration         FLOAT,
    saloon_id        NUMBER,
    CONSTRAINT fk_event_saloon FOREIGN KEY (saloon_id) REFERENCES saloons (saloon_id)
);

create sequence event_seq start with 1 increment by 1;

CREATE TABLE artists

(
    artist_id number primary key,
    name      NVARCHAR2(50) NOT NULL,
    family    NVARCHAR2(50) NOT NULL,
    category  NVARCHAR2(50) NOT NULL,
    genre     NVARCHAR2(50) NOT NULL
);
create sequence artist_seq start with 1 increment by 1;

create table event_artists
(
    event_id  NUMBER NOT NULL ,
    artist_id NUMBER NOT NULL ,
    primary key (event_id,artist_id),
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events (event_id),
    CONSTRAINT fk_artist FOREIGN KEY (artist_id) REFERENCES events (artist_id)
);

CREATE TABLE seats
(
    seat_id      NUMBER PRIMARY KEY,
    saloon_id    NUMBER NOT NULL,
    seat_number  NVARCHAR2(20) NOT NULL,
    is_available NUMBER(1) DEFAULT 1,
    CONSTRAINT fk_seat_saloon  FOREIGN KEY (saloon_id)REFERENCES saloons (saloon_id)
);

create sequence seat_seq start with 1 increment by 1;

CREATE TABLE tickets
(
    ticket_id   NUMBER PRIMARY KEY,
    event_id    NUMBER NOT NULL,
    customer_id NUMBER NOT NULL,
    seat_id     NUMBER NOT NULL,
    payment_id  NUMBER ,
    ticket_time TIMESTAMP DEFAULT SYSTIMESTAMP ,

    CONSTRAINT fk_ticket_event FOREIGN KEY (event_id)REFERENCES events (event_id),

    CONSTRAINT fk_ticket_customer FOREIGN KEY (customer_id)REFERENCES customers (customer_id),

    CONSTRAINT fk_ticket_seat FOREIGN KEY (seat_id)REFERENCES seats (seat_id),

    CONSTRAINT fk_ticket_payment FOREIGN KEY (payment_id)REFERENCES payments (payment_id)
);

create sequence ticket_seq start with 1 increment by 1;
