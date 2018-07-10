/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock.springframework.data.domain;


import java.util.Iterator;
import java.util.List;


/**
 * A page is a sublist of a list of objects. It allows gain information about
 * the position of it in the containing entire list.
 *
 * @param <T>
 * @author Oliver Gierke Akinniranye James Ayodele 
 */
public interface Page<T> extends Iterable<T> {

	/**
	 * Returns the number of the current page. Is always positive and less that
	 * {@code Page#getTotalPages()}.
	 *
	 * @return the number of the current page
	 */
	int getNumber();


	/**
	 * Returns the size of the page.
	 *
	 * @return the size of the page
	 */
	int getSize();


	/**
	 * Returns the number of total pages.
	 *
	 * @return the number of toral pages
	 */
	int getTotalPages();


	/**
	 * Returns the number of elements currently on this page.
	 *
	 * @return the number of elements currently on this page
	 */
	int getNumberOfElements();


	/**
	 * Returns the total amount of elements.
	 *
	 * @return the total amount of elements
	 */
	long getTotalElements();


	/**
	 * Returns if there is a previous page.
	 *
	 * @return if there is a previous page
	 */
	boolean hasPreviousPage();


	/**
	 * Returns whether the current page is the first one.
	 *
	 * @return
	 */
	boolean isFirstPage();


	/**
	 * Returns if there is a next page.
	 *
	 * @return if there is a next page
	 */
	boolean hasNextPage();


	/**
	 * Returns whether the current page is the last one.
	 *
	 * @return
	 */
	boolean isLastPage();


	/*
			 * (non-Javadoc)
			 *
			 * @see java.lang.Iterable#iterator()
			 */
	Iterator<T> iterator();


	/**
	 * Returns the page content as {@link List}.
	 *
	 * @return
	 */
	List<T> getContent();


	/**
	 * Returns whether the {@link Page} has content at all.
	 *
	 * @return
	 */
	boolean hasContent();


	/**
	 * Returns the sorting parameters for the page.
	 *
	 * @return
	 */
	Sort getSort();
}