package ie.lyit.ccr.model.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author juarezjunior
 *
 */

@Entity
@Table(name = "skills")
public class Skills implements Serializable {
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "id", unique = true, nullable = false) 
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
