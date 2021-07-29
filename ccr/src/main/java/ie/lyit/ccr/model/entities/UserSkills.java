package ie.lyit.ccr.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "userskills")
public class UserSkills {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "value")
    private Integer value;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSkillId() {
        return this.skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
