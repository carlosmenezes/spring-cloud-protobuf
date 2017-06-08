/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.customer.controller

import br.com.fireball.services.protobuf.customer.contract.AccountClient
import br.com.fireball.services.protobuf.customer.data.CustomerRepository
import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customer
import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
class CustomerController {

    val logger = Logger.getLogger(CustomerController::class.java.name)

    @Autowired
    lateinit var repository: CustomerRepository

    @Autowired
    lateinit var accountClient: AccountClient

    @RequestMapping(value = "/customers/pesel/{pesel}", produces = arrayOf("application/x-protobuf"))
    fun findByPesel(@PathVariable("pesel") pesel: String): Customer {
        logger.info("Customer.findByPesel($pesel)")
        return repository.findByPesel(pesel)
    }

    @RequestMapping(value = "/customers", produces = arrayOf("application/x-protobuf"))
    fun findAll(): Customers {
        logger.info("Customer.findAll()")
        return Customers.newBuilder().addAllCustomers(repository.findAll()).build()
    }

    @RequestMapping(value = "/customers/{id}", produces = arrayOf("application/x-protobuf"))
    fun findById(@PathVariable("id") id: Int): Customer {
        logger.info("Customer.findById($id)")
        var customer = repository.findById(id)
        val accounts = accountClient.getAccounts(id)
        customer = Customer.newBuilder(customer).addAllAccounts(accounts.getAccountList()).build()
        return customer
    }

}