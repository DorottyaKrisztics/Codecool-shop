--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.8
-- Dumped by pg_dump version 9.5.8

-- Started on 2017-10-05 10:28:30 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12395)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 50897)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product_supplier CASCADE;

CREATE TABLE cart (
    product_id bigint,
    quantity bigint
);


ALTER TABLE cart OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 50900)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product (
    id integer NOT NULL,
    name character varying(40),
    description character varying(255),
    default_price double precision,
    product_category bigint,
    product_supplier bigint,
    image character varying(255),
    currency_type text
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 50906)
-- Name: product_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_category (
    id integer NOT NULL,
    name character varying(40),
    department text,
    description text
);


ALTER TABLE product_category OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 50909)
-- Name: product_supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_supplier (
    id integer NOT NULL,
    name character varying(40),
    description text
);


ALTER TABLE product_supplier OWNER TO postgres;

--
-- TOC entry 2155 (class 0 OID 50897)
-- Dependencies: 181
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cart (product_id, quantity) FROM stdin;
\.


--
-- TOC entry 2156 (class 0 OID 50900)
-- Dependencies: 182
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (id, name, description, default_price, product_category, product_supplier, image, currency_type) FROM stdin;
\.


--
-- TOC entry 2157 (class 0 OID 50906)
-- Dependencies: 183
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_category (id, name, department, description) FROM stdin;
2	dfssdfdf	sdfssf	aaaaas
3	bbb	ccc	www
\.


--
-- TOC entry 2158 (class 0 OID 50909)
-- Dependencies: 184
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_supplier (id, name, description) FROM stdin;
\.


--
-- TOC entry 2035 (class 2606 OID 50961)
-- Name: product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- TOC entry 2033 (class 2606 OID 50947)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2037 (class 2606 OID 50975)
-- Name: product_supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_supplier
    ADD CONSTRAINT product_supplier_pkey PRIMARY KEY (id);


--
-- TOC entry 2038 (class 2606 OID 50948)
-- Name: cart_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart
    ADD CONSTRAINT cart_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2039 (class 2606 OID 50962)
-- Name: product_product_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_product_category_fkey FOREIGN KEY (product_category) REFERENCES product_category(id);


--
-- TOC entry 2040 (class 2606 OID 50976)
-- Name: product_product_supplier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_product_supplier_fkey FOREIGN KEY (product_supplier) REFERENCES product_supplier(id);


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-10-05 10:28:30 CEST

--
-- PostgreSQL database dump complete
--

