PGDMP         (            	    {           user    15.1 (Debian 15.1-1.pgdg110+1)    15.3                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            !           1262    16384    user    DATABASE     q   CREATE DATABASE "user" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE "user";
                postgres    false            �            1259    16385    flyway_schema_history    TABLE     �  CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);
 )   DROP TABLE public.flyway_schema_history;
       public         heap    postgres    false            �            1259    16402    sector_item    TABLE     �   CREATE TABLE public.sector_item (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    is_leaf boolean DEFAULT false NOT NULL,
    parent_id integer
);
    DROP TABLE public.sector_item;
       public         heap    postgres    false            �            1259    16401    sector_item_id_seq    SEQUENCE     �   CREATE SEQUENCE public.sector_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.sector_item_id_seq;
       public          postgres    false    218            "           0    0    sector_item_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.sector_item_id_seq OWNED BY public.sector_item.id;
          public          postgres    false    217            �            1259    16395    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(255),
    is_agreed_to_terms boolean NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16394    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            #           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            �            1259    16415    users_sectors    TABLE     q   CREATE TABLE public.users_sectors (
    id integer NOT NULL,
    users_id integer,
    sector_item_id integer
);
 !   DROP TABLE public.users_sectors;
       public         heap    postgres    false            �            1259    16414    users_sectors_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_sectors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.users_sectors_id_seq;
       public          postgres    false    220            $           0    0    users_sectors_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.users_sectors_id_seq OWNED BY public.users_sectors.id;
          public          postgres    false    219            x           2604    16405    sector_item id    DEFAULT     p   ALTER TABLE ONLY public.sector_item ALTER COLUMN id SET DEFAULT nextval('public.sector_item_id_seq'::regclass);
 =   ALTER TABLE public.sector_item ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            w           2604    16398    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            z           2604    16418    users_sectors id    DEFAULT     t   ALTER TABLE ONLY public.users_sectors ALTER COLUMN id SET DEFAULT nextval('public.users_sectors_id_seq'::regclass);
 ?   ALTER TABLE public.users_sectors ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220                      0    16385    flyway_schema_history 
   TABLE DATA           �   COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
    public          postgres    false    214    %                 0    16402    sector_item 
   TABLE DATA           C   COPY public.sector_item (id, name, is_leaf, parent_id) FROM stdin;
    public          postgres    false    218   �%                 0    16395    users 
   TABLE DATA           =   COPY public.users (id, name, is_agreed_to_terms) FROM stdin;
    public          postgres    false    216   �)                 0    16415    users_sectors 
   TABLE DATA           E   COPY public.users_sectors (id, users_id, sector_item_id) FROM stdin;
    public          postgres    false    220   �)       %           0    0    sector_item_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.sector_item_id_seq', 1, false);
          public          postgres    false    217            &           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 9, true);
          public          postgres    false    215            '           0    0    users_sectors_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.users_sectors_id_seq', 27, true);
          public          postgres    false    219            |           2606    16392 .   flyway_schema_history flyway_schema_history_pk 
   CONSTRAINT     x   ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);
 X   ALTER TABLE ONLY public.flyway_schema_history DROP CONSTRAINT flyway_schema_history_pk;
       public            postgres    false    214            �           2606    16408    sector_item sector_item_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.sector_item
    ADD CONSTRAINT sector_item_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.sector_item DROP CONSTRAINT sector_item_pkey;
       public            postgres    false    218                       2606    16400    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            �           2606    16420     users_sectors users_sectors_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users_sectors
    ADD CONSTRAINT users_sectors_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.users_sectors DROP CONSTRAINT users_sectors_pkey;
       public            postgres    false    220            }           1259    16393    flyway_schema_history_s_idx    INDEX     `   CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);
 /   DROP INDEX public.flyway_schema_history_s_idx;
       public            postgres    false    214            �           2606    16409 &   sector_item sector_item_parent_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.sector_item
    ADD CONSTRAINT sector_item_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES public.sector_item(id);
 P   ALTER TABLE ONLY public.sector_item DROP CONSTRAINT sector_item_parent_id_fkey;
       public          postgres    false    218    3201    218            �           2606    16426 /   users_sectors users_sectors_sector_item_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_sectors
    ADD CONSTRAINT users_sectors_sector_item_id_fkey FOREIGN KEY (sector_item_id) REFERENCES public.sector_item(id);
 Y   ALTER TABLE ONLY public.users_sectors DROP CONSTRAINT users_sectors_sector_item_id_fkey;
       public          postgres    false    218    220    3201            �           2606    16421 )   users_sectors users_sectors_users_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_sectors
    ADD CONSTRAINT users_sectors_users_id_fkey FOREIGN KEY (users_id) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.users_sectors DROP CONSTRAINT users_sectors_users_id_fkey;
       public          postgres    false    3199    220    216               �   x�}̽
�0���)���IL��Ip�'!T.�j��-��z��<��]�ǔ�r:�)5L���B�u���K}.�����#t��Ł��.vdU%$tx����<�-)5J+msD���S�5��V�zߵR���1�         �  x�mU�n�6>����������S�&��&� 1�(�Z�l�钔���}�>IgH�q�����Ùo~��Wc�7������I+��3c�Vl�N�{�$��^4�h%˸j������Ӻ��q�o�L�n�^�W�?�F�Nx�;��S�H*�.��;iw�^G�w�<�~�-�ߺ[£�	���^NB8�V�f�:8�S����D:�0j�3ZW���S���1Ė�Q.`��}k��篿-����t�N�(Wp/�H�%��Ǯ����"̾?��C�`*�k�6A���d��H#{;XR,j��D�O$����p@ޕ'(Cc~a��0���i�O��I�v��> J��@đ���~������:!zv�f��<�\&Y7(]���ی�!�`�-�e'̈�����ư�4���x�B�bh1�|~.:I)<�9�TN(���0�@<����b����� P��}1ϒ���z�"�Ô�d,c��I�*��E����Տ���E^%���l���q���Ѷ��O=���{�Y�ft�|���ç3��O(V�$&�	�9�1�	<���G<��m�����-������4a-���N�^o�	�{�ק�H
�@�S ��'7v��G��3���u�SK�
,�T�r�-�����eR��wz��[M���͆^⥀���oDߟߟ8���Z���}[����O�^�de����r_h�xp=�=�h��*) Թ���bB,'���_�V� �m^��2�;yL�v��Y͓*�[l���e����:J\�����83�3�sD^�2�0y���+��|	aB������h���=uk�=#�V<�F�n-���E���ampx��oq\�^ĚTuRQ�����4�c�p�_�����܉1c�i�M)�O ���Z�O�!�Ko��}H�}�u��O�^o�o�U]µ�U㹂g.�(��y�?�;H_J��_�D[$         Q   x�3�L,N)NLK,�,�2��/�.���!,C ��42M9�*S�L3��1�i�Pk��Y��dYr����$�$91z\\\ YKj         e   x���	1ѳ�2��=�L�ql��J
��;.��[����Ք�-��v<R��	�Һ��8@\�7n  �0�G�-ʙ�W
�Z����m?�t��     