package com.example.zip.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
        basePackages = arrayOf("com.example.zip.repo"),
        entityManagerFactoryRef = "zipEntityManager",
        transactionManagerRef = "zipTransactionManager"
)
class DatasourceConfig (
        @Autowired private val zipConfig: ZipConfig
) {

    @Bean
    fun dataSource(): DataSource {

        return HikariDataSource().also {
            it.jdbcUrl = zipConfig.dbUrl
            it.username = zipConfig.dbUser
            it.password = zipConfig.dbPass
        }
    }

    @Bean
    fun zipEntityManager(): LocalContainerEntityManagerFactoryBean {
        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            setPackagesToScan("com.example.zip.repo.model")
            setJpaPropertyMap(HashMap<String, Any>().apply {
                put("hibernate.hbm2ddl.auto", "update")
                put("hibernate.use_sql_comments", true)

            })
        }
    }

    @Bean
    fun zipTransactionManager() = JpaTransactionManager().apply {
        entityManagerFactory = zipEntityManager().`object`
    }
}