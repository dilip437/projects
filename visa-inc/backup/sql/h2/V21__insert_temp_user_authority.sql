INSERT INTO TEMP_USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (125, 'temp', '$2a$08$BkfhdoTuVNXgUV6sxFy.Ke/eUhANLfOBs72bcWSZZXS01zlpYQhme', FALSE, FALSE, FALSE, TRUE);

INSERT INTO TEMP_USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (125, 2);