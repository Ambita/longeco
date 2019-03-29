package com.ambita.service

import com.ambita.longeco.exception.DuplicationException
import com.ambita.model.User
import com.ambita.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(@Autowired val userRepository: UserRepository, @Autowired val passwordEncoder: PasswordEncoder) : UserService {

  override fun createUser(user: User): User {
    if (userRepository.findByEmail(user.email).isPresent) {
      throw DuplicationException("User with email ${user.email} already exists")
    }
    user.setPassword(passwordEncoder.encode(user.password))
    return userRepository.save(user)
  }
}
