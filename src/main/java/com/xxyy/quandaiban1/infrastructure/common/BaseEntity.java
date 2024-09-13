package com.xxyy.quandaiban1.infrastructure.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author xy
 * @date 2023-09-12 20:38
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //开启jpa审计功能
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(length = 30, updatable = false)
    private String createBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(length = 30)
    private String updateBy;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @JsonIgnore
    private Boolean deleted = false;

    //region ...

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        // 仅比较Id是否相等, 注意:两个id为null的实体会返回true
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "Entity{" + "Id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleted=" + deleted + '}';
    }

    //endregion
}