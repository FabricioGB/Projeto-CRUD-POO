package univs.edu.funcionario;

import univs.edu.funcionario.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;

public class FuncionarioDAO {

    private Session sessao;
    private Transaction trasacao;

    public void salvar(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();

        trasacao = sessao.beginTransaction();
        if(funcionario.getIdFuncionario()==0){
            sessao.save(funcionario);
        }else{
            editar(funcionario);
        }
        
        trasacao.commit();
        sessao.clear();
    }

    public void excluir(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();

        trasacao = sessao.beginTransaction();
        sessao.delete(funcionario);
        trasacao.commit();
        sessao.clear();
    }

    public void editar(Funcionario funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();

        trasacao = sessao.beginTransaction();
        sessao.update(funcionario);
        trasacao.commit();
        sessao.clear();
    }
    
        public Funcionario pesquisar(int id) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trasacao = sessao.beginTransaction();
        Funcionario funcionario = (Funcionario) sessao.createCriteria(Funcionario.class).add(Restrictions.eq("idFuncionario", id)).uniqueResult();
        sessao.close();
        return funcionario;
    } 
       
    
           public List<Funcionario> listarFuncionarios() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trasacao = sessao.beginTransaction();
        List<Funcionario> funcionario = sessao.createCriteria(Funcionario.class).list();
        sessao.close();
        return funcionario;
    }
}
