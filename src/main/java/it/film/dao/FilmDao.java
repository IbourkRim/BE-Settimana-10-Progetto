package it.film.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.film.entity.Film;



public class FilmDao implements IFilmDao {

	private EntityManager em = EntityManagerHelper.getEntityManager();
	
	@Override
	public void save(Film f) {
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();

	}

	@Override
	public void aggiorna(Film f) {
		em.getTransaction().begin();
		em.merge(f);
		em.getTransaction().commit();

	}

	
	@Override
	public void elimina(int id) {
		em.getTransaction().begin();
        em.remove(em.find(Film.class, id));
        em.getTransaction().commit();
	}

	
	@Override
	public List<Film> trovaTutti() {
		Query q = em.createNamedQuery("trovatutti");
		List<Film> listaFilm = q.getResultList();
		return listaFilm;
	}

	/**
	 * Funziona di ricerca per regista
	 * @author Rim
	 * @param regista - il nome da regista da cercare
	 * @return listaRegista
	 */
	public List<Film> trovaRegista(String regista){
		Query q = em.createQuery("SELECT f FROM Film f WHERE f.regista = '" +regista + "'");
		List<Film> listaRegista = q.getResultList();
		return listaRegista;
	}
}
