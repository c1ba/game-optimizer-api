DO $$
     BEGIN
        RAISE NOTICE 'Generating the tables, keep your pants tight!';
    END
$$;

SET statement_timeout = 0;

SET lock_timeout = 0;

SET idle_in_transaction_session_timeout = 0;

SET client_encoding = 'UTF8';

SET standard_conforming_strings = on;

SELECT pg_catalog.set_config('search_path', '', false);

SET check_function_bodies = false;

SET xmloption = content;

SET row_security = off;

CREATE SCHEMA IF NOT EXISTS gaming_optimizer;

ALTER SCHEMA gaming_optimizer OWNER TO rooterino;

DO $$
    BEGIN
        RAISE NOTICE 'Searching for extensions...';
    END
$$;

DO $$
    BEGIN
       CREATE EXTENSION IF NOT EXISTS pgcrypto
       SCHEMA gaming_optimizer
       CASCADE;
    END
$$;

ALTER ROLE rooterino SET search_path TO 'gaming_optimizer';

SET search_path = 'gaming_optimizer';

SET default_tablespace = '';

SET default_with_oids = false;

  -- Creating enums
DO $$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'user_type') THEN
           CREATE TYPE gaming_optimizer.user_type AS ENUM ('user', 'admin');
           ALTER TYPE gaming_optimizer.user_type OWNER TO rooterino;
        END IF;
        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'component_type') THEN
           CREATE TYPE gaming_optimizer.component_type AS ENUM ('processor', 'graphics_card', 'ram');
           ALTER TYPE gaming_optimizer.component_type OWNER TO rooterino;
        END IF;
    END
$$;

DO $$
    BEGIN
        RAISE NOTICE 'Enums installed!';
        RAISE NOTICE 'Creating tables...';
    END
$$;

DO $$
    BEGIN
        CREATE TABLE IF NOT EXISTS gaming_optimizer.users (
            id uuid DEFAULT gen_random_uuid() NOT NULL,
            username character varying(100) NOT NULL UNIQUE,
            password character varying(1024) NOT NULL,
            role gaming_optimizer.user_type NOT NULL DEFAULT 'user',
            created_at timestamp NOT NULL DEFAULT NOW()
        );
        ALTER TABLE gaming_optimizer.users OWNER TO rooterino;

        CREATE TABLE IF NOT EXISTS gaming_optimizer.components (
             id uuid DEFAULT gen_random_uuid() NOT NULL,
             name character varying(100) DEFAULT NULL,
             type gaming_optimizer.component_type NOT NULL,
             capacity float(2),
             created_at timestamp NOT NULL DEFAULT NOW()
        );
        ALTER TABLE gaming_optimizer.components OWNER TO rooterino;

        CREATE TABLE IF NOT EXISTS gaming_optimizer.component_combos (
             id uuid DEFAULT gen_random_uuid() NOT NULL,
             processor_id uuid NOT NULL,
             graphics_card_id uuid NOT NULL,
             ram_id uuid NOT NULL,
             created_at timestamp NOT NULL DEFAULT NOW()
        );
        ALTER TABLE gaming_optimizer.component_combos OWNER TO rooterino;

        CREATE TABLE IF NOT EXISTS gaming_optimizer.games (
             id uuid DEFAULT gen_random_uuid() NOT NULL,
             name character varying(100) NOT NULL UNIQUE,
             year_release int NOT NULL,
             minimum_components_id uuid NOT NULL,
             created_at timestamp NOT NULL DEFAULT NOW()
        );
        ALTER TABLE gaming_optimizer.games OWNER TO rooterino;

        CREATE TABLE IF NOT EXISTS gaming_optimizer.performance_files (
             id uuid DEFAULT gen_random_uuid() NOT NULL,
             components_combo_id uuid NOT NULL,
             user_id uuid NOT NULL,
             game_id uuid NOT NULL,
             performance_files_names varchar[],
             likes int,
             dislikes int,
             created_at timestamp NOT NULL DEFAULT NOW()
        );
        ALTER TABLE gaming_optimizer.performance_files OWNER TO rooterino;
    END
$$;

DO $$
    BEGIN
        RAISE NOTICE 'Tables generated!';
        RAISE NOTICE 'Creating primary and foreign keys...';
    END
$$;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'users_pkey') THEN
        ALTER TABLE ONLY gaming_optimizer.users ADD CONSTRAINT users_pkey PRIMARY KEY (id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'components_pkey') THEN
        ALTER TABLE ONLY gaming_optimizer.components ADD CONSTRAINT components_pkey PRIMARY KEY (id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'component_combos_pkey') THEN
        ALTER TABLE ONLY gaming_optimizer.component_combos ADD CONSTRAINT component_combos_pkey PRIMARY KEY (id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'games_pkey') THEN
        ALTER TABLE ONLY gaming_optimizer.games ADD CONSTRAINT games_pkey PRIMARY KEY (id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'performance_files_pkey') THEN
        ALTER TABLE ONLY gaming_optimizer.performance_files ADD CONSTRAINT performance_files_pkey PRIMARY KEY (id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'processor_fkey') THEN
        ALTER TABLE ONLY gaming_optimizer.component_combos ADD CONSTRAINT processor_fkey FOREIGN KEY (processor_id) REFERENCES gaming_optimizer.components(id) ON DELETE CASCADE;
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'graphics_card_fkey') THEN
        ALTER TABLE ONLY gaming_optimizer.component_combos ADD CONSTRAINT graphics_card_fkey FOREIGN KEY (graphics_card_id) REFERENCES gaming_optimizer.components(id) ON DELETE CASCADE;
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'ram_fkey') THEN
        ALTER TABLE ONLY gaming_optimizer.component_combos ADD CONSTRAINT ram_fkey FOREIGN KEY (ram_id) REFERENCES gaming_optimizer.components(id) ON DELETE CASCADE;
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'components_combo_fkey') THEN
        ALTER TABLE ONLY gaming_optimizer.performance_files ADD CONSTRAINT components_combo_fkey FOREIGN KEY (components_combo_id) REFERENCES gaming_optimizer.component_combos(id) ON DELETE CASCADE;
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'user_fkey') THEN
        ALTER TABLE ONLY gaming_optimizer.performance_files ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES gaming_optimizer.users(id) ON DELETE SET NULL;
        END IF;
    END
$$;

DO $$
    BEGIN
        RAISE NOTICE 'Primary and Foreign Keys generated!';
        RAISE NOTICE 'Yey, we have a database and some tables!';
    END
$$;