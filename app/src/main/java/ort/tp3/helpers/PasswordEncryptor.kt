package ort.tp3.helpers

import org.mindrot.jbcrypt.BCrypt

object PasswordEncryptor {
    private const val SALT_ROUNDS = 12

    fun encrypt(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_ROUNDS))
    }

    fun verify(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}


