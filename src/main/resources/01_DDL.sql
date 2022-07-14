-- Table: public.ms_user

DROP TABLE IF EXISTS public.ms_user;

CREATE TABLE public.ms_user
(
    user_id VARCHAR(15) NOT NULL,
    user_name VARCHAR(50) COLLATE pg_catalog."default",
    user_phone VARCHAR(15) COLLATE pg_catalog."default",
    user_notes VARCHAR(255) COLLATE pg_catalog."default",
    max_limit NUMERIC(15,2) default 0.00 NOT NULL,
	register_date DATE NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(15) NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(15),
    deleted_date TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(15),
    rec_status VARCHAR(1) COLLATE pg_catalog."default" DEFAULT 'N'::VARCHAR,
    CONSTRAINT ms_user_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;




-- Table: public.ms_customer

DROP TABLE IF EXISTS public.ms_customer;

CREATE TABLE public.ms_customer
(
    customer_id VARCHAR(10) NOT NULL,
    customer_name VARCHAR(30) COLLATE pg_catalog."default",
    customer_identity_no VARCHAR(16) COLLATE pg_catalog."default",
    customer_phone VARCHAR(50) COLLATE pg_catalog."default",
    customer_gender VARCHAR(1) COLLATE pg_catalog."default",
    customer_jenis_usaha VARCHAR(10) COLLATE pg_catalog."default",
    customer_max_limit NUMERIC(15,2) default 0.00 NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(15) NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(15),
    deleted_date TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(15),
    rec_status VARCHAR(1) COLLATE pg_catalog."default" DEFAULT 'N'::VARCHAR,
    CONSTRAINT ms_customer_pk PRIMARY KEY (customer_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;




-- Table: public.ms_jenis_usaha

DROP TABLE IF EXISTS public.ms_jenis_usaha;

CREATE TABLE public.ms_jenis_usaha
(
    jenis_usaha_id VARCHAR(10) NOT NULL,
    jenis_usaha_name VARCHAR(30) COLLATE pg_catalog."default",
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(15) NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(15),
    deleted_date TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(15),
    rec_status VARCHAR(1) COLLATE pg_catalog."default" DEFAULT 'N'::VARCHAR,
    CONSTRAINT ms_jenis_usaha_pk PRIMARY KEY (jenis_usaha_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;





-- Table: public.ms_jenis_usaha

DROP TABLE IF EXISTS public.ms_jenis_usaha;

CREATE TABLE public.ms_jenis_usaha
(
    jenis_usaha_id VARCHAR(10) NOT NULL,
    jenis_usaha_name VARCHAR(30) COLLATE pg_catalog."default",
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(15) NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(15),
    deleted_date TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(15),
    rec_status VARCHAR(1) COLLATE pg_catalog."default" DEFAULT 'N'::VARCHAR,
    CONSTRAINT ms_jenis_usaha_pk PRIMARY KEY (jenis_usaha_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;




-- Table: public.lg_activities

DROP TABLE IF EXISTS public.lg_activities;

CREATE TABLE public.lg_activities
(
    log_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    log_by VARCHAR(15) NOT NULL,
	log_type VARCHAR(15) NOT NULL, -- NEW, UPDATE, DELETE, PROSES
    log_module VARCHAR(100) COLLATE pg_catalog."default" NOT NULL,
    log_description VARCHAR(255) COLLATE pg_catalog."default" NOT NULL,
	data_before text,
	data_after text,
    CONSTRAINT lg_activities_pkey PRIMARY KEY (log_time, log_by)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.lg_activities
    OWNER to postgres;


-- Table: public.ms_product

-- DROP TABLE public.ms_product;

CREATE TABLE public.ms_product
(
    product_id character varying(20) NOT NULL,
    product_type character varying(30) NOT NULL,
    product_name character varying(50) NOT NULL,
    product_desc character varying(255),
    product_ltv numeric(5,2) NOT NULL,
    product_tenor numeric(3) NOT NULL,
    biaya_adm_buka_type character varying(7) NOT NULL,
    biaya_adm_buka_val numeric(5,2) NOT NULL,
    biaya_adm_tutup_type character varying(7) NOT NULL,
    biaya_adm_tutup_val numeric(5,2) NOT NULL,
    biaya_js_peny_rate numeric(5,2) NOT NULL,
    biaya_js_peny_per numeric(3) NOT NULL,
    biaya_denda_keterlambatan_rate numeric(5,2) NOT NULL,
    biaya_denda_keterlambatan_per numeric(3) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    created_by character varying(50) NOT NULL,
    updated_date timestamp without time zone,
    updated_by character varying(50),
    deleted_date timestamp without time zone,
    deleted_by character varying(50),
    rec_status character varying(1) COLLATE pg_catalog."default" DEFAULT 'N'::character varying,
    CONSTRAINT ms_product_pk PRIMARY KEY (product_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ms_product
    OWNER to postgres;


-- Table: public.tx_transaksi_cicilan_tetap

-- DROP TABLE IF EXISTS public.tx_transaksi_cicilan_tetap;

CREATE TABLE IF NOT EXISTS public.tx_transaksi_cicilan_tetap
(
    no_transaksi character varying COLLATE pg_catalog."default" NOT NULL,
    total_nilai_tak numeric,
    nilai_pencairan_pel numeric,
    diskon_adm_buka numeric,
    tx_ltv numeric,
    max_nilai_pinj numeric,
    biaya_adm_buka numeric,
    biaya_adm_buka_ak numeric,
    total_nilai_pinj numeric,
    tanggal_tx date,
    tanggal_jatuh_tempo date,
    tx_biaya_jasa_peny numeric,
    tx_biaya_jasa_peny_per numeric,
    total_biaya_jasa_peny numeric,
    tx_biaya_adm_tutup numeric,
    total_pengem numeric,
    customer_id character varying COLLATE pg_catalog."default",
    product_id character varying COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    CONSTRAINT tx_transaksi_cicilan_tetap_pkey PRIMARY KEY (no_transaksi)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tx_transaksi_cicilan_tetap
    OWNER to postgres;


-- Table: public.tx_transaksi_barang

-- DROP TABLE IF EXISTS public.tx_transaksi_barang;

CREATE TABLE IF NOT EXISTS public.tx_transaksi_barang
(
    no_transaksi character varying COLLATE pg_catalog."default" NOT NULL,
    nama_barang character varying(30) COLLATE pg_catalog."default",
    kondisi character varying(150) COLLATE pg_catalog."default",
    jumlah numeric(3,0),
    harga_per_satuan numeric(10,2),
    CONSTRAINT tx_transaksi_barang_pkey PRIMARY KEY (no_urut, no_transaksi)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tx_transaksi_barang
    OWNER to postgres;



-- Table: public.tx_cicilan

-- DROP TABLE IF EXISTS public.tx_cicilan;

CREATE TABLE IF NOT EXISTS public.tx_cicilan
(
    no_transaksi character varying COLLATE pg_catalog."default" NOT NULL,
    cicilan_ke numeric,
    tx_pokok numeric,
    tx_bunga numeric,
    tx_status character varying COLLATE pg_catalog."default",
    tanggal_aktif date,
    tanggal_jatuh_tempo date,
    tanggal_bayar date,
    created_date timestamp without time zone,
    CONSTRAINT tx_cicilan_pkey PRIMARY KEY (no_transaksi)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tx_cicilan
    OWNER to postgres;
    
    
