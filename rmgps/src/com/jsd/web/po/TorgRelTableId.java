package com.jsd.web.po;

/**
 * TorgRelTableId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TorgRelTableId implements java.io.Serializable {

	// Fields

	private Integer orgType;
	private Integer porgTypeId;

	// Constructors

	/** default constructor */
	public TorgRelTableId() {
	}

	/** full constructor */
	public TorgRelTableId(Integer orgType, Integer porgTypeId) {
		this.orgType = orgType;
		this.porgTypeId = porgTypeId;
	}

	// Property accessors

	public Integer getOrgType() {
		return this.orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getPorgTypeId() {
		return this.porgTypeId;
	}

	public void setPorgTypeId(Integer porgTypeId) {
		this.porgTypeId = porgTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TorgRelTableId))
			return false;
		TorgRelTableId castOther = (TorgRelTableId) other;

		return ((this.getOrgType() == castOther.getOrgType()) || (this
				.getOrgType() != null
				&& castOther.getOrgType() != null && this.getOrgType().equals(
				castOther.getOrgType())))
				&& ((this.getPorgTypeId() == castOther.getPorgTypeId()) || (this
						.getPorgTypeId() != null
						&& castOther.getPorgTypeId() != null && this
						.getPorgTypeId().equals(castOther.getPorgTypeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgType() == null ? 0 : this.getOrgType().hashCode());
		result = 37
				* result
				+ (getPorgTypeId() == null ? 0 : this.getPorgTypeId()
						.hashCode());
		return result;
	}

}