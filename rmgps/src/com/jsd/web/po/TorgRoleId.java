package com.jsd.web.po;

/**
 * TorgRoleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgRoleId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trole trole;
	private TorgTable torgTable;

	// Constructors

	/** default constructor */
	public TorgRoleId() {
	}

	/** full constructor */
	public TorgRoleId(Integer id, Trole trole, TorgTable torgTable) {
		this.id = id;
		this.trole = trole;
		this.torgTable = torgTable;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public TorgTable getTorgTable() {
		return this.torgTable;
	}

	public void setTorgTable(TorgTable torgTable) {
		this.torgTable = torgTable;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TorgRoleId))
			return false;
		TorgRoleId castOther = (TorgRoleId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTrole() == castOther.getTrole()) || (this
						.getTrole() != null
						&& castOther.getTrole() != null && this.getTrole()
						.equals(castOther.getTrole())))
				&& ((this.getTorgTable() == castOther.getTorgTable()) || (this
						.getTorgTable() != null
						&& castOther.getTorgTable() != null && this
						.getTorgTable().equals(castOther.getTorgTable())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTrole() == null ? 0 : this.getTrole().hashCode());
		result = 37 * result
				+ (getTorgTable() == null ? 0 : this.getTorgTable().hashCode());
		return result;
	}

}