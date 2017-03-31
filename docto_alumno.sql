-- Table: public.docto_alumno

-- DROP TABLE public.docto_alumno;

CREATE TABLE public.docto_alumno
(
  id integer NOT NULL DEFAULT nextval('docto_alumno_id_seq'::regclass),
  date_reception character varying(50),
  date_devolution character varying(50),
  note character varying(50),
  activo character varying(50),
  document character varying(100),
  CONSTRAINT docto_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.docto_alumno
  OWNER TO postgres;
