package com.desafiomobile

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.desafiomobile.databinding.ActivityPrincipalBinding
import com.desafiomobile.model.database.AppDatabase
import com.desafiomobile.model.entity.PatApelido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrincipalActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPrincipalBinding.inflate(layoutInflater)
    }
    private var patValue: Int? = null

    // Trata o resultado da permissão da câmera
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permissão concedida, abrir a câmera
            openCamera()
        } else {
            // Permissão negada, mostrar mensagem ou tratar conforme necessário
        }
    }

    // Trata o resultado da captura de imagem
    private val startCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") // A sua lógica para tratar a imagem
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val action: String? = intent?.action
        val data: Uri? = intent?.data

        if (Intent.ACTION_VIEW == action && data != null) {
             patValue = data.getQueryParameter("pat")?.toInt()
            // Agora, você tem o valor de 'pat'. Você pode exibi-lo como desejar.
            // Exemplo: Atualizar um TextView
            binding.textViewPat.text = "PAT: $patValue" // Certifique-se de ter um TextView com o id 'textViewPat' no seu layout
        }


        // Inicialmente, desabilita o botão Salvar
        binding.btnSalvar.isEnabled = false

        // Adiciona um ouvinte de texto no campo de apelido para validar a entrada
        binding.inputApelido.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Verifica se o apelido é válido e habilita/desabilita o botão com base nisso
                binding.btnSalvar.isEnabled = s.toString().matches("^[a-zA-Z0-9]{3,20}$".toRegex())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        // Define o que acontece quando o botão Salvar é pressionado
/*        binding.btnSalvar.setOnClickListener {
            val apelido = binding.inputApelido.editText?.text.toString()
            if (apelido.matches("^[a-zA-Z0-9]{3,20}$".toRegex())) {
                Toast.makeText(this, "Apelido válido: $apelido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Apelido inválido. Deve ter entre 3 a 20 caracteres alfanuméricos.", Toast.LENGTH_SHORT).show()
            }
        }*/

        binding.btnSalvar.setOnClickListener {
            // Supondo que você tenha uma maneira de obter o PAT da URI. Pode ser uma variável de instância na sua Activity.
            val pat = this.patValue // Substitua isso pela lógica real de obtenção do PAT
            val apelido = binding.inputApelido.editText?.text.toString().trim()

            // Primeiro, valide os campos
            if (pat == null || pat < 1 || pat > 99) {
                Toast.makeText(applicationContext, "PAT inválido ou não capturado.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (apelido.length < 3 || apelido.length > 20) {
                Toast.makeText(applicationContext, "Apelido inválido. Deve ter entre 3 a 20 caracteres alfanuméricos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val dao = AppDatabase.getDatabase(applicationContext).patApelidoDao()
                val patApelidoExistente = dao.encontrarPorApelido(apelido)

                if (patApelidoExistente != null) {
                    // Apelido já existe, mostre uma mensagem de erro na UI Thread
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Apelido já existe.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Tente inserir o novo PatApelido no banco de dados
                    val novoPatApelido = PatApelido(pat = pat, apelido = apelido)
                    val id = dao.inserir(novoPatApelido)
                    withContext(Dispatchers.Main) {
                        if (id != -1L) {
                            Toast.makeText(applicationContext, "Registro salvo com sucesso.", Toast.LENGTH_SHORT).show()
                        } else {
                            // Lidar com a falha de inserção, se necessário
                            Toast.makeText(applicationContext, "Erro ao salvar o registro.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.cardpost.setOnClickListener{
    startActivity(
        Intent(this, PostagemActivity::class.java)
    )

}

        binding.cardFoto.setOnClickListener{
            startActivity(
                Intent(this, FotoActivity::class.java)
            )

        }

        binding.usuariosCadastrado.setOnClickListener{
            startActivity(
                Intent(this, UsuariosActivity::class.java)
            )

        }

        binding.switchCamera.setOnCheckedChangeListener { _, isChecked ->
            binding.btnCamera.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        binding.btnCamera.setOnClickListener {
            // Solicita a permissão da câmera antes de abri-la
            val cameraPermission = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            )
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            } else {
                openCamera()
            }
        }


   /*     binding.btnSalvar.setOnClickListener {

            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
            finish()

        }*/

    }
    private fun updateUIWithPatValue() {
        // Atualize a UI com 'patValue', certificando-se de que ele não é null
        patValue?.let {
            binding.textViewPat.text = "PAT: $it"
        }
        // Certifique-se de ter um TextView com o id 'textViewPat' no seu layout
    }


    private fun openCamera() {
        // Cria a intenção de capturar a imagem
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startCamera.launch(cameraIntent)
    }

    }
