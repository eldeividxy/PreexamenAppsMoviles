import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itson.potros.calculadora.R


class MainActivity : AppCompatActivity() {
    private var numADeclararTextView: TextView? = null
    private var resultadoTextView: TextView? = null
    private var operacionActual = ""
    private var resultadoAcumulado = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numADeclararTextView = findViewById<TextView>(R.id.NumADeclarar)
        resultadoTextView = findViewById<TextView>(R.id.Resultado)

        configurarBotones()
    }

    private fun configurarBotones() {
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)

        val numeroClickListener = View.OnClickListener { view ->
            val botonPresionado = view as Button
            val numeroPresionado = botonPresionado.text.toString()

            val textoActual = numADeclararTextView!!.text.toString()
            numADeclararTextView!!.text = textoActual + numeroPresionado
        }

        btn0.setOnClickListener(numeroClickListener)
        btn1.setOnClickListener(numeroClickListener)

        val btnIgual = findViewById<Button>(R.id.btnResult)
        btnIgual.setOnClickListener { calcularResultado() }

        val operadorClickListener = View.OnClickListener { view ->
            val botonOperador = view as Button
            val operadorPresionado = botonOperador.text.toString()

            if (!numADeclararTextView!!.text.toString().isEmpty()) {
                calcularResultado()
            }

            operacionActual = operadorPresionado
        }

        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMultiplicacion = findViewById<Button>(R.id.btnMultiplicacion)
        val btnDivision = findViewById<Button>(R.id.btnDivision)

        btnSuma.setOnClickListener(operadorClickListener)
        btnResta.setOnClickListener(operadorClickListener)
        btnMultiplicacion.setOnClickListener(operadorClickListener)
        btnDivision.setOnClickListener(operadorClickListener)

        val btnBorrar = findViewById<Button>(R.id.btnBorrar)
        btnBorrar.setOnClickListener {
            numADeclararTextView!!.text = ""
            resultadoTextView!!.text = ""
            operacionActual = ""
            resultadoAcumulado = 0.0
        }
    }

    private fun calcularResultado() {
        val numeroIngresado = numADeclararTextView!!.text.toString()
        if (!numeroIngresado.isEmpty()) {
            val numero = numeroIngresado.toDouble()
            when (operacionActual) {
                "+" -> resultadoAcumulado += numero
                "-" -> resultadoAcumulado -= numero
                "*" -> resultadoAcumulado *= numero
                "/" -> if (numero != 0.0) {
                    resultadoAcumulado /= numero
                } else {
                    resultadoAcumulado = 0.0
                    resultadoTextView!!.text = "Error"
                }
                else -> resultadoAcumulado = numero
            }

            resultadoTextView!!.text = resultadoAcumulado.toString()
            numADeclararTextView!!.text = ""
        }
    }
}

