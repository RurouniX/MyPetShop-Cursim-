package br.com.mypetshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import br.com.mypetshop.network.model.LoginCredentials
import br.com.mypetshop.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val loginButton by lazy { findViewById<Button>(R.id.login_btn_login) }
    private val edtLogin by lazy { findViewById<EditText>(R.id.login_edt_username) }
    private val edtPassword by lazy { findViewById<EditText>(R.id.login_edt_password) }
    private val chk  by lazy { findViewById<CheckBox>(R.id.login_chk_save_password) }

    private val loginApi by lazy {
        RetrofitConfig.getLoginApi()
    }

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
        val username = edtLogin.text.toString()
        val password = edtPassword.text.toString()
        val loginCredentials = LoginCredentials(username, password)
        val loginCallback = loginApi.doLogin(loginCredentials)
        loginCallback.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (chk.isChecked)
                    Toast.makeText(
                        this@LoginActivity,
                        "Login feito com sucesso e manterá logado",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    Toast.makeText(this@LoginActivity, "Login feito com sucesso", Toast.LENGTH_SHORT).show()
                    this@LoginActivity.startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    this@LoginActivity.finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Usuário ou senha errada", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun isLoginAndPasswordValid(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()

    private fun isCredentialsValid(login: String, password: String) =
        login == "MarKanbi" && password == "Sembugs"

}