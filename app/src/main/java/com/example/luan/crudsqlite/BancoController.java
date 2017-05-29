package com.example.luan.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Luan on 28/05/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    /**
     * Veja que criamos um construtor público e instanciamos o atributo banco
     * e o contexto que é passado por parâmetro a Activity.
     * @param context
     */
    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String titulo, String autor, String editora) {
        ContentValues valores;
        long resultado;

        /* É importante lembrar que o atributo db deve receber o resultado do método getWritableDatabase,
         * que diz ao Android que o banco será utilizado para leitura e escrita de dados.
         */
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        /*
         * O método INSERT recebe como parâmetro a tabela em que os dados serão manipulados,
         * um parâmetro nulo e o map com os dados que serão inseridos no banco no formato key/value
         */
        resultado = db.insert(CriaBanco.TABELA, null, valores);

        /* Lembre-se sempre de encerrar a conexão ao final de uma operação. */
        db.close();

        if (resultado == -1){
            return "Erro ao inserir registro";
        }else {
            return "Registro Inserido com sucesso";
        }

    }

    /**
     *
     * @return
     */
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(CriaBanco.TABELA,where,null);
        db.close();
    }
}
