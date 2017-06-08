/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.account.data

import br.com.fireball.services.protobuf.account.model.AccountProto.Account

class AccountRepository(val accounts: List<Account>) {

    fun findAll() = accounts

    fun findByCustomer(customerId: Int) = accounts.filter { it -> it.customerId == customerId }

    fun findByNumber(number: String) = accounts.stream().filter { it -> it.number == number }.findFirst().get()

}