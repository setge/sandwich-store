-- 식자재 정보 저장 테이블
create table if not exists Ingredient(
    id varchar (4) not null ,
    name varchar (25) not null,
    type varchar (10) not null
);

-- 삭자재를 이용하여 생성한 샌드위치 정보 저장 테이블
create table if not exists Sandwich(
    id identity ,
    name varchar (50) not null,
    createAt timestamp not null
);

-- 샌드위치와 식자재 테이블간의 관계
-- 샌드위치 테비을의 각 행에 대하여 하나 이상의 샌드위치와 식자래가 포함된다.
create table if not exists Sandwich_Ingredients(
    sandwich bigint not null,
    ingredient varchar (4) not null
);

alter table Sandwich_Ingredients add foreign key(sandwich) references Sandwich(id);
alter table Sandwich_Ingredients add foreign key(ingredient) references Ingredient(id);

-- 샌드위치 주문 정보 저장 테이블
create table if not exists Sandwich_Order(
    id identity ,
    deliveryName varchar (50) not null,
    deliveryStreet varchar (50) not null,
    deliveryPhone varchar (50) not null ,
    ccNumber varchar (16) not null,
    ccExpiration varchar (5) not null,
    ccCVV varchar (3) not null,
    placedAt timestamp not null
 );

-- 샌드위치와 주문 태아블간의 관계
-- 하나의 주문에는 하나 이상의 샌드위치가 포함될 수 있다.
create table if not exists Sandwich_Order_Sandwichs(
    sandwichOrder bigint not null,
    sandwich bigint not null
);

alter table Sandwich_Order_Sandwichs add foreign key (sandwichOrder) references Sandwich_Order(id);
alter table Sandwich_Order_Sandwichs add foreign key(sandwich) references Sandwich(id);
