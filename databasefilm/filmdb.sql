PGDMP                         z            film    14.1    14.1 	    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    33848    film    DATABASE     `   CREATE DATABASE film WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE film;
                postgres    false            �            1259    33850    film    TABLE     �   CREATE TABLE public.film (
    id integer NOT NULL,
    anno integer,
    incasso character varying(255),
    regista character varying(255),
    tipo character varying(255),
    titolo character varying(255)
);
    DROP TABLE public.film;
       public         heap    postgres    false            �            1259    33849    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �          0    33850    film 
   TABLE DATA           H   COPY public.film (id, anno, incasso, regista, tipo, titolo) FROM stdin;
    public          postgres    false    210   v       �           0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 7, true);
          public          postgres    false    209            ]           2606    33856    film film_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.film DROP CONSTRAINT film_pkey;
       public            postgres    false    210            �   W  x�U��v�@@�q�#`|0䡶"&IlWO�p�2p��������:�b�i9lb��2'^����v�Ի+J7���[k�(�A�w�*�Ly��y5��(�m�*}�q׶�m��G��=R���הP��4z������;�<W2��:���oS���x�-�y���d̗�f��o��y�Y���@+ݗ����nR�_rm���3&�`��v���<䞵�:�?Ҏ����t0���<�7!�/���Y����3�ND���%G	�寏�Ht}���3������ɵ.��Ga��R�ڪcs������n�H�{"Gb��!�Q0�F {����i�?�x     