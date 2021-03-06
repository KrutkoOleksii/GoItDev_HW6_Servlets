package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "skills")
public class Skill implements Serializable, BaseEntity<Long>{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "name",length = 45)
    String name;
    @Column(name = "skill_level",length = 45)
    String skillLevel;
}

