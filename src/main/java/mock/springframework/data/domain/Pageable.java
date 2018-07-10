/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock.springframework.data.domain;




/**
 * Abstract interface for pagination information.
 *
 * @author Oliver Gierke Akinniranye James Ayodele 
 */
public interface Pageable {

	/**
	 * Returns the page to be returned.
	 *
	 * @return the page to be returned.
	 */
	int getPageNumber();


	/**
	 * Returns the number of items to be returned.
	 *
	 * @return the number of items of that page
	 */
	int getPageSize();


	/**
	 * Returns the offset to be taken according to the underlying page and page
	 * size.
	 *
	 * @return the offset to be taken
	 */
	int getOffset();


	/**
	 * Returns the sorting parameters.
	 *
	 * @return
	 */
	Sort getSort();
}

