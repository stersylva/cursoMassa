package br.ce.wcaquino.dao.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class MontarAmbiente {
	
	private static void removerEstuturas() throws ClassNotFoundException, SQLException{
		Connection conn = ConnectionFactory.getConnection();
//		conn.createStatement().executeUpdate("ALTER TABLE public.usuarios DROP CONSTRAINT IF EXISTS conta_principal_id_foreign;");
		conn.createStatement().executeUpdate("DROP TABLE IF EXISTS public.massas");
		conn.createStatement().executeUpdate("DROP TABLE IF EXISTS public.transacoes");
		conn.createStatement().executeUpdate("DROP TABLE IF EXISTS public.contas");
		conn.createStatement().executeUpdate("DROP TABLE IF EXISTS public.usuarios");
		conn.createStatement().executeUpdate("DROP SEQUENCE IF EXISTS public.massas_id_seq");
		conn.createStatement().executeUpdate("DROP SEQUENCE IF EXISTS public.usuarios_id_seq");
		conn.createStatement().executeUpdate("DROP SEQUENCE IF EXISTS public.contas_id_seq");
		conn.createStatement().executeUpdate("DROP SEQUENCE IF EXISTS public.transacoes_id_seq");
		ConnectionFactory.closeConnection();
	}
	
	private static void criarEstuturas() throws ClassNotFoundException, SQLException{
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("CREATE SEQUENCE public.massas_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1");
		conn.createStatement().executeUpdate("CREATE SEQUENCE public.usuarios_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1");
		conn.createStatement().executeUpdate("CREATE SEQUENCE public.contas_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1");
		conn.createStatement().executeUpdate("CREATE SEQUENCE public.transacoes_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1");
		conn.createStatement().executeUpdate("CREATE TABLE public.massas (id integer NOT NULL DEFAULT nextval('massas_id_seq'::regclass), tipo character varying(255) NOT NULL, "
				+ "valor character varying(255) NOT NULL, usada boolean NOT NULL DEFAULT false, CONSTRAINT massas_pkey PRIMARY KEY (id) )");
		conn.createStatement().executeUpdate("CREATE TABLE public.usuarios (id integer NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass), nome character varying(255) NOT NULL, "
				+ "email character varying(255) NOT NULL, senha character varying(255) NOT NULL, conta_principal_id integer NULL, created_at timestamp with time zone NOT NULL DEFAULT now(), "
				+ "CONSTRAINT usuarios_pkey PRIMARY KEY (id), CONSTRAINT usuarios_email_unique UNIQUE (email) )");
		conn.createStatement().executeUpdate("CREATE TABLE public.contas (id integer NOT NULL DEFAULT nextval('contas_id_seq'::regclass), nome character varying(255) NOT NULL, "
				+ "usuario_id integer NOT NULL, CONSTRAINT contas_pkey PRIMARY KEY (id), CONSTRAINT contas_usuario_id_foreign FOREIGN KEY (usuario_id) "
				+ "REFERENCES public.usuarios (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION )");
		conn.createStatement().executeUpdate("CREATE TABLE public.transacoes (id integer NOT NULL DEFAULT nextval('transacoes_id_seq'::regclass), descricao character varying(255) NOT NULL, "
				+ "envolvido character varying(255) NOT NULL, tipo text NOT NULL, data_transacao date NOT NULL, valor numeric(15,2) NOT NULL, status boolean NOT NULL DEFAULT false, "
				+ "conta_id integer NOT NULL, usuario_id integer NOT NULL, CONSTRAINT transacoes_pkey PRIMARY KEY (id), "
				+ "CONSTRAINT transacoes_conta_id_foreign FOREIGN KEY (conta_id) REFERENCES public.contas (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, "
				+ "CONSTRAINT transacoes_usuario_id_foreign FOREIGN KEY (usuario_id) REFERENCES public.usuarios (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, "
				+ "CONSTRAINT transacoes_tipo_check CHECK (tipo = ANY (ARRAY['REC'::text, 'DESP'::text])) )");
//		conn.createStatement().executeUpdate("ALTER TABLE public.usuarios ADD CONSTRAINT conta_principal_id_foreign FOREIGN KEY (conta_principal_id) REFERENCES public.contas (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION ");
		ConnectionFactory.closeConnection();
	}
	
	public static void criarAmbiente() throws ClassNotFoundException, SQLException {
		removerEstuturas();
		criarEstuturas();
		System.out.println("Ambiente criado!");
	}
	
	public static void main(String[] args) throws Exception {
		MontarAmbiente.criarAmbiente();
	}
}
