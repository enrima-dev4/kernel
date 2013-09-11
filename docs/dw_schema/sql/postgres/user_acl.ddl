
CREATE TABLE AUTHORITIES
(

	ID_USER               INTEGER NOT NULL ,
	ID_ROLE               INTEGER NOT NULL 
).



CREATE UNIQUE  INDEX XPKAUTHORITIES ON AUTHORITIES 
(

	ID_ROLE,
	ID_USER
).



CREATE TABLE Entries
(

	ID                    INTEGER ,
	ACL_OBJ_IDENTITY      INTEGER NOT NULL ,
	SID                   INTEGER ,
	MASK                  INTEGER ,
	ORDER                 INTEGER 
).



CREATE UNIQUE  INDEX XPKEntries ON Entries 
(

	ID
).



CREATE TABLE GROUP_AUTHORITIES
(

	ID_GROUP              INTEGER NOT NULL ,
	ID_ROLE               INTEGER NOT NULL 
).



CREATE UNIQUE  INDEX XPKGROUP_AUTHORITIES ON GROUP_AUTHORITIES 
(

	ID_GROUP,
	ID_ROLE
).



CREATE TABLE GROUP_MEMBERS
(

	ID_GROUP              INTEGER NOT NULL ,
	ID_USER               INTEGER NOT NULL ,
	ID_ROLE               INTEGER 
).



CREATE UNIQUE  INDEX XPKGROUP_MEMBERS ON GROUP_MEMBERS 
(

	ID_GROUP,
	ID_USER
).



CREATE TABLE GROUPS
(

	ID_GROUP              INTEGER ,
	GROUP                 CHARACTER(64) ,
	DESCRIPTION           CHARACTER(256) 
).



CREATE UNIQUE  INDEX XPKGROUPS ON GROUPS 
(

	ID_GROUP
).



CREATE TABLE OBJ_IDENTITY
(

	ID                    INTEGER ,
	ID_OBJ_TYPE           INTEGER NOT NULL ,
	ID_OBJ                INTEGER ,
	OWNER                 INTEGER NOT NULL ,
	ENTRIES_INHERTING     LOGICAL 
).



CREATE UNIQUE  INDEX XPKOBJ_IDENTITY ON OBJ_IDENTITY 
(

	ID
).



CREATE TABLE OBJ_TYPE_DIC
(

	ID_OBJ_TYPE           INTEGER ,
	OBJ_TYPE              CHARACTER(128) 
).



CREATE UNIQUE  INDEX XPKOBJ_TYPE_DIC ON OBJ_TYPE_DIC 
(

	ID_OBJ_TYPE
).



CREATE TABLE ROLES_DIC
(

	ID_ROLE               INTEGER ,
	ROLE                  CHARACTER(64) ,
	DESCRIPTION           CHARACTER(256) 
).



CREATE UNIQUE  INDEX XPKROLES_DIC ON ROLES_DIC 
(

	ID_ROLE
).



CREATE TABLE SID
(

	ID                    INTEGER ,
	ID_SID_TYPE_DIC       INTEGER NOT NULL ,
	SID                   CHARACTER(64) 
).



CREATE UNIQUE  INDEX XPKSID ON SID 
(

	ID
).



CREATE TABLE SID_TYPE_DIC
(

	ID_SID_TYPE_DIC       INTEGER ,
	NAME                  CHARACTER(64) 
).



CREATE UNIQUE  INDEX XPKSID_TYPE_DIC ON SID_TYPE_DIC 
(

	ID_SID_TYPE_DIC
).



CREATE TABLE USERS
(

	ID_USER               INTEGER ,
	USERNAME              CHARACTER(64) ,
	PASSWORD              CHARACTER(64) ,
	ENABLED               INTEGER ,
	EMAIL                 CHARACTER(148) 
).



CREATE UNIQUE  INDEX XPKUSERS ON USERS 
(

	ID_USER
).

