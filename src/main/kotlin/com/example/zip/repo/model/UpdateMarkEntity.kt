package com.example.zip.repo.model

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class UpdateMarkEntity {

    @CreationTimestamp
    @Column(name = "REG_DT", updatable = false)
    lateinit var regDate: Date

    @UpdateTimestamp
    @Column(name = "UPDT_DT")
    lateinit var updateDate: Date

    @PrePersist
    fun preInsert() {
        regDate = Date()
        updateDate = regDate
    }

    @PreUpdate
    fun preUpdate() {
        updateDate = Date()
    }


    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
    }

}