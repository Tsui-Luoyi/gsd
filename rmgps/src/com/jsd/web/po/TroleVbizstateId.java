package com.jsd.web.po;

/**
 * TroleVbizstateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TroleVbizstateId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Trole trole;
	private Integer vstate;

	// Constructors

	/** default constructor */
	public TroleVbizstateId() {
	}

	/** full constructor */
	public TroleVbizstateId(Integer id, Trole trole, Integer vstate) {
		this.id = id;
		this.trole = trole;
		this.vstate = vstate;
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

	public Integer getVstate() {
		return this.vstate;
	}

	public void setVstate(Integer vstate) {
		this.vstate = vstate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TroleVbizstateId))
			return false;
		TroleVbizstateId castOther = (TroleVbizstateId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTrole() == castOther.getTrole()) || (this
						.getTrole() != null
						&& castOther.getTrole() != null && this.getTrole()
						.equals(castOther.getTrole())))
				&& ((this.getVstate() == castOther.getVstate()) || (this
						.getVstate() != null
						&& castOther.getVstate() != null && this.getVstate()
						.equals(castOther.getVstate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTrole() == null ? 0 : this.getTrole().hashCode());
		result = 37 * result
				+ (getVstate() == null ? 0 : this.getVstate().hashCode());
		return result;
	}

}