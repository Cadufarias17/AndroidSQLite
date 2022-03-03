package br.edu.ifsul.pdm.aula10sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PessoaDAO {
    // costante com nome da classe
    public static  String NOME_TABELA_PESSOAS = "pessoas";

    private SQLiteDatabase banco;

    public PessoaDAO(Context context){
        CriaBanco criaBanco = new CriaBanco(context);
        banco = criaBanco.getWritableDatabase();
    }


    public void inserir(Pessoa pessoa){
        // configura os parâmetros a serem enviados - coluna e valor
        ContentValues cv = new ContentValues();
        cv.put("nome", pessoa.getNome());
        cv.put("idade", pessoa.getIdade());
        cv.put("email", pessoa.getEmail());
        cv.put("genero", String.valueOf(pessoa.getSexo())); // f para feminino e m para masculino
        cv.put("estado_civil",pessoa.getEstadoCivil());

        // modifica a tabela e recupera o id gerado
        Long idGerado = banco.insert(NOME_TABELA_PESSOAS, null, cv);
        // adiciona o id gerado no banco de dados na pessoa incluída
        pessoa.setId(idGerado.intValue());
    }

    public ArrayList<Pessoa> listarTodos() {
        ArrayList<Pessoa> lista = new ArrayList<>();

        // parâmetros da função query
        final String TABELA = NOME_TABELA_PESSOAS;
        final String[] COLUNAS = {"_id", "nome", "idade",
                "email", "genero", "estado_civil"};
        final String ORDER_BY = "nome ASC";
        final String WHERE = null;
        final String[] WHERE_PARAM = null;
        final String GROUP_BY = null;
        final String HAVING = null;
        // SQL correspondente:
        /* SELECT _id, nome, idade, email, genero, estado_civil
            FROM pessoas ORDER BY nome ASC  */
        Cursor cursor = banco.query(
                TABELA,     // 1 - nome da tabela
                COLUNAS,    // 2 - vetor com nome das colunas
                WHERE,      // 3 - parte WHERE do SQL
                WHERE_PARAM,// 4 - valores da parte WHERE do SELECT
                GROUP_BY,   // 5 - parte GROUP BY do SQL
                HAVING,     // 6 - parte HAVING do SQL
                ORDER_BY);  // 7 - parte ORDER BY do SQL

        while (cursor.moveToNext()) {
            // Obtem o valor das colunas
            int id = cursor.getInt(0);                  // _id
            String nome = cursor.getString(1);          // nome
            int idade = cursor.getInt(2);               // idade
            String email = cursor.getString(3);         // email
            String genero = cursor.getString(4);        // genero
            String estadoCivil = cursor.getString(5);   // estado_civil
            // instanciar um objeto Pessoa
            Pessoa p = new Pessoa();
            // inserir valores no objeto pessoa
            p.setId(id);
            p.setNome(nome);
            p.setIdade(idade);
            p.setEmail(email);
            p.setSexo(genero.charAt(0)); // f para feminino e m para masculino
            p.setEstadoCivil(estadoCivil);
            // adicionar Pessoa p na lista
            lista.add(p);
        }
        return lista;
    }

    public ArrayList<Pessoa> listarTodosPorNome(String filtroNome) {
        ArrayList<Pessoa> lista = new ArrayList<>();

        // parâmetros da função query
        final String TABELA = NOME_TABELA_PESSOAS;
        final String[] COLUNAS = {"_id", "nome", "idade",
                "email", "genero", "estado_civil"};
        final String ORDER_BY = "nome ASC";
        final String WHERE = "nome LIKE ?";
        final String[] WHERE_PARAM = {"%"+filtroNome+"%"};
        final String GROUP_BY = null;
        final String HAVING = null;
        // SQL correspondente:
   /* SELECT _id, nome, idade, email, genero, estado_civil
        FROM pessoas WHERE nome LIKE ? ORDER BY nome ASC  */
        Cursor cursor = banco.query(
                TABELA,     // 1 - nome da tabela
                COLUNAS,    // 2 - vetor com nome das colunas
                WHERE,      // 3 - parte WHERE do SQL
                WHERE_PARAM,// 4 - valores da parte WHERE do SELECT
                GROUP_BY,   // 5 - parte GROUP BY do SQL
                HAVING,     // 6 - parte HAVING do SQL
                ORDER_BY);  // 7 - parte ORDER BY do SQL

        while (cursor.moveToNext()) {
            // Obtem o valor das colunas
            int id = cursor.getInt(0);                  // _id
            String nome = cursor.getString(1);          // nome
            int idade = cursor.getInt(2);               // idade
            String email = cursor.getString(3);         // email
            String genero = cursor.getString(4);        // genero
            String estadoCivil = cursor.getString(5);   // estado_civil
            // instanciar um objeto Pessoa
            Pessoa p = new Pessoa();
            // inserir valores no objeto pessoa
            p.setId(id);
            p.setNome(nome);
            p.setIdade(idade);
            p.setEmail(email);
            p.setSexo(genero.charAt(0)); // f para feminino e m para masculino
            p.setEstadoCivil(estadoCivil);
            // adicionar Pessoa p na lista
            lista.add(p);
        }
        return lista;
    }

    public void atualizar(Pessoa pessoa){
        // configura os parâmetros a serem atualizados - coluna e valor
        ContentValues cv = new ContentValues();
        cv.put("nome", pessoa.getNome());
        cv.put("idade", pessoa.getIdade());
        cv.put("email", pessoa.getEmail());
        cv.put("genero", String.valueOf(pessoa.getSexo())); // f para feminino e m para masculino
        cv.put("estado_civil", pessoa.getEstadoCivil());

        // parâmetros WHERE da função de alteração
        final String WHERE          = "_id = ?";
        final String [] WHERE_PARAM = {String.valueOf(pessoa.getId())};

        // modifica a tabela montando um SQL equivalente a:
        /* UPDATE INTO pessoas SET nome = ?, idade = ?, email = ?,
                              genero = ?, estado_civil = ?
           WHERE _id = ? ; */
        banco.update(
                NOME_TABELA_PESSOAS, // 1 - tabela
                cv,                  // 2 - valores a serem atualizados
                WHERE,               // 3 - parte WHERE do SQL
                WHERE_PARAM);        // 4 - valores da parte WHERE do SQL
    }

    public void excluir(int id){
        // parâmetros WHERE da consulta DELETE
        final String WHERE          = "_id = ?";
        final String [] WHERE_PARAM = {String.valueOf(id)};

        // exclui uma pessoa na tabela. Corresponde ao SQL
        // DELETE FROM pessoas WHERE _id = ?
        banco.delete(
                NOME_TABELA_PESSOAS,    // 1 - tabela
                WHERE,                  // 2 - parte WHERE do SQL
                WHERE_PARAM);           // 3 - valores da parte WHERE do SQL
    }

    public void fechaConexaoBanco(){
        // libera o banco
        banco.close();
    }

}
