package utils;

import java.sql.*;
import model.FuncaoEnum;
import model.Funcionario;
import model.Login;

/**
 * Singleton utilitario para efetuar consultas no banco de dados.
 *
 * @author Nelson
 */
public class ConexaoBanco {

    private static final String ARQUIVO_BANCO = "banco.db";

    private static ConexaoBanco instance = null;

    private Connection conexao = null;

    private ConexaoBanco() {
    }

    public static ConexaoBanco getInstance() {
        if (instance == null) {
            instance = new ConexaoBanco();
        }
        return instance;
    }

    /**
     * Conecta-se ao banco ou cria um novo caso não exista.
     *
     * @throws SQLException
     */
    public void conectar() throws SQLException {
        String bancoDir = "jdbc:sqlite:" + System.getProperty("user.dir")
                + "\\" + ARQUIVO_BANCO;

        conexao = DriverManager.getConnection(bancoDir);

        System.out.println("Database at " + bancoDir);
        System.out.println("Connection to SQLite has been established.");

        criarTabelas();
        inserirFuncionariosIniciais();
    }

    /**
     * Fecha a conexão com o banco.
     *
     * @throws SQLException
     */
    public void desconectar() throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }

    /**
     * Cria as tabelas do banco, caso não existam.
     *
     * @throws java.sql.SQLException
     */
    private void criarTabelas() throws SQLException {
        Statement stmt;
        String query;

        //FUNCIONARIO
        stmt = conexao.createStatement();
        query = "CREATE TABLE IF NOT EXISTS FUNCIONARIO (\n"
                + "  `Cpf` VARCHAR(9) NOT NULL,\n"
                + "  `Nome` VARCHAR(45) NULL,\n"
                + "  `User` VARCHAR(45) NOT NULL,\n"
                + "  `Senha` VARCHAR(45) NOT NULL,\n"
                + "  `Funcao` VARCHAR(13) NULL,\n"
                + "  PRIMARY KEY (`Cpf`))";
        stmt.executeUpdate(query);
        stmt = conexao.createStatement();
        query = "CREATE UNIQUE INDEX IF NOT EXISTS `Cpf_UNIQUE` "
                + "ON `FUNCIONARIO` (`Cpf`);";
        stmt.executeUpdate(query);
    }

    private void inserirFuncionariosIniciais() {
        Funcionario funcionario;

        funcionario = new Funcionario();
        funcionario.setCpf("12345678901");
        funcionario.setNome("Administrador");
        funcionario.setLogin(new Login("admin", "12345"));
        funcionario.setFuncao(FuncaoEnum.ADMINISTRADOR.toString());
        funcionario.inserirNoBanco();

        funcionario = new Funcionario();
        funcionario.setCpf("12345678902");
        funcionario.setNome("Operador");
        funcionario.setLogin(new Login("operador", "12345"));
        funcionario.setFuncao(FuncaoEnum.OPERADOR.toString());
        funcionario.inserirNoBanco();

        funcionario = new Funcionario();
        funcionario.setCpf("12345678903");
        funcionario.setNome("Gestor");
        funcionario.setLogin(new Login("gestor", "12345"));
        funcionario.setFuncao(FuncaoEnum.GESTOR.toString());
        funcionario.inserirNoBanco();

    }

    public String insert(String tabela, String colunas, String valores)
            throws SQLException {

        return insert(tabela, colunas, valores, false);
    }

    public String insert(String tabela, String colunas, String valores,
            boolean ignorarSeExistir) throws SQLException {

        if (conexao == null) {
            conectar();
        }

        String todo = "INSERT " + (ignorarSeExistir ? "OR IGNORE" : "")
                + " INTO `" + tabela + "` (" + colunas + ") VALUES ("
                + valores + ");";

        Statement s = conexao.createStatement();
        s.executeUpdate(todo);

        return todo;
    }

    public ResultSet query(String tabela, String colunas, String where)
            throws SQLException {
        if (conexao == null) {
            conectar();
        }

        String sql = "SELECT " + colunas + " FROM `" + tabela + "` WHERE "
                + where;
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public boolean exists(String tabela, String colunas, String where)
            throws SQLException {
        ResultSet rs = query(tabela, colunas, where);
        return rs.next();
    }

}
