package com.ambita.service

import com.ambita.model.User

interface UserService {
  fun createUser(user: User): User
}
