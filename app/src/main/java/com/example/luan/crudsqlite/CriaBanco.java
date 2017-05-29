package com.example.luan.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luan on 28/05/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    static final String NOME_BANCO = "banco.db";
    static final String TABELA = "livros";
    static final String ID = "_id";
    static final String TITULO = "titulo";
    static final String AUTOR = "autor";
    static final String EDITORA = "editora";
    static final int VERSAO = 1;


    /**
     * Construtor que passará para a super classe as informações do local e versão do banco
     * @param context
     */
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    /**
     *  É chamado quando a aplicação cria o banco de dados pela primeira vez.
     *  db é uma instância da classe SQLiteDatabase
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+" ("
                + ID + "integer primary key autoincrement,"
                + TITULO + "text,"
                + AUTOR + "text,"
                + EDITORA + "text"
                +")";
        db.execSQL(sql);
    }

    /**
     * É o método responsável por atualizar o banco de dados com alguma informação estrutural que tenha sido alterada.
     *  db é uma instância da classe SQLiteDatabase
     *  oldVersion é a versão antiga da tabela
     *  newVersion é a nova versão para ao qual o upgrade deve ser executado
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
