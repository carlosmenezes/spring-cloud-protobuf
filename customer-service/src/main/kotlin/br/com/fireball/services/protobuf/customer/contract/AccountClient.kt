/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.customer.contract

import br.com.fireball.services.protobuf.customer.model.CustomerProto
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "account-service")
interface AccountClient {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/accounts/customer/{customerId}")
    fun getAccounts(@PathVariable("customerId") customerId: Int): CustomerProto.Accounts

}