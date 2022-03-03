package br.edu.ifsul.pdm.aula10sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instancia o objeto para Criar e acessar o BD
        CriaBanco bd = new CriaBanco(getBaseContext());
        // Instancia objeto para Manipular pessoa
        PessoaDAO pessoaDAO = new PessoaDAO(getBaseContext());
        // Criar uma pessoa para inserir no banco
        Pessoa p1 = new Pessoa();
        p1.setNome("Thrump");
        p1.setEmail("thrump@eua.com");
        p1.setEstadoCivil("Casado");
        p1.setIdade(70);
        p1.setSexo('m');
        // insere no banco
        pessoaDAO.inserir(p1);
        // Listar todas as pessoas
        ArrayList<Pessoa> lista = pessoaDAO.listarTodos();
        for(Pessoa p : lista){
            Log.d("TESTE_BANCO",p.getId()+" "+p.getNome());
        }
        // criar uma segunda pessoa
        Pessoa p2 = new Pessoa();
        p2.setNome("Thais");
        p2.setEmail("thais6715@gmail.com");
        p2.setEstadoCivil("Solteira");
        p2.setIdade(26);
        p2.setSexo('f');
        // insere no banco
        pessoaDAO.inserir(p2);
        // listar todos com nome de Thais
        Log.d("TESTE","Listas com filtro nome");
        lista = pessoaDAO.listarTodosPorNome("ais");
        for(Pessoa p : lista){
            Log.d("TESTE_BANCO",p.getId()+" "+p.getNome());
        }
        // atualizar
        p2.setNome("Amanda");
        pessoaDAO.atualizar(p2);
        // excluir
        pessoaDAO.excluir(p1.getId());
        // listar tudo
        Log.d("TESTE","Listar tudo");
        lista = pessoaDAO.listarTodos();
        for(Pessoa p : lista){
            Log.d("TESTE_BANCO",p.getId()+" "+p.getNome());
        }
    }
}
