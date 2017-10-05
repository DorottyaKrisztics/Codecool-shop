--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.8
-- Dumped by pg_dump version 9.5.8

-- Started on 2017-10-05 23:21:51 CEST

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
-- TOC entry 181 (class 1259 OID 25457)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cart (
    product_id bigint,
    quantity bigint
);


ALTER TABLE cart OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 25460)
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
-- TOC entry 183 (class 1259 OID 25466)
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
-- TOC entry 184 (class 1259 OID 25472)
-- Name: product_supplier; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE product_supplier (
    id integer NOT NULL,
    name character varying(40),
    description text
);


ALTER TABLE product_supplier OWNER TO postgres;

--
-- TOC entry 2155 (class 0 OID 25457)
-- Dependencies: 181
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cart (product_id, quantity) FROM stdin;
\.


--
-- TOC entry 2156 (class 0 OID 25460)
-- Dependencies: 182
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product (id, name, description, default_price, product_category, product_supplier, image, currency_type) FROM stdin;
8	Ty Beanie Ballz Fable Unicorn Plush	Beanie Ballz are a little wild and whacky, toss 'em and they always land on their feet. Collect them all.	16.9899999999999984	2	4	s	USD
4	Anal Intruder	This is a good deal though!	100	3	3	s	USD
5	LEKA Soft toy, ball, multicolor	Fabric: 100 % polyester. Filling: Polyester fiber fill. Recommended for all ages.	1.98999999999999999	2	4	s	USD
7	Yoda Soft Ball	Star Wars fans will love these fantastic talking plush toys! Choose from Yoda, R2D2, Darth Vader and Chewbacca characters.	8.5	2	1	s	USD
6	Pokemon ball	Gotta catch ‘em all!	12	2	4	s	USD
1	Amazon Fire	Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.	49.8999999999999986	1	1	s	USD
2	Lenovo IdeaPad Miix 700	Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.	479	1	2	s	USD
3	Amazon Fire HD 8	Amazon's latest Fire HD 8 tablet is a great value for media consumption.	89	1	1	s	USD
\.


--
-- TOC entry 2157 (class 0 OID 25466)
-- Dependencies: 183
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_category (id, name, department, description) FROM stdin;
1	Tablet	Hardware	A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.
2	Ball	Toys	A stuffed soft ball for playing in the class room
3	Adult	Hardware	xxx xxx xxx
\.


--
-- TOC entry 2158 (class 0 OID 25472)
-- Dependencies: 184
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY product_supplier (id, name, description) FROM stdin;
1	Amazon	Digital content and services
2	Lenovo	Computers
3	eBay	The biggest online store ever
4	IKEA	Interior design
\.


--
-- TOC entry 2035 (class 2606 OID 25479)
-- Name: product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- TOC entry 2033 (class 2606 OID 25481)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2037 (class 2606 OID 25483)
-- Name: product_supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product_supplier
    ADD CONSTRAINT product_supplier_pkey PRIMARY KEY (id);


--
-- TOC entry 2038 (class 2606 OID 25484)
-- Name: cart_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cart
    ADD CONSTRAINT cart_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2039 (class 2606 OID 25489)
-- Name: product_product_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_product_category_fkey FOREIGN KEY (product_category) REFERENCES product_category(id);


--
-- TOC entry 2040 (class 2606 OID 25494)
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


-- Completed on 2017-10-05 23:21:51 CEST

--
-- PostgreSQL database dump complete
--

