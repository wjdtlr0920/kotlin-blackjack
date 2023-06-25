package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UsersTest : BehaviorSpec({

    Given("비어있는 유저 배열이 있다.") {
        val userList = emptySet<User>()
        When("해당 배열로 Users를 만들면") {
            Then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { Users(userList) }
            }
        }
    }

    Given("비어있지 않은 유저 배열이 있다.") {
        val userList = setOf(
            User("홍길동", Deck(listOf(Card(Suit.SPADE, CardNumber.ACE)))),
            User("김한빈", Deck(listOf(Card(Suit.HEART, CardNumber.ACE)))),
        )
        When("해당 배열로 Users를 만들면") {
            Then("정상적으로 생성된다.") {
                Users(userList).size shouldBe 2
            }
        }
    }
})