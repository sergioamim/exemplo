package br.com.exemplo.dominio.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

@Name("dataMapper")
public class DataMapper<T> {
	
	@In
	private EntityManager entityManager;
	
	public void salvar(T objeto){
		entityManager.persist(objeto);
	}
	
	public void atualizar(T objeto) {
		entityManager.merge(objeto);
	}
	
	public void excluir(T objeto){
		objeto = entityManager.merge(objeto);
		entityManager.remove(objeto);
	}
	
	@SuppressWarnings("unchecked")
	public T recuperar(Class classe,Long id){
		return (T) entityManager.find(classe, id);
	}
	
	/*private T getType() {
		return null;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<T> consultarPorCampo(Class classe, String campo, Object valor){
		Query query = entityManager.createQuery("from " + classe.getName() + " m where m." + campo + " like :valor order by " + campo);
		query.setParameter("valor", "%" + valor + "%");
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar(Class classe){
		return entityManager.createQuery("from " + classe.getName() + " m").getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	

}
