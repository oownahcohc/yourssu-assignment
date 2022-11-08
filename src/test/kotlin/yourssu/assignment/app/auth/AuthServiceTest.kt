package yourssu.assignment.app.auth

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import yourssu.assignment.app.auth.dto.request.SignupRequest
import yourssu.assignment.app.user.service.UserService
import yourssu.assignment.common.exception.custom.ConflictException
import yourssu.assignment.common.exception.custom.UnAuthorizedException
import yourssu.assignment.common.utils.BCryptUtils
import yourssu.assignment.common.utils.JwtUtils
import yourssu.assignment.domain.user.entity.User
import yourssu.assignment.domain.user.entity.UserRole
import yourssu.assignment.domain.user.repository.UserRepository

class AuthServiceTest: BehaviorSpec({
    val userRepository = mockk<UserRepository>(relaxed = true)
    val bCryptUtils = mockk<BCryptUtils>(relaxed = true)
    val jwtUtils = mockk<JwtUtils>(relaxed = true)

    val userService: UserService = spyk(UserService(userRepository))
    val authService: AuthService = spyk(AuthService(userService, userRepository, bCryptUtils, jwtUtils))

    Given("이미 회원가입한 유저일 경우") {
        every { userRepository.existByEmail(any()) } returns true

        When("해당 이메일과 비밀번호로 회원가입을 시도하면") {
            Then("ConflictException 이 발생한다") {
                shouldThrow<ConflictException> {
                    authService.signup(SignupRequest("","","", UserRole.USER))
                }
            }
        }
    }

    Given("처음 회원가입하는 경우") {
        every { userRepository.existByEmail(any()) } returns false

        When("정상적인 이메일, 비밀번호를 입력하면") {

            val request = SignupRequest("abcd1234@naver.com", "password", "유어슈", UserRole.USER)
            val encoded = bCryptUtils.encodePassword(request.password)
            val createDto = request.toCreateUserDto(encoded)
            val new = User.newInstance(createDto.username, createDto.email, createDto.password, UserRole.USER)

            every { bCryptUtils.encodePassword(any()) } returns encoded
            every { userRepository.save(any()) } returns new

            val response = withContext(Dispatchers.IO) {
                authService.signup(request)
            }

            Then("회원을 저장하고 정보를 반환한다") {
                verify { userRepository.save(any()) }
                response.email shouldBe new.email
                response.password shouldBe new.password
                response.username shouldBe new.username
            }
        }
    }

    Given("회원가입 이후 유효한 회원을 판별하는 경우") {
        val email = "EMAIL"
        val username = "유어슈"
        val password = "PASSWORD"
        val encodePassword = bCryptUtils.encodePassword(password)
        val user = User.newInstance(username, email, encodePassword, UserRole.USER)

        every { userRepository.save(any()) } returns user
        every { userRepository.existByEmail(any()) } returns true
        every { userRepository.findByEmail(any()) } returns user

        When("비밀번호를 잘못 입력했을 경우") {
            val wrongPassword = "WRONG_PASSWORD"

            every { bCryptUtils.isMatchedPassword(wrongPassword, user.password) } returns false

            Then("UnAuthorizedException 이 발생한다") {
                shouldThrow<UnAuthorizedException> {
                    authService.getValidUser(email, wrongPassword)
                }
            }
        }

        When("비밀번호를 올바르게 입력했을 경우") {
            every { bCryptUtils.isMatchedPassword(password, user.password) } returns true

            val valid = withContext(Dispatchers.IO) {
                authService.getValidUser(email, password)
            }

            Then("검증된 해당 유저를 반환한다") {
                valid.email shouldBe user.email
                valid.password shouldBe user.password
                valid.username shouldBe user.username
            }
        }
    }

    afterTest {
        clearAllMocks(answers = false)
    }

})