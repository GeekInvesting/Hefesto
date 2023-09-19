package com.api.hefesto.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.data.annotation.Persistent;

@Entity
@Table(name = "TB_COMPANY")
public class CompanyModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String companyName;
    private String companyCode;
    private String companyLogo;
    private String companySiteRi;
    private String companyMainActivity;
    private String companyAbout;
    private boolean companyEnabled;
    private boolean companyDeleted;

    @OneToMany(mappedBy = "ticketCompany")
    private List<TicketModel> tickets;

    public CompanyModel() {
    }

    public CompanyModel(UUID id, String companyName, String companyCode, String companyLogo, String companySiteRi,
            String companyMainActivity, String companyAbout, boolean companyEnabled, boolean companyDeleted) {
        this.id = id;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.companyLogo = companyLogo;
        this.companySiteRi = companySiteRi;
        this.companyMainActivity = companyMainActivity;
        this.companyAbout = companyAbout;
        this.companyEnabled = companyEnabled;
        this.companyDeleted = companyDeleted;
    }

    public CompanyModel(String companyName){
        this.id = null;
        this.companyName = companyName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanySiteRi() {
        return companySiteRi;
    }

    public void setCompanySiteRi(String companySiteRi) {
        this.companySiteRi = companySiteRi;
    }

    public String getCompanyMainActivity() {
        return companyMainActivity;
    }

    public void setCompanyMainActivity(String companyMainActivity) {
        this.companyMainActivity = companyMainActivity;
    }

    public String getCompanyAbout() {
        return companyAbout;
    }

    public void setCompanyAbout(String companyAbout) {
        this.companyAbout = companyAbout;
    }

    public boolean isCompanyEnabled() {
        return companyEnabled;
    }

    public void setCompanyEnabled(boolean companyEnabled) {
        this.companyEnabled = companyEnabled;
    }

    public boolean isCompanyDeleted() {
        return companyDeleted;
    }

    public void setCompanyDeleted(boolean companyDeleted) {
        this.companyDeleted = companyDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
        result = prime * result + ((companyLogo == null) ? 0 : companyLogo.hashCode());
        result = prime * result + ((companySiteRi == null) ? 0 : companySiteRi.hashCode());
        result = prime * result + ((companyMainActivity == null) ? 0 : companyMainActivity.hashCode());
        result = prime * result + ((companyAbout == null) ? 0 : companyAbout.hashCode());
        result = prime * result + (companyEnabled ? 1231 : 1237);
        result = prime * result + (companyDeleted ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompanyModel other = (CompanyModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (companyCode == null) {
            if (other.companyCode != null)
                return false;
        } else if (!companyCode.equals(other.companyCode))
            return false;
        if (companyLogo == null) {
            if (other.companyLogo != null)
                return false;
        } else if (!companyLogo.equals(other.companyLogo))
            return false;
        if (companySiteRi == null) {
            if (other.companySiteRi != null)
                return false;
        } else if (!companySiteRi.equals(other.companySiteRi))
            return false;
        if (companyMainActivity == null) {
            if (other.companyMainActivity != null)
                return false;
        } else if (!companyMainActivity.equals(other.companyMainActivity))
            return false;
        if (companyAbout == null) {
            if (other.companyAbout != null)
                return false;
        } else if (!companyAbout.equals(other.companyAbout))
            return false;
        if (companyEnabled != other.companyEnabled)
            return false;
        if (companyDeleted != other.companyDeleted)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CompanyModel [id=" + id + ", companyName=" + companyName + ", companyCode=" + companyCode
                + ", companyLogo=" + companyLogo + ", companySiteRi=" + companySiteRi + ", companyMainActivity="
                + companyMainActivity + ", companyAbout=" + companyAbout + ", companyEnabled=" + companyEnabled
                + ", companyDeleted=" + companyDeleted + "]";
    }

}
