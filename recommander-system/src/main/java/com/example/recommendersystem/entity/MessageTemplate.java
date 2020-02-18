package com.example.recommendersystem.entity;

import java.io.Serializable;

public class MessageTemplate implements Serializable {
    private Integer id;

    private String template;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", template=").append(template);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}