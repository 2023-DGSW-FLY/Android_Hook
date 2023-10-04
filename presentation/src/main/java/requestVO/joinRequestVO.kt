package requestVO
    data class joinRequestVO (
        val userAccount: String,
        val password: String,
        val userName: String,
        val email: String,
        val userInfo: String,
        val githubURL: String,
        val portfolioURL: String
    )
