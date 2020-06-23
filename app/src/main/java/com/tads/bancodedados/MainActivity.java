package com.tads.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Criar banco de dados
            SQLiteDatabase db = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabela
            db.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");
            //db.execSQL("DROP TABLE pessoas");

            //Update
            db.execSQL("UPDATE pessoas SET nome = 'Jaime', idade = 41 WHERE id = 4");

            //Delete
            db.execSQL("DELETE FROM pessoas");

            //Inserir dados
            //db.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Dolores', 30)");
            //db.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Jhon', 22)");
            //db.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Matheus', 25)");

            //Recuperar dados
            //db.rawQuery("SELECT nome, idade FROM pessoas", null);

            //Recuperar e exibir dados
            String filtro = "th";

            //String consulta = "SELECT nome, idade " +
                                //"FROM pessoas WHERE nome LIKE '%" + filtro + "%'";

            String consulta = "SELECT id, nome, idade " +
                    "FROM pessoas WHERE 1=1";

            Cursor cursor = db.rawQuery(consulta, null);

            //Indice da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            int indiceId = cursor.getColumnIndex("id");

            //Posicionar o cursor no inicio
            cursor.moveToFirst();

            while (cursor != null){
                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO - id ", id + " | nome: " + nome + " | idade: " + idade);
                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}