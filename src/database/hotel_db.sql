CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE rooms (
    room_no INT PRIMARY KEY,
    room_type VARCHAR(30),
    price_per_day DOUBLE,
    status VARCHAR(20) DEFAULT 'Available'
);

CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(50),
    phone VARCHAR(15),
    room_no INT,
    check_in_date DATE,
    check_out_date DATE,
    total_amount DOUBLE DEFAULT 0,
    status VARCHAR(20) DEFAULT 'Booked',
    FOREIGN KEY (room_no) REFERENCES rooms(room_no)
);

INSERT INTO rooms (room_no, room_type, price_per_day, status) VALUES
(101, 'Single', 1500, 'Available'),
(102, 'Double', 2500, 'Available'),
(103, 'Deluxe', 4000, 'Available'),
(104, 'Single', 1500, 'Available');


