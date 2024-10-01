package com.example.demo.domain.models;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {

	@NotNull(message = "El ID no puede ser null")
	@Min(value = 1, message = "El ID debe ser un numero positivo")
	private final Long id;
	
	@NotNull(message = "El nombre no puede ser null")
	@NotEmpty(message = "El nombre no puede ser vacio")
	private final String nombre;
	
	@NotNull(message = "El apellido no puede ser null")
	@NotEmpty(message = "El apellido no puede ser vacio")
	private final String apellido;
	
	@NotNull(message = "El documento de identidad no puede ser null")
	@NotEmpty(message = "El documento de identidad no puede ser vacio")
	@Pattern(regexp = "\\d+", message = "El documento de identidad debe ser numérico")
	private final String documentoDeIdentidad;
	
	@Size(max = 13, message= "El numero de celular debe tener 13 caracteres como maximo")
	@Pattern(regexp = "^(\\+\\d{1,2}|\\d{1,2})\\s?\\d+$", message = "El numero de celular debe ser numérico y puede comenzar con un '+' seguido de uno o dos dígitos, o solo con uno o dos dígitos, con un espacio opcional antes de los siguientes dígitos, estructura que debe tener: (indicativo pais opcional +57) +(espacio opcional)+ (numero celular 3219876543) = +57 3219876543, tambien puede quedar +573219876543 o 3219876543.")
	private final String celular;
	
	@NotNull(message = "La fecha de nacimiento no puede ser null")
	private final Date fechaNacimiento;
	
	@NotNull(message = "El correo no puede ser null")
	@NotEmpty(message = "El correo no puede ser vacio")
	@Email(message = "El correo debe ser válido")
	private final String correo;
	
	@NotNull(message = "La clave no puede ser null")
	@NotEmpty(message = "La clave no puede ser vacio")
	private final String clave;

	@Schema(hidden = true)
    private Rol rol;

	public Usuario(Long id, String nombre, String apellido, String documentoDeIdentidad, String celular,
			Date fechaNacimiento, String correo, String clave,Rol rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documentoDeIdentidad = documentoDeIdentidad;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.clave = clave;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDocumentoDeIdentidad() {
		return documentoDeIdentidad;
	}

	public String getCelular() {
		return celular;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

	public Rol getRol() {
		return rol;
	}
	
	@Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documentoDeIdentidad='" + documentoDeIdentidad + '\'' +
                ", celular='" + celular + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", claveEncriptada='" + clave + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
