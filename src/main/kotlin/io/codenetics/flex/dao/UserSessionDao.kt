package io.codenetics.flex.dao

import io.codenetics.flex.domain.UserSession
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional


@Transactional
interface UserSessionDao : Repository<UserSession, String>, CrudRepository<UserSession, String> {


}