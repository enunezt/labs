--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
CREATE SCHEMA IF NOT EXISTS ESQ_CORE AUTHORIZATION SA;
--SET SCHEMA ESQ_CORE;
                  
--COMMENT ON SCHEMA ESQ_CORE  IS 'Esquema para tablas principales';

CREATE SEQUENCE IF NOT EXISTS esq_core.rol_id_rol_seq;
  
-- You can use this file to load seed data into the database using SQL statements
insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 

--Catalogo tipo documento 
INSERT INTO esq_core.catalogo(id_catalogo,nombre, descripcion, fec_registro,fec_cambio ,id_usuario_cambio) VALUES (1,'TIPO_DOCUMENTO','Tipos de Documento de personas', current_date,current_date,1);

INSERT INTO esq_core.catalogo_det(id_catalogo_det, codigo,valor, descripcion, estado, id_catalogo,fec_registro,fec_cambio ,id_usuario_cambio) VALUES (11, 'CC', 'CÉDULA DE CIUDADANÍA', 'Cédula de ciudadanía', 'A',1, current_date,current_date,1);
INSERT INTO esq_core.catalogo_det(id_catalogo_det, codigo,valor, descripcion, estado, id_catalogo,fec_registro,fec_cambio ,id_usuario_cambio) VALUES (12, 'TI', 'TARJETA DE IDENTIDAD', 'Tarjeta de identidad', 'A',1, current_date,current_date,1);
INSERT INTO esq_core.catalogo_det(id_catalogo_det, codigo,valor, descripcion, estado, id_catalogo,fec_registro,fec_cambio ,id_usuario_cambio) VALUES (13, 'CE', 'CÉDULA EXTRANJERA', 'Cédula Extranjera', 'A',1, current_date,current_date,1);

--usuarios de pruebas

INSERT INTO esq_core.usuarios(id_usuario,apellido1,	apellido2, nombres, num_documento, email, tipo_documento, tipo_usuario ,usuario,clave,fec_registro,fec_cambio,id_usuario_cambio) VALUES (nextval('esq_core.usuario_id_usuario_seq'),'ApAdmin1','ApAdmin2','NomAdmin','987456321','admin@admin.com',11,0,'Admin','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', current_date,current_date,1);
INSERT INTO esq_core.usuarios(id_usuario,apellido1,	apellido2, nombres, num_documento, email, tipo_documento, tipo_usuario ,usuario,clave,fec_registro,fec_cambio,id_usuario_cambio) values (nextval('esq_core.usuario_id_usuario_seq'),'ApBsico1','ApBsico2','NomApBsico','654987321','admin@admin.com',11,0,'Basico','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', current_date,current_date,1);
