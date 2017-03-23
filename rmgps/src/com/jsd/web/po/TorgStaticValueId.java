package com.jsd.web.po;

/**
 * TorgStaticValueId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgStaticValueId implements java.io.Serializable {

	// Fields

	private TorgStaticTable torgStaticTable;
	private String code;

	// Constructors

	/** default constructor */
	public TorgStaticValueId() {
	}

	/** full constructor */
	public TorgStaticValueId(TorgStaticTable torgStaticTable, String code) {
		this.torgStaticTable = torgStaticTable;
		this.code = code;
	}

	// Property accessors

	public TorgStaticTable getTorgStaticTable() {
		return this.torgStaticTable;
	}

	public void setTorgStaticTable(TorgStaticTable torgStaticTable) {
		this.torgStaticTable = torgStaticTable;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TorgStaticValueId))
			return false;
		TorgStaticValueId castOther = (TorgStaticValueId) other;

		return ((this.getTorgStaticTable() == castOther.getTorgStaticTable()) || (this
				.getTorgStaticTable() != null
				&& castOther.getTorgStaticTable() != null && this
				.getTorgStaticTable().equals(castOther.getTorgStaticTable())))
				&& ((this.getCode() == castOther.getCode()) || (this.getCode() != null
						&& castOther.getCode() != null && this.getCode()
						.equals(castOther.getCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTorgStaticTable() == null ? 0 : this.getTorgStaticTable()
						.hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		return result;
	}

}