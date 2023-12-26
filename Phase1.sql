

create database java_project;

use java_project;

CREATE TABLE ROLE (
    RoleId INT PRIMARY KEY,
    RoleName VARCHAR(255)
);

CREATE TABLE USER (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
	RoleId INT,
    UserName VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Cellphone_No VARCHAR(15),
    Email VARCHAR(255),
    Name VARCHAR(255),
    Address TEXT,
    Status INT,
    FOREIGN KEY (RoleId) REFERENCES ROLE(RoleId)
);

CREATE TABLE PRODUCT (
    ProductId INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT,
    ProductName VARCHAR(255) NOT NULL,
    SerialNo VARCHAR(50),
    PurchaseDate DATE,
    FOREIGN KEY (UserId) REFERENCES USER(UserId)
);




CREATE TABLE CompanyProduct (
    CompanyProductId INT AUTO_INCREMENT PRIMARY KEY,
    CompanyProductName VARCHAR(255) NOT NULL,
    Category VARCHAR(50),
    Price DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE CLAIM (
    ClaimId INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT,
    ProductId INT,
    ClaimDate DATE,
    Description TEXT,
    Status VARCHAR(50),
    FOREIGN KEY (UserId) REFERENCES USER(userid),
    FOREIGN KEY (ProductId) REFERENCES Product(productid)
);



INSERT INTO ROLE (RoleId, RoleName) VALUES
(1, 'user'),
(2, 'admin');

INSERT INTO CompanyProduct (CompanyProductName, Category, Price) VALUES
('Laptop Pro X', 'Electronics', 1299.99),
('Smartphone Max 12', 'Electronics', 799.99),
('Coffee Maker Deluxe', 'Appliances', 89.99),
('Premium Office Chair', 'Furniture', 249.99),
('Fitness Tracker Ultra', 'Fitness', 69.99);


INSERT INTO USER (RoleId, UserName, Password, Email, Name, Status) VALUES
(1, 'Gurlal123', 'humber', 'gurlal@admin.com', 'Gurlal Singh', 1),
(1, 'Jais456', 'humber', 'jais@admin.com', 'Jais Jose', 1),
(1, 'Yogesh789', 'humber', 'yogesh@admin.com', 'Yogesh Kakkar', 1);

INSERT INTO CO



select * from ROLE;
select * from USER;






