package com.ambita.controller

import com.ambita.controller.api.Error
import com.ambita.controller.api.User
import com.ambita.longeco.exception.DuplicationException
import com.ambita.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid


@RestController
@RequestMapping(path=arrayOf("/v1/users"), produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class UserController (@Autowired val service: UserService, @Value("longeco.baseUrl") val baseUrl: String) {
  private val logger = LoggerFactory.getLogger(UserController::class.java)

  @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
  fun createUser(@RequestBody @Valid user: User) : ResponseEntity<Any> {
    try {
      val newUser: com.ambita.model.User = service.createUser(mapApi2Model(user))
      val location = URI("$baseUrl/v1/users/${newUser.id}")
      return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).build()
    }
    catch (de: DuplicationException ) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(Error(HttpStatus.CONFLICT.value(), de.message?:"Duplication"))
    }
    catch (e: Exception){
        logger.error("Unexpected error ${e.message}", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(com.ambita.controller.api.Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error  ${e::class.java}. Check logs."))
    }
  }

  private fun mapApi2Model(user: User): com.ambita.model.User {
    val modelUSer = com.ambita.model.User(null, user.username, user.password, user.email, user.name, user.active )
    return modelUSer
  }

}
