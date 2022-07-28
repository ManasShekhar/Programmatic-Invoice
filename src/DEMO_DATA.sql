USE store;

-- Registration
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES('Alankar' , '29/08/2000' , 'alankarsharma298@gmail.com', 9958828835, 'Male', 'Dwarka sector 23 New Delhi', 'Alankar11@');
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES ('Ayush Luthra', '24/10/2000', 'ayushluthra24@gmail.com', 9958136675 ,'Male', 'Geeta Colony', 'Ayush24@');
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES(" Keshav Garg","15/02/2000","keshavgrg@gmail.com","9643775880","Male","Rohini","keshav*45");
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES("Manas Shekhar","29/03/2000","manasshekhar17@gmail.com","7011049944","Male","A-83/F-1 Dilshad Colony Delhi","Manas@1234");
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES("Om Gupta","20/02/2002","omg.cool20@gmail.com","9354061155","Male","Naveen shahdara","Omg@1234");
INSERT INTO Registration  (Name, DOB, Email, Mobile_Number, Gender, Address, Password) VALUES("Saurabh Wadhwa","04/12/2000","saurabhwadhwa96@gmail.com","8700513704","Male","b-205 indira nagar","Saurabh@123");


-- Product Details
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH01', 'iPhone 12 (128GB)', 70900);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH02', 'iPhone XR (64GB)', 34999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH03', 'iPhone 13 (128GB)', 70990);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH04', 'iPhone 11 (128GB)', 79900);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH05', 'iPhone 11 Pro (128GB)', 54900);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH06', 'iPhone 12 Mini(128GB)', 57900);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBIPH07', 'iPhone XR (128GB)', 40999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH08', 'Samsung Galaxy F62', 23995);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH09', 'Samsung Galaxy M52 5G', 27800);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH10', 'Samsung Galaxy M32', 14999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH11', 'Samsung Galaxy M31s', 22990);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH12', 'Samsung Galaxy A52', 23499);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH13', 'Samsung Galaxy S21 Ultra', 96900);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH14', 'Samsung Galaxy F22', 12999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('MBSGH15', 'Samsung Galaxy M21', 12999);

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('WMSGH16', 'Samsung 6kg Fully Automatic', 25999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('WMSGH17', 'Samsung 10kg Fully Automatic', 40999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('WMWPH18', 'Whirlpool 7Kg Semi Automatic', 12999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('WMWPH19', 'Whirlpool 8Kg Fully Automatic', 27999);
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES('WMWPH20', 'Whirlpool 5Kg Fully Automatic', 18999);

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT01HP111","HP Spectre x360","55999");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT02HP112","HP Omen 15","35989.98");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT03HP113","HP GA Gaming17","49999");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT04HP114","HP Pavilion12","75000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT05HP115","HP Chromebook 11a","60000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT01DELL551","DELL Inspiron 15","47000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT02DELL552","DELL Vostro 3510","64999");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT03DELL553","DELL Xps 9500 ","56000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT04DELL554","DELL Alienware X17","75840");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT05DELL555","DELL Latitude 13","23990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT01MI671","MI Notebook pro ","27462");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT02MI672","MI Notebook14","53990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT03MI673","MI horizon","70000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT04MI674","MI Notebook15 pro","84990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("LT05MI675","MI Notebook Grand9","61990");

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("TV95LG01","Wondertainment 4k","45000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("TV96MI02", "Horizon Series 4K","70990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("TV97OnePlus03","50’’ UK Series 4K","55000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("TV98Sony04","55’’Full HD Smart TV ","25989.65");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("TV99TCL05","32’’ FHD Android ","55990");

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("AC11DK02","Diakin D25","45990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("AC12LG02","LG N80","45990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("AC13OG03","ogeneral","55000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("AC14HT04","Hitachi 65A","75000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("AC15NP05","Napoleon 987CD","65990");

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HS94DG01","Devialet Gemini Bluetooth","32998");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HS95BB02","Boat Bassheads","2200");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HS96BR03","Boat Rockerz 550","999");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HS97BB04","Boat Bassheads 100 ","379");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HS98SN05","Sony WH-1000XM4 Bluetooth","26990");

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HT11US22","Usha 30002","1199");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HT12OO23","Orpat 220","1299");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HT13OR24","Orient 302","4000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HT14BJ25","Bajaj 230","2999");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("HT15HV26","Havells","3450");

INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("RG31SS01","Samsung G18","70000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("RG31WP02","Whirlpool X50","89990");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("RG31PS03","Panasonic K11","55000");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("RG31HR04","Haier C45","89090.89");
INSERT INTO ProductRecords (SU, Product_Name, Cost) VALUES ("RG31LG05","LG 20S","45000");



