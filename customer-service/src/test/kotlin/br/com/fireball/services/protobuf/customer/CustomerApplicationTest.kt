/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.customer

import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customer
import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customers
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
class CustomerApplicationTest {

    val logger = Logger.getLogger(CustomerApplicationTest::class.java.name)

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun testFindByPesel() {
        val c = this.template.getForObject("/customers/pesel/{pesel}", Customer::class.java, "12346")
        logger.info("Customer[\n$c]")
    }

    @Test
    fun testFindAll() {
        val c = this.template.getForObject("/customers", Customers::class.java)
        logger.info("Customers[\n$c]")
    }


    @TestConfiguration
    internal class Config {

        @Bean
        fun restTemplateBuilder() = RestTemplateBuilder().additionalMessageConverters(ProtobufHttpMessageConverter())

    }
}