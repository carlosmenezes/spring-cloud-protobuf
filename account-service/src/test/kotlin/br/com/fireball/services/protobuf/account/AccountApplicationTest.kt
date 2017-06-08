/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.account

import br.com.fireball.services.protobuf.account.model.AccountProto
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import java.util.logging.Logger

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class AccountApplicationTest {

    val logger = Logger.getLogger(AccountApplicationTest::class.java.name)

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun testFindByNumber() {
        val a = template.getForObject("/accounts/{id}", AccountProto.Account::class.java, "111111")
        logger.info("Account[\n$a]")
    }

    @Test
    fun testFindByCustomer() {
        val a = template.getForObject("/accounts/customer/{customer}", AccountProto.Accounts::class.java, "2")
        logger.info("Accounts[\n$a]")
    }

    @Test
    fun testFindAll() {
        val a = template.getForObject("/accounts", AccountProto.Accounts::class.java)
        logger.info("Accounts[\n$a]")
    }


    @TestConfiguration
    internal class Config {

        @Bean
        fun restTemplateBuilder() = RestTemplateBuilder().additionalMessageConverters(ProtobufHttpMessageConverter())

    }

}