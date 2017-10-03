--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.8
-- Dumped by pg_dump version 9.5.8

-- Started on 2017-10-03 14:36:13 CEST

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
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 50888)
-- Name: cart; Type: TABLE; Schema: public; Owner:
--

CREATE TABLE cart (
    product_id bigint,
    quantity bigint
);


ALTER TABLE cart OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 50860)
-- Name: product; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE product (
    id bigint NOT NULL,
    name character varying(40),
    description character varying(255),
    default_price numeric(2,0),
    product_category bigint,
    product_supplier bigint,
    image character varying(255)
);


ALTER TABLE product OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 50866)
-- Name: product_category; Type: TABLE; Schema: public; Owner:
--

CREATE TABLE product_category (
    id bigint NOT NULL,
    name character varying(40)
);


ALTER TABLE product_category OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 50869)
-- Name: product_supplier; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE product_supplier (
    id bigint NOT NULL,
    name character varying(40)
);


ALTER TABLE product_supplier OWNER TO postgres;

--
-- TOC entry 2156 (class 0 OID 50888)
-- Dependencies: 184
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: 
--

COPY cart (product_id, quantity) FROM stdin;
\.


--
-- TOC entry 2153 (class 0 OID 50860)
-- Dependencies: 181
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: 
--

COPY product (id, name, description, default_price, product_category, product_supplier, image) FROM stdin;
\.


--
-- TOC entry 2154 (class 0 OID 50866)
-- Dependencies: 182
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: 
--

COPY product_category (id, name) FROM stdin;
\.


--
-- TOC entry 2155 (class 0 OID 50869)
-- Dependencies: 183
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: public; Owner: 
--

COPY product_supplier (id, name) FROM stdin;
\.


--
-- TOC entry 2033 (class 2606 OID 50873)
-- Name: product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 50875)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 50877)
-- Name: product_supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY product_supplier
    ADD CONSTRAINT product_supplier_pkey PRIMARY KEY (id);


--
-- TOC entry 2038 (class 2606 OID 50891)
-- Name: cart_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY cart
    ADD CONSTRAINT cart_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id);


--
-- TOC entry 2036 (class 2606 OID 50878)
-- Name: product_product_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_product_category_fkey FOREIGN KEY (product_category) REFERENCES product_category(id);


--
-- TOC entry 2037 (class 2606 OID 50883)
-- Name: product_product_supplier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_product_supplier_fkey FOREIGN KEY (product_supplier) REFERENCES product_supplier(id);


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-10-03 14:36:13 CEST

--
-- PostgreSQL database dump complete
--

