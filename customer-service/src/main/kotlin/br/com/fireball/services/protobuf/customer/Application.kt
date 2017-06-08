/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.customer



import br.com.fireball.services.protobuf.customer.data.CustomerRepository
import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customer.CustomerType.INDIVIDUAL
import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customer.newBuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableFeignClients
open class Application {

    @Bean
    open fun protobufHttpMessageConverter() = ProtobufHttpMessageConverter()

    @Bean
    open fun restTemplate(hmc: ProtobufHttpMessageConverter) = RestTemplate(listOf(hmc))

    @Bean
    open fun repository(): CustomerRepository {
        val customers = listOf(
            newBuilder().setId(1).setPesel("12345").setName("Adam Kowalski")
                    .setType(INDIVIDUAL).build(),
            newBuilder().setId(2).setPesel("12346").setName("Anna Malinowska")
                    .setType(INDIVIDUAL).build(),
            newBuilder().setId(3).setPesel("12347").setName("Paweł Michalski")
                    .setType(INDIVIDUAL).build(),
            newBuilder().setId(4).setPesel("12348").setName("Karolina Lewandowska")
                    .setType(INDIVIDUAL).build()
        )
        return CustomerRepository(customers)
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

}