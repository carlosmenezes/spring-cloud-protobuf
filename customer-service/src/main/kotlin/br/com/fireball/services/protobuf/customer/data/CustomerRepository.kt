/**
 * Created by carlos on 08/06/17.
 */
package br.com.fireball.services.protobuf.customer.data

import br.com.fireball.services.protobuf.customer.model.CustomerProto.Customer

class CustomerRepository(val customers: List<Customer>) {

    fun findById(id: Int) = customers.stream().filter { it -> it.id == id }.findFirst().get()

    fun findByPesel(pesel: String) = customers.stream().filter { it -> it.pesel == pesel }.findFirst().get()

    fun findAll() = customers
}