# Truck-Tracker

# Dependencies
       There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.
   
# Building the project
    You will need:
       Java JDK 8 or higher
       Maven 3.1.1 or higher
       postgres
            OR
      Java JDK 8 or higher
      Tomcat-server
      postgres


# you need to install Postgres  
    username =  postgres
    password = postgres
    DB Name  = trucktracker
   
# Create Tabels and Insert Followed Data in Postgres

    CREATE TABLE IF NOT EXISTS public.oauth_client_details
     (
       client_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
       access_token_validity integer,
       additional_information character varying(255) COLLATE pg_catalog."default",
       authorities character varying(255) COLLATE pg_catalog."default",
       authorized_grant_types character varying(255) COLLATE pg_catalog."default",
       autoapprove character varying(255) COLLATE pg_catalog."default",
       client_secret character varying(255) COLLATE pg_catalog."default",
       created_by character varying(255) COLLATE pg_catalog."default",
       created_on timestamp without time zone,
       refresh_token_validity integer,
       resource_ids character varying(255) COLLATE pg_catalog."default",
       scope character varying(255) COLLATE pg_catalog."default",
       updated_by character varying(255) COLLATE pg_catalog."default",
       updated_on timestamp without time zone,
       web_server_redirect_uri character varying(4096) COLLATE pg_catalog."default",
       CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id))
       TABLESPACE pg_default;
       ALTER TABLE IF EXISTS public.oauth_client_details
       OWNER to postgres; 

    INSERT INTO public.oauth_client_details(
    client_id, access_token_validity, additional_information, authorities,
    authorized_grant_types, autoapprove, client_secret,
    created_by, created_on,
    refresh_token_validity, resource_ids, scope, updated_by, updated_on,
    web_server_redirect_uri)
    VALUES ('broan-ciaq-app.client.e48bfce9b047459a88ecc42daedd53e3', 300, '{​​​​​​​​}​​​​​​​​', NULL,
    'password,authorization_code,refresh_token,client_credentials', 'true', '$2a$12$RJc/ah7yI69L8hWJyeyKVeyljX8Rjbxst/HTtnDfyP5olqjbacHRa',
    'adminuser@driven-4.com', '2022-01-25 01:23:39.809',
    691201, 'resource_id', 'read, write, alexa::skills:account_linking', 'adminuser@driven-4.com', '2022-01-25 01:23:39.809',
    'https://iotft.broan-nutone.com/overturev2/api/v1/ciaq/getToken,https://iotft.broan-nutone.com/overturev2/api/v1/ciaq/createAlexaAccountLinking,https://developers.google.com/oauthplayground,https://oauth-redirect.googleusercontent.com/r/broan-voice,https://oauth-redirect-sandbox.googleusercontent.com/r/broan-voice,https://oauth-redirect.googleusercontent.com/r/broan-second,https://oauth-redirect-sandbox.googleusercontent.com/r/broan-second,https://oauth-redirect.googleusercontent.com/r/overture-ee20d,https://oauth-redirect-sandbox.googleusercontent.com/r/overture-ee20d,https://pitangui.amazon.com/api/skill/link/M1OQBN3T0P4C2W,https://alexa.amazon.co.jp/api/skill/link/M1OQBN3T0P4C2W,https://layla.amazon.com/api/skill/link/M1OQBN3T0P4C2W');
   

    CREATE TABLE public.oauth_access_token
      (
      authentication_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
      token_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
      token bytea NOT NULL,
      user_name character varying(255) COLLATE pg_catalog."default",
      client_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
      authentication bytea NOT NULL,
      refresh_token character varying(255) COLLATE pg_catalog."default" NOT NULL,
      CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
       )
      WITH (OIDS = FALSE)
      TABLESPACE pg_default;
 
     CREATE TABLE public.oauth_refresh_token
        (
        token_id character varying(256) COLLATE pg_catalog."default",
        token bytea,
        authentication bytea)
        WITH (OIDS = FALSE)
        TABLESPACE pg_default; 
        ALTER TABLE public.oauth_refresh_token
        OWNER to postgres;

# API
    For API go to target Folder and Import The Collection  "Truck_Tracker.postman_collection" In Postman


