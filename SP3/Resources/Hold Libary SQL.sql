SELECT * FROM HoldDatabase.Hold;
	DROP TABLE IF EXISTS Hold;
    
CREATE TABLE Hold (
    hold VARCHAR (255),
    spiller1 VARCHAR(255),
    spiller2 VARCHAR(255), 
    result TINYINT(255),
    PRIMARY KEY (hold)

)ENGINE = InnoDB;

INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("Silver Hawks","Emil","Emma",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("Knockout Stingers","Chris","Oscar",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("Bad Heroes","Jeppe","Daniel",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("German Gangsters","Micki","Oliver",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("Copenhagen Blasters","Emilie","Ida",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("Yellow Legends","Christina","Anna",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("The Assaulted Nuts","Anton","David",false);
INSERT INTO HoldDatabase.Hold (hold,spiller1,spiller2,result) VALUES ("The Canadian Slayers","Rosa","Torben",false);


CREATE TABLE Date (
    date VARCHAR (255)
)

CREATE TABLE Deadline (
    deadline VARCHAR (255)
)