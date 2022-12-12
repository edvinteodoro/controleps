/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "role_permission")
@NamedQueries({
    @NamedQuery(name = "RolePermission.findAll", query = "SELECT r FROM RolePermission r"),
    @NamedQuery(name = "RolePermission.findByRoleId", query = "SELECT r FROM RolePermission r WHERE r.rolePermissionPK.roleId = :roleId"),
    @NamedQuery(name = "RolePermission.findByPermissionId", query = "SELECT r FROM RolePermission r WHERE r.rolePermissionPK.permissionId = :permissionId"),
    @NamedQuery(name = "RolePermission.findByCreatedAt", query = "SELECT r FROM RolePermission r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "RolePermission.findByUpdatedAt", query = "SELECT r FROM RolePermission r WHERE r.updatedAt = :updatedAt")})
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolePermissionPK rolePermissionPK;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "permissionId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permission permission;
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Role role;

    public RolePermission() {
    }

    public RolePermission(RolePermissionPK rolePermissionPK) {
        this.rolePermissionPK = rolePermissionPK;
    }

    public RolePermission(RolePermissionPK rolePermissionPK, Date createdAt) {
        this.rolePermissionPK = rolePermissionPK;
        this.createdAt = createdAt;
    }

    public RolePermission(long roleId, long permissionId) {
        this.rolePermissionPK = new RolePermissionPK(roleId, permissionId);
    }

    public RolePermissionPK getRolePermissionPK() {
        return rolePermissionPK;
    }

    public void setRolePermissionPK(RolePermissionPK rolePermissionPK) {
        this.rolePermissionPK = rolePermissionPK;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermissionPK != null ? rolePermissionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermission)) {
            return false;
        }
        RolePermission other = (RolePermission) object;
        if ((this.rolePermissionPK == null && other.rolePermissionPK != null) || (this.rolePermissionPK != null && !this.rolePermissionPK.equals(other.rolePermissionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.RolePermission[ rolePermissionPK=" + rolePermissionPK + " ]";
    }
    
}
