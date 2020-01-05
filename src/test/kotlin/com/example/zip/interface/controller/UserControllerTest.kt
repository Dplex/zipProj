package com.example.zip.`interface`.controller

import com.example.zip.`interface`.dto.UserDto
import com.example.zip.`interface`.request.UserRequest
import com.example.zip.`interface`.response.UserResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.ReflectionTestUtils
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    lateinit var userController: UserController

    @Test
    @DisplayName("user GET")
    fun test1() {
        //when
        val response = userController.getUserList()

        //then
        assertAll("get UserList Test",
            { assertNotNull(response) },
            { assertTrue(response.statusCode.is2xxSuccessful) },
            { assertTrue(response.body != null) },
            { assertTrue(response.body is UserResponse) }
        )
    }

    @Test
    @DisplayName("save user")
    @Transactional
    fun test2() {
        //given
        val testUserRequest = UserRequest()

        ReflectionTestUtils.setField(testUserRequest, "userId", "userIdTest2")
        ReflectionTestUtils.setField(testUserRequest, "userPass", "userPass1")

        //when
        val response = userController.insertUser(testUserRequest)

        //then
        assertAll("save User Test",
            { assertTrue(response.statusCode.is2xxSuccessful) },
            { assertTrue(response.hasBody()) }
        )

        //when
        val userLstResponse = userController.getUserList()

        //then
        assertTrue(userLstResponse.hasBody())
        userLstResponse.body?.run {
            assertTrue(this is UserResponse)

            val userResponse = this as UserResponse
            assertAll("exists testUser in getUserList",
                { assertTrue(userResponse.users.isNotEmpty()) },
                {
                    assertNotNull(userResponse.users.find {
                        it.userId == testUserRequest.userId
                            && it.userPass == testUserRequest.userPass
                    })
                }
            )

        }
    }

}