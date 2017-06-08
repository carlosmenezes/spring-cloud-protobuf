package br.com.fireball.services.protobuf.account.controller

import br.com.fireball.services.protobuf.account.data.AccountRepository
import br.com.fireball.services.protobuf.account.model.AccountProto
import br.com.fireball.services.protobuf.account.model.AccountProto.Accounts.newBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
class AccountController {

    @Autowired
    lateinit var repository: AccountRepository

    val logger = Logger.getLogger(AccountController::class.java.name)

    @RequestMapping(value = "/accounts/{number}", produces = arrayOf("application/x-protobuf"))
    fun findByNumber(@PathVariable("number") number: String) : AccountProto.Account {

        logger.info("Account.findByNumber($number)")
        return repository.findByNumber(number)
    }

    @RequestMapping(value = "/accounts/customer/{customer}", produces = arrayOf("application/x-protobuf"))
    fun findByCustomer(@PathVariable("customer") customerId: Int) : AccountProto.Accounts {
        logger.info("Account.findByCustomer($customerId)")
        return newBuilder().addAllAccount(repository.findByCustomer(customerId)).build()
    }

    @RequestMapping(value = "/accounts", produces = arrayOf("application/x-protobuf", "application/json"))
    fun findAll() : AccountProto.Accounts {
        logger.info("Account.findAll()")
        return newBuilder().addAllAccount(repository.findAll()).build()
    }

}
