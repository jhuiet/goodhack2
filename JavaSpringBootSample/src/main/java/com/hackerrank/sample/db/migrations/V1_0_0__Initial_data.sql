
CREATE TABLE IF NOT EXIST customer (
    id INTEGER NOT NULL PRIMARY KEY,
    customerName VARCHAR(50), 
    contactNumber INTEGER, 
    address VARCHAR(50),
    gender VARCHAR(20)
);


CREATE TABLE IF NOT EXIST  sku (
    id INTEGER NOT NULL PRIMARY KEY,
    productName VARCHAR(50),
    productLabel VARCHAR(50),
    inventoryOnHand INTEGER,
    minQtyReq INTEGER,
    price DEC
);



CREATE TABLE IF NOT EXIST  vendor (
    id INTEGER NOT NULL PRIMARY KEY,
    vendorName VARCHAR(50),
	vendorContactNo INTEGER,
	vendorEmail VARCHAR(50),
	vendorUsername VARCHAR(50),
	vendorAddress VARCHAR(50)
);



INSERT INTO customer
( id, customerName, contactNumber, address, gender)
VALUES
(1, 'bob', 1553332222, 'grove street', 'male' ),
(2, 'joe', 166647725, 'wallace woods', 'male');

INSERT INTO sku 
(id, productName, productLabel, inventoryOnHand, minQtyReq, price)
VALUES
(1, 'hammer' , 'good hammer' , 22, 1 , 12.42),
(2, 'football', 'good tossin', 42, 3, 6.99);

INSERT INTO vendor
(id, vendorName, vendorContactNo, vendorEmail, vendorUsername, vendorAddress)
VALUES
(1, 'walgreens', 500500500, 'walgreens@google.com', 'wall-g', 'west sidddeeee!'),
(2, 'joesCrabShack', 166647725, 'crabbs@gmail.com', 'crustyc', 'north beach');