/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author edvin
 */
@Embeddable
public class RolePermissionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "roleId")
    private long roleId;
    @Basic(optional = false)
    @Column(name = "permissionId")
    private long permissionId;

    public RolePermissionPK() {
    }

    public RolePermissionPK(long roleId, long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) permissionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermissionPK)) {
            return false;
        }
        RolePermissionPK other = (RolePermissionPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.permissionId != other.permissionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.RolePermissionPK[ roleId=" + roleId + ", permissionId=" + permissionId + " ]";
    }
    
}
