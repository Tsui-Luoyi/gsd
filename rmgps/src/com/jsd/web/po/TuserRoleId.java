package com.jsd.web.po;

/**
 * TuserRoleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TuserRoleId implements java.io.Serializable {

	// Fields

	private Tuser tuser;
	private Trole trole;

	// Constructors

	/** default constructor */
	public TuserRoleId() {
	}

	/** full constructor */
	public TuserRoleId(Tuser tuser, Trole trole) {
		this.tuser = tuser;
		this.trole = trole;
	}

	// Property accessors

	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TuserRoleId))
			return false;
		TuserRoleId castOther = (TuserRoleId) other;

		return ((this.getTuser() == castOther.getTuser()) || (this.getTuser() != null
				&& castOther.getTuser() != null && this.getTuser().equals(
				castOther.getTuser())))
				&& ((this.getTrole() == castOther.getTrole()) || (this
						.getTrole() != null
						&& castOther.getTrole() != null && this.getTrole()
						.equals(castOther.getTrole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTuser() == null ? 0 : this.getTuser().hashCode());
		result = 37 * result
				+ (getTrole() == null ? 0 : this.getTrole().hashCode());
		return result;
	}

}