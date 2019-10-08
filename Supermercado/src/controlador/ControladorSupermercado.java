/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.DaoSupermercado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Supermercado;
import tela.manutencao.ManutencaoSupermercado;
/**
 *
 * @author Administrador
 */
public class ControladorSupermercado {
    public static void inserir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setNome_fantasia(man.jTextField1.getText());
        objeto.setRazao_social(man.jTextField2.getText());
        objeto.setFundacao(LocalDate.parse(man.jTextField3.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setFuncionarios(Integer.parseInt(man.jTextField4.getText()));
        objeto.setValor(Double.parseDouble(man.jTextField5.getText()));
        
        boolean resultado = DaoSupermercado.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}
    
    public static void alterar(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jTextField6.getText()));
        objeto.setNome_fantasia(man.jTextField1.getText());
        objeto.setRazao_social(man.jTextField2.getText());
        objeto.setFundacao(LocalDate.parse(man.jTextField3.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setFuncionarios(Integer.parseInt(man.jTextField4.getText()));
        objeto.setValor(Double.parseDouble(man.jTextField5.getText()));
        
        boolean resultado = DaoSupermercado.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
     public static void excluir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setCodigo(Integer.parseInt(man.jTextField6.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoSupermercado.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
     
     public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome fantasia");
        modelo.addColumn("Razão social");
        modelo.addColumn("Fundação");
        modelo.addColumn("Número de funcionários");
        modelo.addColumn("Valor na bolsa");
        List<Supermercado> resultados = DaoSupermercado.consultar();
        for (Supermercado objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome_fantasia());
            linha.add(objeto.getRazao_social());
            linha.add(objeto.getFundacao());
            linha.add(objeto.getFuncionarios());
            linha.add(objeto.getValor());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
     
     public static void atualizaCampos(ManutencaoSupermercado man, int pk){ 
        Supermercado objeto = DaoSupermercado.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jTextField6.setText(objeto.getCodigo().toString());
        man.jTextField1.setText(objeto.getNome_fantasia());
        man.jTextField2.setText(objeto.getRazao_social());
        man.jTextField3.setText(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jTextField4.setText(objeto.getFuncionarios().toString());
        man.jTextField5.setText(objeto.getValor().toString());
        
        man.jTextField6.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
