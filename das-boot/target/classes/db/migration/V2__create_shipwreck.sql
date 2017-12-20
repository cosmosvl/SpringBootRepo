/*! 
 * COMMENTS:
 * This CREATE statement only work for H2 database because initially we configured
 * Spring boot to use H2 as the database source.
 * Reason: Flyway SQL is database dependent
 * 
 * */

CREATE TABLE SHIPWRECK(
	ID INT AUTO_INCREMENT,
	NAME VARCHAR(255),
	DESCRIPTION VARCHAR(2000),
	CONDITION VARCHAR(255),
	DEPTH INT,
	LATITUDE DOUBLE,
	LONGITUDE DOUBLE,
	YEAR_DISCOVERED INT
);