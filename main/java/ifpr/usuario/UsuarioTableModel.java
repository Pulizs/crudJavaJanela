/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifpr.usuario;

import ifpr.usuario.gerenciar.GerenciarUsuario;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author paulojr
 */
public class UsuarioTableModel extends
        AbstractTableModel {
    
    private GerenciarUsuario gerUsuario;
    private List<Usuario> usuarios;
    
    private String[] nomesColunas = {
      "ID", "Nome", "E-mail", "Data de nascimento",
      "Sexo"
    };
    
    // Método construtor
    // Exatamente o mesmo nome da classe
    // Não tem retorno
    public UsuarioTableModel(GerenciarUsuario gerUsuario)
    {
        this.gerUsuario = gerUsuario;
        usuarios = gerUsuario.listar();
    }
    
    public void pesquisar(String nome)
    {
        usuarios = gerUsuario.pesquisarPorNome(nome);
        this.fireTableDataChanged();
    }
    
    public void atualizar()
    {
        usuarios = gerUsuario.listar();
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return nomesColunas[column];
    }
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return nomesColunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if ( columnIndex == 0 )
        {
            Usuario user = usuarios.get(rowIndex);
            return user.getId();
        }
        
        if ( columnIndex == 1 )
        {
            Usuario user = usuarios.get(rowIndex);
            return user.getNome();
        }
        
        if ( columnIndex == 2 )
        {
            Usuario user = usuarios.get(rowIndex);
            return user.getEmail();
        }
        
        if ( columnIndex == 3 )
        {
            Usuario user = usuarios.get(rowIndex);
            DateTimeFormatter dtf =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dtf.format(user.getDataNascimento());
        }
        
        if ( columnIndex == 4 )
        {
            Usuario user = usuarios.get(rowIndex);
            return user.getSexo().toString();
        }
        return null;
    }
    
}
