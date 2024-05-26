package com.example.drp.domain.customizedFields;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "customized_fields")
@Entity(name = "customizedField")
@NoArgsConstructor
@AllArgsConstructor
public class CustomizedField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "name")
    private String name;

    @Column(name = "label")
    private String label;

    @Column(name = "obligatory")
    private boolean obligatory;

    @Column(name = "type")
    private FieldType type;

    @Column(name = "company_id")
    private long companyId;

    @Column(name = "author_id")
    private long authorId;

    @Column(name = "update_author_id")
    private long updateAuthorId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public CustomizedField(CustomizedFieldRequestDTO request) {
        this.active = request.active();
        this.name = request.name();
        this.label = request.label();
        this.obligatory = request.obligatory();
        this.type = request.type();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isObligatory() {
        return obligatory;
    }

    public void setObligatory(boolean obligatory) {
        this.obligatory = obligatory;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getUpdateAuthorId() {
        return updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        this.updateAuthorId = updateAuthorId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
