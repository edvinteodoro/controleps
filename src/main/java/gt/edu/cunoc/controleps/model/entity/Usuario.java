/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.model.entity;

import gt.edu.cunoc.controleps.model.dto.UsuarioDto;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edvin
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByRegistroAcademico", query = "SELECT u FROM Usuario u WHERE u.registroAcademico = :registroAcademico"),
    @NamedQuery(name = "Usuario.findByNumeroColegiado", query = "SELECT u FROM Usuario u WHERE u.numeroColegiado = :numeroColegiado"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = true)
    @Column(name = "registro_academico")
    private String registroAcademico;
    @Basic(optional = true)
    @Column(name = "carnet")
    private String carnet;
    @Basic(optional = true)
    @Column(name = "numero_colegiado")
    private String numeroColegiado;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioFk")
    private List<CarrerasUsuario> carrerasUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioFk")
    private List<Comentario> comentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudianteFk")
    private List<ProyectoEps> proyectoEpsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSupervisorFk")
    private List<ProyectoEps> proyectoEpsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsesorFk")
    private List<ProyectoEps> proyectoEpsList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSecretariaFk")
    private List<ProyectoEps> proyectoEpsList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioFk")
    private List<Bitacora> bitacoraList;
    @OneToMany(mappedBy = "idUsuarioAfectadFk")
    private List<Bitacora> bitacoraList1;
    @JoinColumn(name = "id_rol_fk", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRolFk;

    public Usuario() {
    }
    
    public Usuario(UsuarioDto usuarioDto) {
        this.correo=usuarioDto.getCorreo();
        this.nombres=usuarioDto.getNombres();
        this.apellidos=usuarioDto.getApellidos();
        this.fechaNacimiento=usuarioDto.getFechaNacimiento();
        this.numeroColegiado=usuarioDto.getNumeroColegiado();
        this.registroAcademico=usuarioDto.getRegistroAcademico();
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String correo, Date fechaNacimiento, String nombres, String apellidos,String registroAcademico, String carnet, String numeroColegiado, String password) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.registroAcademico=registroAcademico;
        this.carnet=carnet;
        this.numeroColegiado=numeroColegiado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public void setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CarrerasUsuario> getCarrerasUsuarioList() {
        return carrerasUsuarioList;
    }

    public void setCarrerasUsuarioList(List<CarrerasUsuario> carrerasUsuarioList) {
        this.carrerasUsuarioList = carrerasUsuarioList;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public List<ProyectoEps> getProyectoEpsList() {
        return proyectoEpsList;
    }

    public void setProyectoEpsList(List<ProyectoEps> proyectoEpsList) {
        this.proyectoEpsList = proyectoEpsList;
    }

    public List<ProyectoEps> getProyectoEpsList1() {
        return proyectoEpsList1;
    }

    public void setProyectoEpsList1(List<ProyectoEps> proyectoEpsList1) {
        this.proyectoEpsList1 = proyectoEpsList1;
    }

    public List<ProyectoEps> getProyectoEpsList2() {
        return proyectoEpsList2;
    }

    public void setProyectoEpsList2(List<ProyectoEps> proyectoEpsList2) {
        this.proyectoEpsList2 = proyectoEpsList2;
    }

    public List<ProyectoEps> getProyectoEpsList3() {
        return proyectoEpsList3;
    }

    public void setProyectoEpsList3(List<ProyectoEps> proyectoEpsList3) {
        this.proyectoEpsList3 = proyectoEpsList3;
    }

    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

    public void setBitacoraList(List<Bitacora> bitacoraList) {
        this.bitacoraList = bitacoraList;
    }

    public List<Bitacora> getBitacoraList1() {
        return bitacoraList1;
    }

    public void setBitacoraList1(List<Bitacora> bitacoraList1) {
        this.bitacoraList1 = bitacoraList1;
    }

    public Rol getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(Rol idRolFk) {
        this.idRolFk = idRolFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.cunoc.controleps.model.entity.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
