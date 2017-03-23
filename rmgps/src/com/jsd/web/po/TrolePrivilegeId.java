package com.jsd.web.po;

/**
 * TrolePrivilegeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrolePrivilegeId implements java.io.Serializable {

	// Fields

	private Tprivilege tprivilege;
	private Trole trole;
	private String privilegeValue;

	// Constructors

	/** default constructor */
	public TrolePrivilegeId() {
	}

	/** full constructor */
	public TrolePrivilegeId(Tprivilege tprivilege, Trole trole,
			String privilegeValue) {
		this.tprivilege = tprivilege;
		this.trole = trole;
		this.privilegeValue = privilegeValue;
	}

	// Property accessors

	public Tprivilege getTprivilege() {
		return this.tprivilege;
	}

	public void setTprivilege(Tprivilege tprivilege) {
		this.tprivilege = tprivilege;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public String getPrivilegeValue() {
		return this.privilegeValue;
	}

	public void setPrivilegeValue(String privilegeValue) {
		this.privilegeValue = privilegeValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TrolePrivilegeId))
			return false;
		TrolePrivilegeId castOther = (TrolePrivilegeId) other;

		return ((this.getTprivilege() == castOther.getTprivilege()) || (this
				.getTprivilege() != null
				&& castOther.getTprivilege() != null && this.getTprivilege()
				.equals(castOther.getTprivilege())))
				&& ((this.getTrole() == castOther.getTrole()) || (this
						.getTrole() != null
						&& castOther.getTrole() != null && this.getTrole()
						.equals(castOther.getTrole())))
				&& ((this.getPrivilegeValue() == castOther.getPrivilegeValue()) || (this
						.getPrivilegeValue() != null
						&& castOther.getPrivilegeValue() != null && this
						.getPrivilegeValue().equals(
								castOther.getPrivilegeValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTprivilege() == null ? 0 : this.getTprivilege()
						.hashCode());
		result = 37 * result
				+ (getTrole() == null ? 0 : this.getTrole().hashCode());
		result = 37
				* result
				+ (getPrivilegeValue() == null ? 0 : this.getPrivilegeValue()
						.hashCode());
		return result;
	}

}