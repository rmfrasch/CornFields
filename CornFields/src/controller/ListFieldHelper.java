package controller;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.FieldDescription;

/**
 * @author RYANF This class is the helper methods to communicate to the SQL
 *         database.
 */
public class ListFieldHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CornFields");

	// Connects to the JPA to control the database and insert a field.
	public void insertField(FieldDescription fd) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(fd);
		em.getTransaction().commit();
		em.close();
	}

	// Shows all items in the database
	public List<FieldDescription> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<FieldDescription> allItems = em.createQuery("SELECT i FROM FIELD i").getResultList();
		return allItems;
	}

	// Deletes an item from the database and commits.
	public void deleteItem(FieldDescription toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<FieldDescription> typedQuery = em
				.createQuery("select fd from FIELD fd where fd.fieldName = :selectedName", FieldDescription.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedName", toDelete.getFieldName());

		// we only want one result
		typedQuery.setMaxResults(1);
		// get the result and save it into a new list item
		FieldDescription result = typedQuery.getSingleResult();
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	// searches for a particular record in the table based on the fieldID.
	public FieldDescription searchForItemByFieldID(int fieldID) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		FieldDescription found = em.find(FieldDescription.class, fieldID);
		em.close();
		return found;
	}
	//Searches for a particular record in the table based on the Field Name.
	public List<FieldDescription> searchForItemByFieldName(String FieldName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<FieldDescription> typedQuery = em
				.createQuery("select fd from FIELD fd where fd.fieldName = :selectedItem", FieldDescription.class);
		typedQuery.setParameter("selectedItem", FieldName);
		List<FieldDescription> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
//Updates the variable of the object and commits the changes.
	public void updateField(FieldDescription toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	//Closes connections to the database after we're done with the program.
	public void cleanUp() {
		emfactory.close();
	}
}
