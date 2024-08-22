CREATE TABLE IF NOT EXISTS USERS
(
    userid    INT PRIMARY KEY auto_increment,
    username  VARCHAR(20) UNIQUE,
    salt      VARCHAR,
    password  VARCHAR,
    firstname VARCHAR(20),
    lastname  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES
(
    noteid          INT PRIMARY KEY auto_increment,
    notetitle       VARCHAR(20),
    notedescription VARCHAR(1000),
    userid          INT,
    foreign key (userid) references USERS (userid)
);

CREATE TABLE IF NOT EXISTS FILES
(
    fileId      INT PRIMARY KEY auto_increment,
    filename    VARCHAR,
    contenttype VARCHAR,
    filesize    VARCHAR,
    userid      INT,
    filedata    BLOB,
    foreign key (userid) references USERS (userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS
(
    credentialid INT PRIMARY KEY auto_increment,
    url          VARCHAR(100),
    username     VARCHAR(30),
    key          VARCHAR,
    password     VARCHAR,
    userid       INT,
    foreign key (userid) references USERS (userid)
);


INSERT INTO USERS(username, salt, password, firstname, lastname)
VALUES ('quang', 'ao+njZPy8EgLeloW1dUblw==',
        'W8o0EOVZg6njYS/saJjZy8pThdR5PgAcHM9wmFmEzaUpW7Q4EbVt1hxExIKMydDBNXO4nC4MnvSPX7UZ37PB7Jw+o1pwVzkc/Zyk75ifC//VjD1SVmYA5IL6jEUwawLDDZQ5u2fT3mg4vgAWQ9PvZoFOG15ZqdmzYH/5V3U8RRfZIX2Fpdtp8uKeKw9FDci6YV193UcEh3EBQyNOC0NF1hIB83Ufcps9EiDJMeVLK6Ss2kLi8e9bw9F+tW9DH44GBMFoTa34MDX+CBOx6w4Lu30thVxn6kGhmv08EiphzwS+zrR/a02HxS5Jv9mKz323zqlKS+cP9Q8wjrm7i6lRpg==',
        'quang', 'quang');