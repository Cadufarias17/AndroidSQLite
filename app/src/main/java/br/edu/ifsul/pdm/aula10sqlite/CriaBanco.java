package br.edu.ifsul.pdm.aula10sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    // nome do banco de dados
    private static final String NOME_BANCO = "pdm.banco.bd";
    // versão do banco de dados
    private static final int VERSAO = 1;

    public CriaBanco(Context contexto){
        super(contexto,     // 1 - contexto da Activity
                NOME_BANCO, // 2 - nome do banco de dados
                null,       // 3 - cursor customizado (usar null)
                VERSAO);    // 4 - versão atual do banco
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // função para criação das tabelas no banco de dados
        db.execSQL("CREATE TABLE pessoas (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome VARCHAR(200), " +
                " idade INTEGER, " +
                " email TEXT, " +
                " genero TEXT, " +
                " estado_civil TEXT " +
                " );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // função executada nas mudanças de versão
        db.execSQL("DROP TABLE IF EXISTS pessoas;");
        // cria o banco novamente
        onCreate(db);
    }
}
