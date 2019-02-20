
INSERT INTO TOPICS ( TOPIC1, TOPIC2 ) 
VALUES ( 'JDBC', 'Java' );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER )
VALUES ( 'Type IV JDBC driver is a driver ___________', 2, 'which is written in C++', 'which requires an intermediate layer', 'which communicates through Java sockets', 'which translates JDBC function calls into API not native to DBMS', 3);
UPDATE QUESTION
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 )
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 ) 
VALUES ( 'Abstract Class', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER )
VALUES ( 'Which of the following is FALSE about abstract classes in Java?', 1 , 'If we derive an abstract class and do not implement all the abstract methods, then the derived class should also be marked as abstract using ABSTRACT keyword.', 'Abstract classes can have constructors.', 'A class can be made abstract without any abstract method.', 'A class can inherit from multiple abstract classes.', 4);
UPDATE QUESTION
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 )
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );

INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Abstract Class', 'Identifiers' );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'Which of the following is used to make an Abstract class?', 3, 'Making atleast one member function as pure virtual function', 'Making atleast one member function as virtual function', 'Declaring as Abstract class using virtual keyword', 'Declaring as Abstract class using static keyword', 1 );
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Abstract Class', 'Identifiers' );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'We can make a class abstract by', 1, 'Declaring it abstract using the virtual keyword', 'Making at least one member function as virtual function', 'Making at least one member function as pure virtual function', 'Making at least one member function as pure virtual function', 3);
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Data Types', 'Keywords' );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'Which of the following statements is/are TRUE regarding JAVA ? (a) Constants that cannot be changed are declared using the ‘static’ keyword. (b) A class can only inherit one class but can implement multiple interfaces.', 2, 'Only (a) is TRUE.', 'Only (b) is TRUE.', 'Both (a) and (b) are TRUE.', 'Neither (a) nor (b) are TRUE.', 2);
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Arrays', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'Which of the following is FALSE about arrays on Java', 1, 'A java array is always an object', 'Length of array can be changed after creation of array', 'Arrays in Java are always allocated on heap', 'None of the above', 2);
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Exceptions', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'Which of these is a super class of all errors and exceptions in the Java language?', 3, 'RunTimeExceptions', 'Throwable', 'Catchable', 'None of the above', 2);
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Exceptions', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'The built-in base class in Java, which is used to handle all exceptions is', 2, 'Throwable', 'Error', 'Exception', 'Raise', 1 );
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Packages', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'Which of the following is/are advantages of packages?', 3, 'Packages avoid name clashes', 'Classes, even though they are visible outside their package, can have fields visible to packages only', 'We can have hidden classes that are used by the packages, but not visible outside.', 'All of the above', 4 );
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Final', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'What is the use of final keyword in Java?', 2, 'When a class is made final, a sublcass of it can not be created.', 'When a method is final, it can not be overridden.', 'When a variable is final, it can be assigned value only once.', 'All of the above', 4 );
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );


INSERT INTO TOPICS ( TOPIC1, TOPIC2 )  
VALUES ( 'Final', NULL );
INSERT INTO QUESTION ( QUESTION, DIFFICULTY, CH1, CH2, CH3, CH4, ANSWER ) 
VALUES ( 'In Java, when we implement an interface method, it must be declared as:', 1, 'Private', 'Protected', 'Public', 'Friend', 3 );
UPDATE QUESTION 
SET TOPICID = ( SELECT ID FROM TOPICS ORDER BY ID DESC LIMIT 1 ) 
WHERE ID = ( SELECT ID FROM QUESTION ORDER BY ID DESC LIMIT 1 );

