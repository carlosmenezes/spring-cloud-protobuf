/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.account

import br.com.fireball.services.protobuf.account.data.AccountRepository
import br.com.fireball.services.protobuf.account.model.AccountProto.Account.newBuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.client.RestTemplate

@SpringBootApplication
open class Application {

    @Bean
    @Primary
    open fun protobufHttpMessageConverter() = ProtobufHttpMessageConverter()

    @Bean
    open fun restTemplate(hmc: ProtobufHttpMessageConverter) = RestTemplate(listOf(hmc))

    @Bean
    open fun repository(): AccountRepository {
        val accounts = listOf(
            newBuilder().setId(1).setCustomerId(1).setNumber("111111").build(),
            newBuilder().setId(2).setCustomerId(2).setNumber("222222").build(),
            newBuilder().setId(3).setCustomerId(3).setNumber("333333").build(),
            newBuilder().setId(4).setCustomerId(4).setNumber("444444").build(),
            newBuilder().setId(5).setCustomerId(1).setNumber("555555").build(),
            newBuilder().setId(6).setCustomerId(2).setNumber("666666").build(),
            newBuilder().setId(7).setCustomerId(2).setNumber("777777").build()
        )
        return AccountRepository(accounts)
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

}
