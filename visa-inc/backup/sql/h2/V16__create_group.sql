--   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
CREATE TABLE TEAM (
  ID BIGINT NOT NULL  AUTO_INCREMENT,
  NAME VARCHAR(255),
  PRIMARY KEY (ID),
  UNIQUE KEY TEAM_NAME (NAME)
);


CREATE TABLE USERS_TEAMS (
   USER_ID BIGINT NOT NULL,
   TEAM_ID BIGINT NOT NULL,
   PRIMARY KEY (USER_ID, TEAM_ID)
);

ALTER TABLE USERS_TEAMS ADD CONSTRAINT USERS_TEAMS_TEAM
   FOREIGN KEY (TEAM_ID) REFERENCES TEAM;

ALTER TABLE USERS_TEAMS ADD CONSTRAINT USERS_TEAMS_USER
   FOREIGN KEY (USER_ID) REFERENCES USER_;

-- TEAM_AUTHORITIES
CREATE TABLE TEAM_AUTHORITIES (
   TEAM_ID BIGINT NOT NULL,
   AUTHORITY_ID BIGINT NOT NULL,
   PRIMARY KEY (TEAM_ID, AUTHORITY_ID)
);

ALTER TABLE TEAM_AUTHORITIES ADD CONSTRAINT TEAM_AUTHORITIES_TEAM
   FOREIGN KEY (TEAM_ID) REFERENCES TEAM;

ALTER TABLE TEAM_AUTHORITIES ADD CONSTRAINT TEAM_AUTHORITIES_AUTHORITY
   FOREIGN KEY (AUTHORITY_ID) REFERENCES AUTHORITY;
   