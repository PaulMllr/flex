package io.codenetics.flex.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import graphql.schema.DataFetchingEnvironment
import io.codenetics.flex.exception.GraphQLRequestError
import io.codenetics.flex.graphql.context.AuthContext
import io.codenetics.flex.graphql.dto.UserDto
import org.springframework.stereotype.Service

/**
 *  Created by Pavel Laktiushkin on 14.03.2019
 */
@Service
class UserQueryResolver : GraphQLQueryResolver {

    fun getUser(env: DataFetchingEnvironment): UserDto {
        val user = env.getContext<AuthContext>().user ?: throw GraphQLRequestError("Unauthorized")
        return UserDto(user.id, user.fullName, user.email)
    }

}