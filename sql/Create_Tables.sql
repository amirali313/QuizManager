CREATE TABLE QUESTION ( ID INT PRIMARY KEY AUTO_INCREMENT,
			QUESTION VARCHAR(255) UNIQUE NOT NULL,
			DIFFICULTY INT,
			TOPICID INT,
			CH1 VARCHAR(255),
			CH2 VARCHAR(255),
			CH3 VARCHAR(255),
			CH4 VARCHAR(255),
			ANSWER INT );

CREATE TABLE TOPICS ( ID INT PRIMARY KEY AUTO_INCREMENT,
		      TOPIC1 VARCHAR(255),
                      TOPIC2 VARCHAR(255) );

ALTER TABLE QUESTION
ADD FOREIGN KEY(ID) REFERENCES TOPICS(ID);
