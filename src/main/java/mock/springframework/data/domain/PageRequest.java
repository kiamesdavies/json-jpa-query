/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock.springframework.data.domain;

import java.io.Serializable;
import mock.springframework.data.domain.Sort.Direction;

/**
 *
 * @author Akinniranye James Ayodele 
 */
public class PageRequest implements Pageable, Serializable {

	private static final long serialVersionUID = 8280485938848398236L;

	private final int page;
	private final int size;
	private final Sort sort;


	/**
	 * Creates a new {@link PageRequest}. Pages are zero indexed, thus providing
	 * 0 for {@code page} will return the first page.
	 *
	 * @param size
	 * @param page
	 */
	public PageRequest(int page, int size) {

		this(page, size, null);
	}


	/**
	 * Creates a new {@link PageRequest} with sort parameters applied.
	 *
	 * @param page
	 * @param size
	 * @param direction
	 * @param properties
	 */
	public PageRequest(int page, int size, Direction direction,
										 String... properties) {

		this(page, size, new Sort(direction, properties));
	}


	/**
	 * Creates a new {@link PageRequest} with sort parameters applied.
	 *
	 * @param page
	 * @param size
	 * @param sort
	 */
	public PageRequest(int page, int size, Sort sort) {

		if (0 > page) {
			throw new IllegalArgumentException(
					"Page index must not be less than zero!");
		}

		if (0 >= size) {
			throw new IllegalArgumentException(
					"Page size must not be less than or equal to zero!");
		}

		this.page = page;
		this.size = size;
		this.sort = sort;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see org.springframework.data.domain.Pageable#getPageSize()
			 */
	public int getPageSize() {

		return size;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see org.springframework.data.domain.Pageable#getPageNumber()
			 */
	public int getPageNumber() {

		return page;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see org.springframework.data.domain.Pageable#getFirstItem()
			 */
	public int getOffset() {

		return page * size;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see org.springframework.data.domain.Pageable#getSort()
			 */
	public Sort getSort() {

		return sort;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see java.lang.Object#equals(java.lang.Object)
			 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageRequest)) {
			return false;
		}

		PageRequest that = (PageRequest) obj;

		boolean pageEqual = this.page == that.page;
		boolean sizeEqual = this.size == that.size;

		boolean sortEqual =
				this.sort == null ? that.sort == null : this.sort
						.equals(that.sort);

		return pageEqual && sizeEqual && sortEqual;
	}


	/*
			 * (non-Javadoc)
			 *
			 * @see java.lang.Object#hashCode()
			 */
	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + page;
		result = 31 * result + size;
		result = 31 * result + (null == sort ? 0 : sort.hashCode());

		return result;
	}
}

