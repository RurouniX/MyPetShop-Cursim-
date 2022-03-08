package br.com.mypetshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged

class LoginActivity : AppCompatActivity() {

    private val loginButton by lazy { findViewById<Button>(R.id.login_btn_login) }
    private val edtLogin by lazy { findViewById<EditText>(R.id.login_edt_username) }
    private val edtPassword by lazy { findViewById<EditText>(R.id.login_edt_password) }
    private val chk  by lazy { findViewById<CheckBox>(R.id.login_chk_save_password) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupUI()

    }

    private fun setupUI() {
        loginButton.setOnClickListener {
            verifyCredentials(edtLogin, edtPassword, chk)
        }

        edtLogin.doOnTextChanged { _, _, _, _ ->
            loginButton.isEnabled =
                isLoginAndPasswordValid(edtLogin.text.toString(), edtPassword.text.toString())
        }

        edtPassword.doOnTextChanged { _, _, _, _ ->
            loginButton.isEnabled =
                isLoginAndPasswordValid(edtLogin.text.toString(), edtPassword.text.toString())
        }
    }

    private fun verifyCredentials(
        edtLogin: EditText,
        edtPassword: EditText,
        chk: CheckBox
    ) {
        if (isCredentialsValid(edtLogin.text.toString(), edtPassword.text.toString())) {
            if (chk.isChecked)
                Toast.makeText(
                    this@LoginActivity,
                    "Login feito com sucesso e manterá logado",
                    Toast.LENGTH_SHORT
                )
                    .show()
            else {
                Toast.makeText(this@LoginActivity, "Login feito com sucesso", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        } else
            Toast.makeText(this@LoginActivity, "Usuário ou senha errada", Toast.LENGTH_SHORT)
                .show()
    }

    private fun isLoginAndPasswordValid(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()

    private fun isCredentialsValid(login: String, password: String) =
        login == "MarKanbi" && password == "Sembugs"

}