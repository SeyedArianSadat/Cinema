BEGIN EXECUTE IMMEDIATE 'drop table USERS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table CUSTOMERS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table EVENTS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table ARTISTS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table SEATS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table SALOONS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table PAYMENTS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN EXECUTE IMMEDIATE 'drop table TICKETS cascade constraints';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/

BEGIN execute immediate 'drop sequence event_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence user_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence customer_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence artist_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence seat_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence saloon_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence payment_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/
BEGIN execute immediate 'drop sequence ticket_seq';EXCEPTION  WHEN OTHERS THEN NULL ;END;
/




